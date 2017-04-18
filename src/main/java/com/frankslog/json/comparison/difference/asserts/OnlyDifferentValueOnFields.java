package com.frankslog.json.comparison.difference.asserts;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.frankslog.json.comparison.JSONComparisonResult;
import com.frankslog.json.comparison.difference.DifferentValue;
import com.frankslog.json.comparison.difference.JSONDifference;


public class OnlyDifferentValueOnFields {
	
	public static boolean check(JSONComparisonResult result, String... fieldPaths) {
		final Set<String> fieldPathsSet = Arrays.stream(fieldPaths).collect(Collectors.toSet());
		
		for (JSONDifference difference : result.getDifferences()) {
			if (difference instanceof DifferentValue) {
				isContainedInPaths(fieldPathsSet, (DifferentValue) difference);
			} else {
				Assert.fail(difference.getMessage());
			}
		}
		return true;
	}
	
	private static void isContainedInPaths(Set<String> fieldPathsSet, DifferentValue difference) {
		for (String fieldPath : fieldPathsSet) {
			if (haveSamePath(fieldPath, difference)) {
				return;
			}
		}
		Assert.fail(difference.getMessage());
	}
	
	private static boolean haveSamePath(String fieldPath, DifferentValue difference) {
		if (fieldPath.endsWith("*")) {
			return difference.getKey().toString().startsWith(fieldPath.substring(0, fieldPath.length() - 2));
		} else if (fieldPath.startsWith("*")) {
			return difference.getKey().toString().endsWith(fieldPath.substring(1));
		} else {
			return difference.getKey().toString().equals(fieldPath);
		}
	}
	
}
