package com.iconmaster.sbcore.library;

import com.iconmaster.sbcore.execute.SourceObject;
import com.iconmaster.sbcore.execute.VirtualMachine;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;

/**
 *
 * @author iconmaster
 */
public class CoreFunctions {
	public static interface CustomFunction {
		public SourceObject execute(VirtualMachine vm, SourceObject... args);
	}
	
	public static interface CustomIterator {
		public SourceObject[][] execute(VirtualMachine vm, SourceObject... args);
	}
	
	public static void registerFunctions(SourcePackage pkg) {
		pkg.getFunction("core.print").data.put("onRun",(CustomFunction) (vm, args) -> {
			System.out.println(args[0].data);
			return null;
		});
		
		pkg.getIterator("core.range").data.put("onRun",(CustomIterator) (vm, args) -> {
			return new SourceObject[][] {{new SourceObject(TypeDef.INT, 3)},{new SourceObject(TypeDef.INT, 2)}};
		});
	}
}
