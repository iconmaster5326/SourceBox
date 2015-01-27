package com.iconmaster.sbcore.execute;

import com.iconmaster.sbcore.exception.DataTypeError;
import com.iconmaster.source.compile.DataType;
import com.iconmaster.source.prototype.Field;
import com.iconmaster.source.prototype.Function;

/**
 *
 * @author iconmaster
 */
public class ExecUtils {
	public static boolean checkType(VirtualMachine vm, Function fn, SourceObject[] got) {
		if (fn.getArguments().size()!=got.length) {
			return false;
		}
		int i=0;
		for (Field f : fn.getArguments()) {
			SourceObject ob = got[i];
			if (!DataType.canCastTo(f.getType(), ob.type)) {
				vm.error = new DataTypeError(null, f.getType(), ob.type);
				return false;
			}
			i++;
		}
		return true;
	}
}
