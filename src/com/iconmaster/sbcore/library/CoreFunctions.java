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
			if (args.length==0 || args[0]==null || args[0].data==null) {return null;}
			if (args.length==0 || args[0].data==null) {return null;}
			PrintWriter pw = new PrintWriter(vm.outputStream);
			pw.println(args[0].data);
			pw.flush();
			return null;
		});

		{
			Function fn = pkg.getFunction("core.int._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)+((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)+((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)+((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)+((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)+((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)+((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)+((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._add");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)+((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)-((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)-((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)-((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)-((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)-((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)-((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)-((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._sub");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)-((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)*((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)*((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)*((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)*((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)*((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)*((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)*((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._mul");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)*((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)/((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)/((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)/((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)/((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)/((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)/((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)/((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._div");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)/((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)%((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)%((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)%((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)%((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)%((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)%((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)%((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._mod");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)%((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (int) Math.pow(((int)args[0].data),((int)args[1].data)));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (byte) Math.pow(((byte)args[0].data),((byte)args[1].data)));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (short) Math.pow(((short)args[0].data),((short)args[1].data)));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (int) Math.pow(((int)args[0].data),((int)args[1].data)));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (long) Math.pow(((long)args[0].data),((long)args[1].data)));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (float) Math.pow(((float)args[0].data),((float)args[1].data)));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (float) Math.pow(((float)args[0].data),((float)args[1].data)));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._pow");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, (float) Math.pow(((double)args[0].data),((double)args[1].data)));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)==((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)==((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)==((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)==((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)==((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)==((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)==((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._eq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)==((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)!=((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)!=((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)!=((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)!=((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)!=((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)!=((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)!=((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._neq");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)!=((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)<((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)<((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)<((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)<((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)<((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)<((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)<((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._lt");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)<((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)>((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)>((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)>((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)>((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)>((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)>((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.bool._and");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((boolean)args[0].data)&&((boolean)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.bool._or");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((boolean)args[0].data)&&((boolean)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.bool._not");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, !((boolean)args[0].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)<=((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)<=((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)<=((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)<=((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)<=((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)<=((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)<=((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._le");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)<=((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>=((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)>=((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)>=((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>=((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)>=((long)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)>=((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real32._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((float)args[0].data)>=((float)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.real64._ge");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((double)args[0].data)>=((double)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._band");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)&((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._band");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)&((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._band");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)&((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._band");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)&((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._band");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)&((long)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._bor");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)|((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._bor");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)|((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._bor");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)|((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._bor");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)|((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._bor");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)|((long)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._sll");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)<<((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._sll");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)<<((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._sll");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)<<((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._sll");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)<<((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._sll");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)<<((long)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._srl");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>>((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._srl");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)>>((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._srl");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)>>((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._srl");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>>((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._srl");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)>>((long)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._sra");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>>>((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._sra");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((byte)args[0].data)>>>((byte)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._sra");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((short)args[0].data)>>>((short)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._sra");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((int)args[0].data)>>>((int)args[1].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._sra");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ((long)args[0].data)>>>((long)args[1].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.int._bnot");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ~((int)args[0].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int8._bnot");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ~((byte)args[0].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int16._bnot");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ~((short)args[0].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int32._bnot");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ~((int)args[0].data));
			});
		}
		{
			Function fn = pkg.getFunction("core.int64._bnot");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(args[0].type, ~((long)args[0].data));
			});
		}
		
		{
			Function fn = pkg.getFunction("core.?._concat");
			fn.data.put("onRun",(CustomFunction) (vm, args) -> {
				if (!ExecUtils.checkType(vm, fn, args)) {return null;}
				return new SourceObject(TypeDef.STRING, ""+args[0].data+args[1].data);
			});
		}
		
		for (TypeDef type : LibraryCore.MATH_TYPES) {
			ArrayList<Function> fns = pkg.getFunctions("core."+type.name+"._cast");
			for (Function fn : fns) {
				if (type==TypeDef.INT || type==TypeDef.INT32) {
					fn.data.put("onRun",(CustomFunction) (vm, args) -> {
						if (!ExecUtils.checkType(vm, fn, args)) {return null;}
						return new SourceObject(type, ((Number)args[0].data).intValue());
					});
				} else if (type==TypeDef.INT8) {
					fn.data.put("onRun",(CustomFunction) (vm, args) -> {
						if (!ExecUtils.checkType(vm, fn, args)) {return null;}
						return new SourceObject(type, ((Number)args[0].data).byteValue());
					});
				} else if (type==TypeDef.INT16) {
					fn.data.put("onRun",(CustomFunction) (vm, args) -> {
						if (!ExecUtils.checkType(vm, fn, args)) {return null;}
						return new SourceObject(type, ((Number)args[0].data).shortValue());
					});
				} else if (type==TypeDef.INT64) {
					fn.data.put("onRun",(CustomFunction) (vm, args) -> {
						if (!ExecUtils.checkType(vm, fn, args)) {return null;}
						return new SourceObject(type, ((Number)args[0].data).longValue());
					});
				} else if (type==TypeDef.REAL || type==TypeDef.REAL32) {
					fn.data.put("onRun",(CustomFunction) (vm, args) -> {
						if (!ExecUtils.checkType(vm, fn, args)) {return null;}
						return new SourceObject(type, ((Number)args[0].data).floatValue());
					});
				} else if (type==TypeDef.REAL64) {
					fn.data.put("onRun",(CustomFunction) (vm, args) -> {
						if (!ExecUtils.checkType(vm, fn, args)) {return null;}
						return new SourceObject(type, ((Number)args[0].data).doubleValue());
					});
				}
			}
		}
		
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
