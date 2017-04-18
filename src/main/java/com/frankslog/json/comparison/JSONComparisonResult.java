package com.frankslog.json.comparison;

import java.util.ArrayList;
import java.util.List;

import com.frankslog.json.comparison.difference.JSONDifference;


public class JSONComparisonResult {
	
	private boolean equal = true;
	private List<JSONDifference> differences = new ArrayList<JSONDifference>();
	
	public void addDifference(JSONDifference difference) {
		this.differences.add(difference);
		this.setEqual(false);
	}
	
	public void setEqual(boolean equal) {
		this.equal = equal;
	}
	
	public boolean areEqual() {
		return this.equal;
	}
	
	public List<JSONDifference> getDifferences() {
		return this.differences;
	}
	
	public void setDifferences(List<JSONDifference> differences) {
		this.differences = differences;
	}
	
}
