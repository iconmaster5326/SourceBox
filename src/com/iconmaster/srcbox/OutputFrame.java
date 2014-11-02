package com.iconmaster.srcbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author iconmaster
 */
public class OutputFrame extends JPanel {
	public static int ROWS = 35;
	public static int COLS = 16;
	
	public int rows = 35;
	public int cols = 16;
	
	public int winW = 0;
	public int winH = 0;

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void setSize(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}
	
	public void setWindowSize(int w, int h) {
		this.winW = w;
		this.winH = h;
	}
	
	public int cellW() {
		return winW/rows;
	}
	
	public int cellH() {
		return winH/cols;
	}
	
	public int boxOffset() {
		return (winH/600+winW/1200)/2;
	}
	
	public int stringOffsetX() {
		return (winH/600+winW/300)/2;
	}
	
	public int stringOffsetY() {
		return (winH/200+winW/400)/2;
	}
	
	public void drawRect(Graphics g,int x,int y,int w,int h, Color c) {
		g.setColor(c);
		g.drawRect(x*cellW()+boxOffset(), y*cellH()+boxOffset(), w*cellW()-boxOffset(), h*cellH()-boxOffset());
	}
	
	public void drawRect(Graphics g,int x,int y,int w,int h) {
		this.drawRect(g, x, y, w, h, Color.BLACK);
	}
	
	public void drawFillRect(Graphics g,int x,int y,int w,int h, Color c) {
		g.setColor(c);
		g.fillRect(x*cellW()+boxOffset(), y*cellH()+boxOffset(), w*cellW()-boxOffset(), h*cellH()-boxOffset());
	}
	
	public void drawFillRect(Graphics g,int x,int y,int w,int h) {
		this.drawFillRect(g, x, y, w, h, Color.WHITE);
	}
	
	public void highlight(Graphics g,int x,int y,int w,int h) {
		g.setColor(new Color(0,0,0,128));
		g.fillRect(x*cellW()+boxOffset(), y*cellH()+boxOffset(), w*cellW()-boxOffset(), h*cellH()-boxOffset());
	}
	
	public void drawBorderedRect(Graphics g,int x,int y,int w,int h, Color back, Color outline) {
		drawFillRect(g,x,y,w,h,back);
		drawRect(g,x,y,w,h,outline);
	}
	
	public void drawBorderedRect(Graphics g,int x,int y,int w,int h) {
		this.drawBorderedRect(g, x, y, w, h, Color.WHITE, Color.BLACK);
	}
	
	public void drawString(Graphics g, String str, int x, int y, Color c) {
		g.setColor(c);
		Font oldf = g.getFont();
		g.setFont(getFont(cellW(), cellH()));
		g.drawString(str, x*cellW()+stringOffsetX(), (y+1)*cellH()-stringOffsetY());
		g.setFont(oldf);
	}
	
	public void drawString(Graphics g, String str, int x, int y) {
		this.drawString(g, str, x, y, Color.BLACK);
	}
	
	public void drawStringRightJustified(Graphics g, String str, int x, int y, Color c) {
		drawString(g,str,x-str.length(),y,c);
	}
	
	public void drawStringRightJustified(Graphics g, String str, int x, int y) {
		this.drawString(g,str,x-str.length(),y);
	}
	
	public void drawStringCentered(Graphics g, String str, int x, int y, Color c) {
		g.setColor(c);
		drawCenteredString(g, str, x*cellW()+cellW()/2, (y+1)*cellH()-3);
	}
	
	public void drawStringCentered(Graphics g, String str, int x, int y) {
		this.drawStringCentered(g, str, x, y, Color.BLACK);
	}
	
	public void drawCursor(Graphics g, int cx, int cy) {
		g.setColor(new Color(0,0,0,128));
		g.fillRect((cx+1)*cellW(), (cy)*cellH(), cellW()/4, cellH());
	}

	public void onResize() {
		
	}
	
	public static Font getFont(int xSize, int ySize) {
		return new Font(Font.MONOSPACED, Font.PLAIN, (int) (Math.min(xSize, ySize) * 1.5));
	}
	
	public static void drawCenteredString(Graphics g, String s, int x, int y) {
		g.drawString(s, (int) (x-g.getFontMetrics().getStringBounds(s, g).getMaxX()/2), y);
	}
	
	public String[] output = new String[COLS];

	@Override
	public void paintComponent(Graphics g) {
		int col = 0;
		for (String str : output) {
			if (str!=null) {
				drawString(g, str, 0, col);
			}
			col++;
		}
	}
	
	public void println(String str) {
		int col = 0;
		//find first non-null column
		for (String str2 : output) {
			if (str2==null) {
				output[col] = str;
				return;
			}
			col++;
		}
		//shift output up one
		col = 1;
		for (String str2 : output) {
			output[col-1] = str2;
			col++;
		}
		output[col] = str;
		
		this.repaint();
	}
}
