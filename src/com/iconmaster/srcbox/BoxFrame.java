package com.iconmaster.srcbox;

import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class BoxFrame {
	public BoxFrame parent;
	public Executor exc;
	public HashMap<String,Object> vars = new HashMap<>();

	public BoxFrame(Executor exc) {
		this.exc = exc;
	}

	public BoxFrame(Executor exc, BoxFrame parent) {
		this(exc);
		this.parent = parent;
	}
	
	public Object getVar(String name) {
		Object v = vars.get(name);
		if (v==null && parent!=null) {
			return parent.getVar(name);
		} else {
			if (v==null) {
				return exc.getField(name).value;
			}
			return v;
		}
	}
	
	public void putVar(String name, Object value) {
		if (exc.pkg.getField(name)!=null) {
			exc.setField(name, value);
			return;
		}
		
		if (getVar(name)==null) {
			vars.put(name, value);
		} else if (vars.get(name)!=null) {
			vars.put(name, value);
		} else if (parent!=null) {
			parent.putVar(name, value);
		}
	}
}
