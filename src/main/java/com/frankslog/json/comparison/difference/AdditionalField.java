package com.frankslog.json.comparison.difference;

import com.frankslog.json.comparison.difference.asserts.Assert;


public class AdditionalField implements JSONDifference {
	
	private Object key, actual;
	
	public static AdditionalField create(Object key, Object actual) {
		Assert.assertNotNull(key, "key must not be null.");
		Assert.assertNotNull(actual, "actual must not be null.");
		AdditionalField difference = new AdditionalField();
		difference.setKey(key);
		difference.setActual(actual);
		return difference;
	}
	
	public Object getActual() {
		return this.actual;
	}
	
	public void setActual(Object actual) {
		this.actual = actual;
	}
	
	public Object getKey() {
		return this.key;
	}
	
	public void setKey(Object key) {
		this.key = key;
	}
	
	public String getMessage() {
		return "NEW FIELD FOUND ON KEY: " + this.key.toString() + " VALUE: " + this.actual.toString();
	}
	
}
