package com.iconmaster.srcbox;

import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class BoxFrame {
	public BoxFrame parent;
	public HashMap<String,Object> vars = new HashMap<>();

	public BoxFrame() {
		
	}

	public BoxFrame(BoxFrame parent) {
		this.parent = parent;
	}
	
	public Object getVar(String name) {
		Object v = vars.get(name);
		if (v==null && parent!=null) {
			return parent.getVar(name);
		} else {
			return v;
		}
	}
	
	public void putVar(String name, Object value) {
		if (getVar(name)==null) {
			vars.put(name, value);
		} else if (vars.get(name)!=null) {
			vars.put(name, value);
		} else if (parent!=null) {
			parent.putVar(name, value);
		}
	}
}
