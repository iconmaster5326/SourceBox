package com.iconmaster.srcbox;

import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class LibraryCore extends SourcePackage {

	public LibraryCore() {
		this.name = "core";
		
		this.addType(TypeDef.UNKNOWN);
		this.addType(TypeDef.REAL);
		this.addType(TypeDef.STRING);
		this.addType(TypeDef.LIST);
		
		Function fn = Function.libraryFunction("print", new String[] {"item"}, new TypeDef[] {TypeDef.UNKNOWN}, null);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			exc.println(args[1]);
			return null;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("range", new String[] {"begin","end"}, new TypeDef[] {TypeDef.REAL,TypeDef.REAL}, TypeDef.LIST);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a = new ArrayList();
			for (double i=(Double)args[1];i<=(Double)args[2];i++) {
				a.add(i);
			}
			return a;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("list.size", new String[] {"list"}, new TypeDef[] {TypeDef.LIST}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return ((ArrayList)args[1]).size();
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("list.append", new String[] {"list","item"}, new TypeDef[] {TypeDef.LIST,TypeDef.UNKNOWN}, TypeDef.LIST);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a = (ArrayList) args[1];
			a.add(args[2]);
			return a;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("list.join", new String[] {"list1","list2"}, new TypeDef[] {TypeDef.LIST,TypeDef.LIST}, TypeDef.LIST);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			ArrayList a1 = (ArrayList) args[1];
			ArrayList a2 = (ArrayList) args[2];
			a1.addAll(a2);
			return a1;
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("string._cast", new String[] {"item"}, new TypeDef[] {}, TypeDef.STRING);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return String.valueOf(args[1]);
		};
		this.addFunction(fn);
		
	}
	
}
