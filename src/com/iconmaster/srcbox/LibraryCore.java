package com.iconmaster.srcbox;

import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;

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
	}
	
}
