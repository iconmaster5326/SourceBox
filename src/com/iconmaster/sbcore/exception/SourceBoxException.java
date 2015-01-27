
package com.iconmaster.sbcore.exception;

import com.iconmaster.source.util.Range;

/**
 *
 * @author iconmaster
 */
public class SourceBoxException extends Exception {
	private final Range range;
	
	public SourceBoxException(Range range) {
		super(range.toString()+": Unknown SourceBox exception");
		this.range = range;
	}
	
	public SourceBoxException(Range range, String message) {
		super((range==null?"null: ":range.toString()+": ")+message);
		this.range = range;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public Range getRange() {
		return range;
	}
}
