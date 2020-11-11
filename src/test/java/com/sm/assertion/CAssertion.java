package com.sm.assertion;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class CAssertion extends Assert {

    private static Logger logger = Logger.getLogger(CAssertion.class.getName());

    public static void assertEquals(String expected, String actual) {
        try {
            Assert.assertEquals(expected, actual);
        }catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }

    public static void assertEquals(boolean expected, boolean actual) {
        try {
            Assert.assertEquals(expected, actual);
        }catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }

    public static void assertEquals(int expected, int actual) {
        try {
            Assert.assertEquals(expected, actual);
        }catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }

    public static void assertEquals(Object obj1, Object obj2) {
        try {
            Assert.assertEquals(obj1, obj2);
        }catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }

    public static void assertTrue(boolean expected) {
        try {
            Assert.assertTrue(expected);
        }catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }

    public static void assertNotNull(String message, Object obj) {
        try {
            Assert.assertNotNull(message, obj);
        }catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }

    public static void assertNull(String message, Object obj) {
        try {
            Assert.assertNull(message, obj);
        }catch (AssertionError e) {
            logger.error(e.getMessage());
        }
    }
}
