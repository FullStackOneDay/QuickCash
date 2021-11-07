package com.example.quick_cash_grp13;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Not;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;

import android.content.Context;

import androidx.core.app.NotificationCompat;

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

    /*
     * JUnit Tests for Notifications
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

    /*
     * Mockito Tests for Notifications
     */

    @Mock
    Context mockContext;

    public static NotificationCompat.Builder build1 = null;
    public static NotificationCompat.Builder build2 = null;

    @BeforeClass
    public static void setup() {
        build1 = Mockito.mock(NotificationCompat.Builder.class);
        Mockito.when(build1.getPriority()).thenReturn(NotificationCompat.PRIORITY_DEFAULT);
        Mockito.when(build1.getColor()).thenReturn(NotificationCompat.COLOR_DEFAULT);

        build2 = Mockito.mock(NotificationCompat.Builder.class);
        Mockito.when(build1.getPriority()).thenReturn(0);
        Mockito.when(build1.getColor()).thenReturn(13567);
    }

    @Test
    public void testNotificationPriority() {
        assertEquals(NotificationCompat.PRIORITY_DEFAULT, build1.getPriority());
        assertEquals(0, build2.getPriority());
    }

    @Test
    public void testNotificationColor() {
        assertEquals(NotificationCompat.COLOR_DEFAULT, build1.getColor());
        assertEquals(13567, build2.getColor());
    }

}