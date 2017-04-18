package com.frankslog.json.comparison.difference;

import com.frankslog.json.comparison.difference.asserts.Assert;


public class DifferentValue
    implements JSONDifference {

    private Object key, expected, actual;

    public static DifferentValue create(Object key, Object expected, Object actual) {
        Assert.assertNotNull(key, "key must not be null.");
        Assert.assertNotNull(expected, "expected must not be null.");
        Assert.assertNotNull(actual, "actual must not be null.");

        DifferentValue difference = new DifferentValue();
        difference.setKey(key);
        difference.setExpected(expected);
        difference.setActual(actual);
        return difference;
    }

    public Object getExpected() {
        return this.expected;
    }

    public void setExpected(Object expected) {
        this.expected = expected;
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
        return "DIFFERENTE VALUE ON KEY: " + this.key.toString() + " EXPECTED: " + this.print(this.expected) + " ACTUAL: "
            + this.print(this.actual);
    }

    private String print(Object object) {
        if (object instanceof String) {
            return "\"" + object + "\"";
        } else {
            return object.toString();
        }
    }

}
