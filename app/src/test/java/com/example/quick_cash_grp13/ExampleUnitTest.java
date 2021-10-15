package com.example.quick_cash_grp13;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    static MainActivity mainActivity;

    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkEmailIsEmpty() {
        assertTrue(mainActivity.isEmailEmpty(""));
    }

    @Test
    public void checkEmailIsNotEmpty() {
        assertFalse(mainActivity.isEmailEmpty("qwerty123@dal.ca"));
    }

    @Test
    public void checkEmailIsValid() {
        assertTrue(mainActivity.isEmailValid("qwerty123@dal.ca"));
    }

    @Test
    public void checkEmailIsInvalid() {
        assertFalse(mainActivity.isEmailValid("abc123gmail.com"));
    }

}