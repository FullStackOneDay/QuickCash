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

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*static MainActivity mainActivity;

    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    *//**
     * check for empty email
     *//*
    @Test
    public void checkEmailIsEmpty() {
        assertTrue(mainActivity.isEmailEmpty(""));
    }

    *//**
     * check for nonempty email
     *//*
    @Test
    public void checkEmailIsNotEmpty() {
        assertFalse(mainActivity.isEmailEmpty("qwerty123@dal.ca"));
    }

    *//**
     * check for valid email
     *//*
    @Test
    public void checkEmailIsValid() {
        assertTrue(mainActivity.isEmailValid("qwerty123@dal.ca"));
    }

    *//**
     * check for invalid email
     *//*
    @Test
    public void checkEmailIsInvalid() {
        assertFalse(mainActivity.isEmailValid("abc123gmail.com"));
    }
*/
    @Test
    public void checkNotificationNameIsNotEmpty() {
        assertNotEquals(CreateNotificationChannel.getNotificationName(), "");
    }

    @Test
    public void checkNotificationIdIsNotEmpty() {
        assertNotEquals(CreateNotificationChannel.getNotificationId(), "");
    }

    @Test
    public void checkNotificationImportanceIsNotEmpty() {
        assertNotEquals(CreateNotificationChannel.getNotificationImportance(), -1);
    }

}