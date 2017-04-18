package com.frankslog.json.comparison.sharing;

import java.util.HashMap;
import java.util.Map;

import com.frankslog.json.comparison.difference.asserts.Assert;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JSONSharing {
	
	private static final Map<String, JSONObject> SHARED = new HashMap<>();
	
	public static void share(String testName, String key, JSONObject jsonObject) {
		SHARED.put(testName + "-" + key, jsonObject);
	}
	
	public static void share(String testName, String key, String jsonString) {
		SHARED.put(testName + "-" + key, (JSONObject) JSONSerializer.toJSON(jsonString));
	}
	
	public static JSONObject getExisting(String testName, String key) {
		JSONObject result = get(testName, key);
		Assert.assertNotNull(result, "Shared key not found :" + testName + "-" + key);
		return result;
	}
	
	public static JSONObject get(String testName, String key) {
		return SHARED.get(testName + "-" + key);
	}
	
}
