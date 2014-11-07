package com.iconmaster.srcbox;

/**
 *
 * @author iconmaster
 */
public class BoxField {
	public String name;
	public Object value;

	public BoxField(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return name+"="+value;
	}
}
