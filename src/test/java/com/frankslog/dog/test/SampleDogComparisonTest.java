package com.frankslog.dog.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frankslog.json.comparison.JSONComparator;
import com.frankslog.json.comparison.JSONComparisonResult;
import com.frankslog.json.comparison.difference.asserts.OnlyDifferentValueOnFields;
import com.frankslog.json.comparison.util.file.FileContentReader;

public class SampleDogComparisonTest {

    @Test
    public void compareEqualDogsTest() {
        String actualDog = FileContentReader.readFromFile("dogs/get/actualDog.json");
        String expectedDog = FileContentReader.readFromFile("dogs/get/expectedEqualDog.json");
        JSONComparisonResult result = JSONComparator.compare(expectedDog, actualDog);
        Assert.assertFalse(result.areEqual());
        OnlyDifferentValueOnFields.check(result, ".lastReadTime");
    }

    @Test
    public void compareDifferentDogsTest() {
        String actualDog = FileContentReader.readFromFile("dogs/get/actualDog.json");
        String expectedDog = FileContentReader.readFromFile("dogs/get/expectedDifferentDog.json");
        JSONComparisonResult result = JSONComparator.compare(expectedDog, actualDog);
        Assert.assertFalse(result.areEqual());
        OnlyDifferentValueOnFields.check(result, ".lastReadTime");
    }

}
