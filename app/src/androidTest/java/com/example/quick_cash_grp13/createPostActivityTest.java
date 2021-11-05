package com.example.quick_cash_grp13;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import android.content.Context;
import android.content.Intent;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class createPostActivityTest {

    @Rule
    public ActivityScenarioRule<createPostActivity> myRule = new ActivityScenarioRule<>(createPostActivity.class);

    @Before
    public void setup(){
        Intents.init();
    }

    @After
    public void tearDown(){
        System.gc();
    }

    @Test
    public void checkJobPostedSuccessfully() {
        onView(withId(R.id.jobTitle)).perform(typeText("Test Job"));
        onView(withId(R.id.company)).perform(typeText("Test Company"));
        onView(withId(R.id.location)).perform(typeText("Test Location"));
        onView(withId(R.id.fieldSpinner)).perform(click());
        onData(is("Law")).perform(click());
        onView(withId(R.id.salary)).perform(typeText("5000")).perform(closeSoftKeyboard());
        onView(withId(R.id.monthly)).perform(click());
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.outputMsg)).check(matches(withText("Job posted successfully!")));
    }

    @Test
    public void checkJobPostedWithError() {
        onView(withId(R.id.jobTitle)).perform(typeText(""));
        onView(withId(R.id.company)).perform(typeText(""));
        onView(withId(R.id.location)).perform(typeText(""));
        onView(withId(R.id.fieldSpinner)).perform(click());
        onData(is("Law")).perform(click());
        onView(withId(R.id.salary)).perform(typeText("5000")).perform(closeSoftKeyboard());
        onView(withId(R.id.monthly)).perform(click());
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.outputMsg)).check(matches(withText("Missing Required Fields.")));
    }

    /*@Test
    public void checkSpinnerItems(){
        for (int i = 0; i < fields.length; i++) {
            onView(withId(R.id.fieldSpinner)).perform(click());
            onData(is(fields[i])).check(matches(withText(fields[i])));
        }
    }*/
}
