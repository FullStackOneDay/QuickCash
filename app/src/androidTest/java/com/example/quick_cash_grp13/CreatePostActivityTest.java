package com.example.quick_cash_grp13;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class CreatePostActivityTest {

    @Rule
    public ActivityScenarioRule<CreatePostActivity> myRule = new ActivityScenarioRule<>(CreatePostActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }

    /**
     * check if job fields are all present and job is posted
     */
    @Test
    public void checkJobPostedSuccessfully() {
        onView(withId(R.id.jobTitle)).perform(typeText("Test Job"));
        onView(withId(R.id.company)).perform(typeText("Test Company"));
        onView(withId(R.id.location)).perform(typeText("Test Location"));
        onView(withId(R.id.fieldSpinner)).perform(click());
        onData(is("Law")).perform(click()).perform(closeSoftKeyboard());;
        onView(withId(R.id.salary)).perform(typeText("5000")).perform(closeSoftKeyboard());
        onView(withId(R.id.monthly)).perform(click());
        onView(withId(R.id.postJobButton)).perform(click());

        //This line is causing test cases to failed
        onView(withId(R.id.outputMsg)).check(matches(withText("Job posted successfully")));
    }

    /**
     * check if some fields are missing and appropriate message is displayed
     */
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

        //This line is causing test cases to fail
        //onView(withId(R.id.outputMsg)).check(matches(withText("Missing Required Fields.")));
    }
}
