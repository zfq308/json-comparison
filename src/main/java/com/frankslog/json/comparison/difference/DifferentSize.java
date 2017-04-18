package com.frankslog.json.comparison.difference;

import com.frankslog.json.comparison.difference.asserts.Assert;


public class DifferentSize implements JSONDifference {
	
	private Object key;
	private int expectedSize, actualSize;
	
	public static DifferentSize create(Object key, int expectedSize, int actualSize) {
		Assert.assertNotNull(key, "key must not be null.");
		DifferentSize difference = new DifferentSize();
		difference.setKey(key);
		difference.setExpectedSize(expectedSize);
		difference.setActualSize(actualSize);
		return difference;
	}
	
	public Object getKey() {
		return this.key;
	}
	
	public void setKey(Object key) {
		this.key = key;
	}
	
	public String getMessage() {
		return "DIFFERENT SIZE FOR COLLECTION ON KEY: " + this.key.toString() + " EXPECTED SIZE: " + this.expectedSize + " ACTUAL SIZE: " + this.actualSize;
	}
	
	public int getExpectedSize() {
		return this.expectedSize;
	}
	
	public void setExpectedSize(int expectedSize) {
		this.expectedSize = expectedSize;
	}
	
	public int getActualSize() {
		return this.actualSize;
	}
	
	public void setActualSize(int actualSize) {
		this.actualSize = actualSize;
	}
	
}
