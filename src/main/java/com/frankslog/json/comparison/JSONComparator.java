package com.frankslog.json.comparison;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frankslog.json.comparison.difference.AdditionalField;
import com.frankslog.json.comparison.difference.DifferentSize;
import com.frankslog.json.comparison.difference.DifferentValue;
import com.frankslog.json.comparison.difference.MissingField;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;


public class JSONComparator {
	
	private static Logger LOGGER = LoggerFactory.getLogger(JSONComparator.class);
	
	public static JSONComparisonResult compare(JSON expected, JSON actual, Object key, JSONComparisonResult result) {
		if (expected == actual) {
			return result;
		}
		if (expected == null) {
			result.addDifference(AdditionalField.create(key, actual));
			return result;
		}
		if (actual == null) {
			result.addDifference(MissingField.create(key, expected));
			return result;
		}
		if (expected == actual || expected.equals(actual)) {
			return result;
		}
		
		if (expected instanceof JSONArray) {
			if (actual instanceof JSONArray) {
				compare((JSONArray) expected, (JSONArray) actual, key, result);
			} else {
				result.addDifference(DifferentValue.create(key, expected, actual));
				return result;
			}
		} else if (expected instanceof JSONObject) {
			if (actual instanceof JSONObject) {
				compare((JSONObject) expected, (JSONObject) actual, key, result);
			} else {
				result.addDifference(DifferentValue.create(key, expected, actual));
				return result;
			}
		} else if (expected instanceof JSONNull) {
			if (actual instanceof JSONNull) {
				return result;
			} else {
				result.addDifference(AdditionalField.create(key, actual));
				return result;
			}
		}
		return result;
	}
	
	public static JSONComparisonResult compare(JSONArray expected, JSONArray actual, Object key, JSONComparisonResult result) {
		if (expected == actual) {
			return result;
		}
		if (expected == null) {
			result.addDifference(AdditionalField.create(key, actual));
			return result;
		}
		if (actual == null) {
			result.addDifference(MissingField.create(key, expected));
			return result;
		}
		if (expected == actual || expected.equals(actual)) {
			return result;
		}
		
		if (actual.size() != expected.size()) {
			result.addDifference(DifferentSize.create(key, expected.size(), actual.size()));
			return result;
		}
		
		int max = expected.size();
		for (int i = 0; i < max; i++) {
			Object o1 = expected.get(i);
			Object o2 = actual.get(i);
			
			compare(o1, o2, key.toString() + "." + i, result);
		}
		return result;
	}
	
	private static JSONComparisonResult compare(Object o1, Object o2, Object key, JSONComparisonResult result) {
		if (o1 instanceof JSON && o2 instanceof JSON) {
			compare((JSON) o1, (JSON) o2, key, result);
		} else if (!o1.equals(o2)) {
			result.addDifference(DifferentValue.create(key, o1, o2));
		}
		return result;
	}
	
	public static JSONComparisonResult compare(JSONObject expected, JSONObject actual, Object key, JSONComparisonResult result) {
		if (expected == actual) {
			return result;
		}
		if (expected == null) {
			result.addDifference(AdditionalField.create(key, actual));
			return result;
		}
		if (actual == null) {
			result.addDifference(MissingField.create(key, expected));
			return result;
		}
		
		if (expected.isNullObject()) {
			if (actual.isNullObject()) {
				return result;
			} else {
				result.addDifference(AdditionalField.create(key, actual));
				return result;
			}
		} else if (actual.isNullObject()) {
			result.addDifference(MissingField.create(key, expected));
			return result;
		}
		
		Collection<String> keys = new HashSet(expected.keySet());
		keys.addAll(actual.keySet());
		
		for (String subkey : keys) {
			Object o1 = expected.opt(subkey);
			Object o2 = actual.opt(subkey);
			
			if (JSONNull.getInstance().equals(o1)) {
				if (JSONNull.getInstance().equals(o2)) {
					if (expected.containsKey(subkey) && !actual.containsKey(subkey)) {
						result.addDifference(MissingField.create(key + "." + subkey, o1));
					} else if (!expected.containsKey(subkey) && actual.containsKey(subkey)) {
						result.addDifference(AdditionalField.create(key + "." + subkey, o2));
					}
				} else {
					if (expected.containsKey(subkey)) {
						result.addDifference(DifferentValue.create(key + "." + subkey, o1, o2));
					} else {
						result.addDifference(AdditionalField.create(key + "." + subkey, o2));
					}
				}
			} else if (JSONNull.getInstance().equals(o2)) {
				if (actual.containsKey(subkey)) {
					result.addDifference(DifferentValue.create(key + "." + subkey, o1, o2));
				} else {
					result.addDifference(MissingField.create(key + "." + subkey, o1));
				}
			} else {
				compare(o1, o2, key + "." + subkey, result);
			}
		}
		return result;
	}
	
	public static JSONComparisonResult compare(String expected, String actual) {
		JsonConfig config = new JsonConfig();
		config.setAllowNonStringKeys(true);
		JSONComparisonResult result = new JSONComparisonResult();
		compare(JSONSerializer.toJSON(expected, config), JSONSerializer.toJSON(actual, config), "", result);
		result.getDifferences().stream().forEach(d -> LOGGER.info(d.getMessage()));
		return result;
	}
}
