package com.frankslog.json.comparison.difference;

import com.frankslog.json.comparison.difference.asserts.Assert;


public class MissingField implements JSONDifference {
	
	private Object key, expected;
	
	public static MissingField create(Object key, Object expected) {
		Assert.assertNotNull(key, "key must not be null.");
		Assert.assertNotNull(expected, "expected must not be null.");
		MissingField difference = new MissingField();
		difference.setKey(key);
		difference.setExpected(expected);
		return difference;
	}
	
	public Object getExpected() {
		return this.expected;
	}
	
	public void setExpected(Object expected) {
		this.expected = expected;
	}
	
	public Object getKey() {
		return this.key;
	}
	
	public void setKey(Object key) {
		this.key = key;
	}
	
	public String getMessage() {
		return "MISSING FIELD ON KEY: " + this.key.toString() + " EXPECTED: " + this.expected.toString();
	}
	
}
