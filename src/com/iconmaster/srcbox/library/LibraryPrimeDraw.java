package com.iconmaster.srcbox.library;

import com.iconmaster.srcbox.execute.Executor;
import com.iconmaster.source.prototype.Field;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author iconmaster
 */
public class LibraryPrimeDraw extends SourcePackage {
	public static TypeDef GROB_TYPE = new TypeDef("grob");
	
	public static class Grob {
		public int w = 0;
		public int h = 0;
		
		public BufferedImage image;

		public Grob() {
			
		}

		public Grob(int w, int h) {
			resize(w,h);
		}
		
		public void resize(int w, int h) {
			this.w = w;
			this.h = h;
			
			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		}
	}
	
	public static final Grob screen = new Grob(320,240);
	
	public static final Grob[] buffers = new Grob[9];
	
	static {
		for (int i=0;i<buffers.length;i++) {
			buffers[i] = new Grob();
		}
	}

	public LibraryPrimeDraw() {
		this.name = "prime.draw";
		
		this.addType(GROB_TYPE);
		
		Function fn = Function.libraryFunction("grob.width", new String[] {"g"}, new TypeDef[] {GROB_TYPE}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return ((Grob) args[1]).w;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("grob.height", new String[] {"g"}, new TypeDef[] {GROB_TYPE}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return ((Grob) args[1]).h;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("grob.drawString", new String[] {"g","text","x","y"}, new TypeDef[] {GROB_TYPE,TypeDef.STRING,TypeDef.REAL,TypeDef.REAL}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			Grob g = (Grob) args[1];
			g.image.getGraphics().setColor(Color.BLACK);
			g.image.getGraphics().drawString((String) args[2],((Double) args[3]).intValue(),((Double) args[4]).intValue());
			exc.updatePrimeDraw();
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("grob.drawString", new String[] {"g","text","x","y","font"}, new TypeDef[] {GROB_TYPE,TypeDef.STRING,TypeDef.REAL,TypeDef.REAL,TypeDef.REAL}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			Grob g = (Grob) args[1];
			g.image.getGraphics().setColor(Color.BLACK);
			g.image.getGraphics().drawString((String) args[2],((Double) args[3]).intValue(),((Double) args[4]).intValue());
			exc.updatePrimeDraw();
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("grob.fill", new String[] {"g","color"}, new TypeDef[] {GROB_TYPE,TypeDef.REAL}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			Grob g = (Grob) args[1];
			g.image.getGraphics().setColor((Color) args[2]);
			g.image.getGraphics().fillRect(0, 0, g.w, g.h);
			exc.updatePrimeDraw();
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("grob.clear", new String[] {"g"}, new TypeDef[] {GROB_TYPE}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			Grob g = (Grob) args[1];
			g.image.getGraphics().setColor(Color.WHITE);
			g.image.getGraphics().fillRect(0, 0, g.w, g.h);
			exc.updatePrimeDraw();
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("rgb", new String[] {"r","g","b"}, new TypeDef[] {TypeDef.REAL,TypeDef.REAL,TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			return new Color(((Double) args[1]).intValue(),((Double) args[2]).intValue(),((Double) args[3]).intValue());
		};
		this.addFunction(fn);
		
		Field f = Field.libraryField("screen", GROB_TYPE);
		f.onRun = (pkg,isGet,args)->{
			Executor exc = (Executor) args[0];
			return screen;
		};
		this.addField(f);
		
		for (int i=1;i<=9;i++) {
			final int ii = i;
			f = Field.libraryField("buffer"+i, GROB_TYPE);
			f.onRun = (pkg,isGet,args)->{
				return buffers[ii-1];
			};
			this.addField(f);
		}
	}
}
