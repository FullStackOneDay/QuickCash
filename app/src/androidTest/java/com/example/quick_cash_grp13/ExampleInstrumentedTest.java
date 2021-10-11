package com.example.quick_cash_grp13;
import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quick_cash_grp13", appContext.getPackageName());
    }

    @Test
    public void checkIfRegistrationPageIsVisible() {
        onView(withId(R.id.textView)).check(matches(withText(R.string.quick_cash_login)));
        onView(withId(R.id.loginUserName)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.loginUserName)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.loginCheck)).check(matches(withText(R.string.login)));
        onView(withId(R.id.registerCheck)).check(matches(withText(R.string.register)));
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

}