package com.iconmaster.sbcore.execute;

import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public abstract class Executor {
	public VirtualMachine vm;
	public HashMap<String,SourceObject> vars = new HashMap<>();
	
	public boolean done = false;
	
	public abstract void step();
	
	public SourceObject getVar(String name) {
		SourceObject ob = vars.get(name);
		if (ob==null) {
			return vm.fields.get(name);
		} else {
			return ob;
		}
	}
	
	public void setVar(String name, SourceObject value) {
		if (vm.pkg.getField(name)!=null) {
			vm.fields.put(name, value);
		} else {
			vars.put(name, value);
		}
	}
}
