package com.iconmaster.sbcore.execute;

import com.iconmaster.source.compile.DataType;
import com.iconmaster.source.prototype.TypeDef;

/**
 *
 * @author iconmaster
 */
public class SourceObject {
	public DataType type;
	public Object data;

	public SourceObject(DataType type, Object data) {
		this.type = type;
		this.data = data;
	}
	
	public SourceObject(TypeDef type, Object data) {
		this.type = new DataType(type);
		this.data = data;
	}

	@Override
	public String toString() {
		return "<"+type+"> "+data;
	}
}
