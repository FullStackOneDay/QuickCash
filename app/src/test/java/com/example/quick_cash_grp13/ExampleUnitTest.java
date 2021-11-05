package com.example.quick_cash_grp13;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    MainActivity mainActivity;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        mainActivity = new MainActivity();
    }

    @After
    public void tearDown(){
        System.gc();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    /**
     * check for empty email
     */
    @Test
    public void checkEmailIsEmpty() {
        assertTrue(mainActivity.isEmailEmpty(""));
    }

    /**
     * check for nonempty email
     */
    @Test
    public void checkEmailIsNotEmpty() {
        assertFalse(mainActivity.isEmailEmpty("qwerty123@dal.ca"));
    }

    /**
     * check for valid email
     */
    @Test
    public void checkEmailIsValid() {
        assertTrue(mainActivity.isEmailValid("qwerty123@dal.ca"));
    }

    /**
     * check for invalid email
     */
    @Test
    public void checkEmailIsInvalid() {
        assertFalse(mainActivity.isEmailValid("abc123gmail.com"));
    }

}