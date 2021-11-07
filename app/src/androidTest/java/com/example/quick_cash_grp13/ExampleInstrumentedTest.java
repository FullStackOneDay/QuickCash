package com.example.quick_cash_grp13;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.doesNotHaveFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocused;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quick_cash_grp13", appContext.getPackageName());
    }

    /**
     * check if logging in with empty email gives appropriate error message
     */
    @Test
    public void checkLoginWithEmptyEmail() {
        onView(withId(R.id.loginEmail)).perform(typeText(""));
        onView(withId(R.id.loginPassword)).perform(typeText("xyz123")).perform(closeSoftKeyboard());
        onView(withId(R.id.loginCheck)).perform(click());
        onView(withId(R.id.errMsg)).check(matches(withText("Email field required")));
    }

    /**
     * check if logging in with an invalid email address returns appropriate error message
     */
    @Test
    public void checkLoginWithInvalidEmail() {
        onView(withId(R.id.loginEmail)).perform(typeText("abc123.gmail.com"));
        onView(withId(R.id.loginPassword)).perform(typeText("xyz123")).perform(closeSoftKeyboard());
        onView(withId(R.id.loginCheck)).perform(click());
        onView(withId(R.id.errMsg)).check(matches(withText("Invalid email address")));
    }

    /**
     * check if logging in with correct and valid credentials proceeds to the next activity
     */
    @Test
    public void checkLoginWithCorrectCredentials() {
        onView(withId(R.id.loginEmail)).perform(typeText("abc123@gmail.com"));
        onView(withId(R.id.loginPassword)).perform(typeText("xyz123")).perform(closeSoftKeyboard());
        onView(withId(R.id.loginCheck)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(not(isFocused())));
    }

    /**
     * check if registering with an empty email returns appropriate error message
     */
    @Test
    public void checkRegistrationWithEmptyEmail() {
        onView(withId(R.id.loginEmail)).perform(typeText(""));
        onView(withId(R.id.loginPassword)).perform(typeText("xyz123")).perform(closeSoftKeyboard());
        onView(withId(R.id.registerCheck)).perform(click());
        onView(withId(R.id.errMsg)).check(matches(withText("Email field required")));
    }

    /**
     * check if registering with an invalid email address returns appropriate error message
     */
    @Test
    public void checkRegistrationWithInvalidEmail() {
        onView(withId(R.id.loginEmail)).perform(typeText("abc123.gmail.com"));
        onView(withId(R.id.loginPassword)).perform(typeText("xyz123")).perform(closeSoftKeyboard());
        onView(withId(R.id.registerCheck)).perform(click());
        onView(withId(R.id.errMsg)).check(matches(withText("Invalid email address")));
    }




    @Test
    public void checkIfRegistrationPageIsVisible() {
        onView(withId(R.id.textView)).check(matches(withText(R.string.quick_cash_login)));
        onView(withId(R.id.loginEmail)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.loginEmail)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.loginCheck)).check(matches(withText(R.string.login)));
        onView(withId(R.id.registerCheck)).check(matches(withText(R.string.register)));
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

}