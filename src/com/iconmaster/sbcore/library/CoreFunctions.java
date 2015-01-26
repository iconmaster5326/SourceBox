package com.iconmaster.sbcore.library;

import com.iconmaster.sbcore.execute.SourceObject;
import com.iconmaster.sbcore.execute.VirtualMachine;
import com.iconmaster.source.prototype.SourcePackage;
import com.iconmaster.source.prototype.TypeDef;
import java.io.PrintWriter;
import java.util.ArrayList;

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
			PrintWriter pw = new PrintWriter(vm.outputStream);
			pw.println(args[0].data);
			pw.flush();
			return null;
		});
		
		pkg.getIterator("core.range").data.put("onRun",(CustomIterator) (vm, args) -> {
			ArrayList<SourceObject[]> pairs = new ArrayList<>();
			int begin = ((Number)args[0].data).intValue();
			int end = ((Number)args[1].data).intValue();
			for (int i=begin;i<=end;i++) {
				pairs.add(new SourceObject[] {new SourceObject(TypeDef.INT, i)});
			}
			return pairs.toArray(new SourceObject[0][0]);
		});
	}
}
