package com.iconmaster.sbcore.library;

import com.iconmaster.sbcore.execute.ExecUtils;
import com.iconmaster.sbcore.execute.SourceObject;
import com.iconmaster.sbcore.execute.VirtualMachine;
import com.iconmaster.source.prototype.Function;
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
		
		Function fn;
		
		fn = pkg.getFunction("core.int._add");
		fn.data.put("onRun",(CustomFunction) (vm, args) -> {
			if (!ExecUtils.checkType(vm, fn, args)) {return null;}
			return new SourceObject(args[0].type, ((int)args[0].data)+((int)args[1].data));
		});
		pkg.getFunction("core.int64._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((long)args[0].data)+((long)args[1].data));
		});
		pkg.getFunction("core.int32._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)+((int)args[1].data));
		});
		pkg.getFunction("core.int16._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((short)args[0].data)+((short)args[1].data));
		});
		pkg.getFunction("core.int8._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((byte)args[0].data)+((byte)args[1].data));
		});
		pkg.getFunction("core.real._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)+((float)args[1].data));
		});
		pkg.getFunction("core.real64._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((double)args[0].data)+((double)args[1].data));
		});
		pkg.getFunction("core.real32._add").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)+((float)args[1].data));
		});
		
		pkg.getFunction("core.int._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)-((int)args[1].data));
		});
		pkg.getFunction("core.int64._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((long)args[0].data)-((long)args[1].data));
		});
		pkg.getFunction("core.int32._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)-((int)args[1].data));
		});
		pkg.getFunction("core.int16._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((short)args[0].data)-((short)args[1].data));
		});
		pkg.getFunction("core.int8._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((byte)args[0].data)-((byte)args[1].data));
		});
		pkg.getFunction("core.real._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)-((float)args[1].data));
		});
		pkg.getFunction("core.real64._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((double)args[0].data)-((double)args[1].data));
		});
		pkg.getFunction("core.real32._sub").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)-((float)args[1].data));
		});
		
		pkg.getFunction("core.int._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)*((int)args[1].data));
		});
		pkg.getFunction("core.int64._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((long)args[0].data)*((long)args[1].data));
		});
		pkg.getFunction("core.int32._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)*((int)args[1].data));
		});
		pkg.getFunction("core.int16._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((short)args[0].data)*((short)args[1].data));
		});
		pkg.getFunction("core.int8._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((byte)args[0].data)*((byte)args[1].data));
		});
		pkg.getFunction("core.real._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)*((float)args[1].data));
		});
		pkg.getFunction("core.real64._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((double)args[0].data)*((double)args[1].data));
		});
		pkg.getFunction("core.real32._mul").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)*((float)args[1].data));
		});
		
		pkg.getFunction("core.int._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)/((int)args[1].data));
		});
		pkg.getFunction("core.int64._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((long)args[0].data)/((long)args[1].data));
		});
		pkg.getFunction("core.int32._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)/((int)args[1].data));
		});
		pkg.getFunction("core.int16._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((short)args[0].data)/((short)args[1].data));
		});
		pkg.getFunction("core.int8._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((byte)args[0].data)/((byte)args[1].data));
		});
		pkg.getFunction("core.real._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)/((float)args[1].data));
		});
		pkg.getFunction("core.real64._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((double)args[0].data)/((double)args[1].data));
		});
		pkg.getFunction("core.real32._div").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)/((float)args[1].data));
		});
		
		pkg.getFunction("core.int._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)%((int)args[1].data));
		});
		pkg.getFunction("core.int64._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((long)args[0].data)%((long)args[1].data));
		});
		pkg.getFunction("core.int32._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((int)args[0].data)%((int)args[1].data));
		});
		pkg.getFunction("core.int16._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((short)args[0].data)%((short)args[1].data));
		});
		pkg.getFunction("core.int8._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((byte)args[0].data)%((byte)args[1].data));
		});
		pkg.getFunction("core.real._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)%((float)args[1].data));
		});
		pkg.getFunction("core.real64._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((double)args[0].data)%((double)args[1].data));
		});
		pkg.getFunction("core.real32._mod").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, ((float)args[0].data)%((float)args[1].data));
		});
		
		pkg.getFunction("core.int._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, (int) Math.pow(((int)args[0].data),((int)args[1].data)));
		});
		pkg.getFunction("core.int64._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, (long) Math.pow(((long)args[0].data),((long)args[1].data)));
		});
		pkg.getFunction("core.int32._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, (int) Math.pow(((int)args[0].data),((int)args[1].data)));
		});
		pkg.getFunction("core.int16._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, (short) Math.pow((short)args[0].data,((short)args[1].data)));
		});
		pkg.getFunction("core.int8._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, (byte) Math.pow(((byte)args[0].data),((byte)args[1].data)));
		});
		pkg.getFunction("core.real._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, (float) Math.pow(((float)args[0].data),((float)args[1].data)));
		});
		pkg.getFunction("core.real64._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, Math.pow(((double)args[0].data),((double)args[1].data)));
		});
		pkg.getFunction("core.real32._pow").data.put("onRun",(CustomFunction) (vm, args) -> {
			return new SourceObject(args[0].type, (float) Math.pow(((float)args[0].data),((float)args[1].data)));
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
