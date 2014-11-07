package com.iconmaster.srcbox;

import com.iconmaster.source.prototype.Field;
import com.iconmaster.source.prototype.Function;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;

/**
 *
 * @author iconmaster
 */
public class LibraryMath extends SourcePackage {

	public LibraryMath() {
		this.name = "math";
		
		Function fn = Function.libraryFunction("sin", new String[] {"n"}, new TypeDef[] {TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Math.sin((Double) args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("cos", new String[] {"n"}, new TypeDef[] {TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Math.cos((Double) args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("tan", new String[] {"n"}, new TypeDef[] {TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Math.tan((Double) args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("log", new String[] {"n"}, new TypeDef[] {TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Math.log10((Double) args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("ln", new String[] {"n"}, new TypeDef[] {TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Math.log((Double) args[1]);
		};
		this.addFunction(fn);
		
		fn = Function.libraryFunction("sqrt", new String[] {"n"}, new TypeDef[] {TypeDef.REAL}, TypeDef.REAL);
		fn.onRun = (pkg,args)->{
			Executor exc = (Executor) args[0];
			
			return Math.sqrt((Double) args[1]);
		};
		this.addFunction(fn);
	
		Field f = Field.libraryField("pi", TypeDef.REAL);
		f.onRun = (pkg,isGet,args)->{
			return Math.PI;
		};
		this.addField(f);
		
		f = Field.libraryField("e", TypeDef.REAL);
		f.onRun = (pkg,isGet,args)->{
			return Math.E;
		};
		this.addField(f);
	}
}
