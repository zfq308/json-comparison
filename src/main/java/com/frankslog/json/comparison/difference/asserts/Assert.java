package com.frankslog.json.comparison.difference.asserts;

public class Assert {
	
	public static void fail(String message) {
		throwAssertionError(message);
	}
	
	public static void assertTrue(boolean condition, String message) {
		if (!condition) {
			throwAssertionError(message);
		}
	}
	
	public static void assertNotNull(Object object, String message) {
		if (object == null) {
			throwAssertionError(message);
		}
	}
	
	private static void throwAssertionError(String message) throws AssertionError {
		throw new AssertionError(message);
	}
	
}