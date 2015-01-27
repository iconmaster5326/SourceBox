package com.iconmaster.sbcore.exception;

import com.iconmaster.source.compile.DataType;
import com.iconmaster.source.util.Range;

/**
 *
 * @author iconmaster
 */
public class DataTypeError extends SBError {
	public DataTypeError(Range range, DataType expected, DataType got) {
		super(range,"Incorrect data types: Expected "+expected+", got "+got);
	}
}
