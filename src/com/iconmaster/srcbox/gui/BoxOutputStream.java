package com.iconmaster.srcbox.gui;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author iconmaster
 */
public class BoxOutputStream extends OutputStream {
	public BoxOutput out;

	@Override
	public void write(int b) throws IOException {
		if (out==null) {
			out = new BoxOutput();
			out.setVisible(true);
		}
		out.print((char) b);
	}
	
}
