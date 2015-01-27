package com.iconmaster.sbcore.exception;

import com.iconmaster.source.util.Range;

/**
 *
 * @author iconmaster
 */
public class SBError {
	public Range range;
	public String message;

	public SBError(Range range, String message) {
		this.range = range;
		this.message = message;
	}
}
