package com.iconmaster.srcbox.gui;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author iconmaster
 */
public class BoxOutputStream extends OutputStream {
	public BoxOutput out;
	public boolean isError = false;

	public BoxOutputStream(boolean b) {
		isError = b;
	}

	@Override
	public void write(int b) throws IOException {
		if (out==null) {
			out = new BoxOutput(isError);
			out.setVisible(true);
		}
		out.print((char) b);
	}
	
}
