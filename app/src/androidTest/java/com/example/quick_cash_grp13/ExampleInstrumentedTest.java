package com.example.quick_cash_grp13;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quick_cash_grp13", appContext.getPackageName());
    }

    static SearchJob searchJob;
    Job job1= new Job("Chocolate Shop Clerk","Leonidas","Sales","Halifax",12.95);
    Job job2= new Job("Chocolate Lovers Wanted","Lindt & Sprüngli","Sales","Kanata");
    Job job3= new Job("Respiratory Therapists (RRTs) ","Switch Health","Health and medicine","Halifax");
    Job job4= new Job("Image Processing / Computer Vision Engineer (Internship)","iClassifier Inc","Science and technology","Waterloo",4000);


    // Search Test
    public void ini(){
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        jobs.add(job4);
        searchJob = new SearchJob(jobs);
    }

    @Test
    public void searchTitleTest(){
        ini();

        ArrayList<Job> jobTitle1 = new ArrayList<>();
        jobTitle1.add(job1);

        ArrayList<Job> jobTitle2 = new ArrayList<>();
        jobTitle2.add(job1);
        jobTitle2.add(job2);

        ArrayList<Job> jobTitle3 = new ArrayList<>();
        jobTitle3.add(job3);

        ArrayList<Job> jobTitle4 = new ArrayList<>();
        jobTitle4.add(job4);

        assertEquals(jobTitle1,searchJob.searchByTitle("Chocolate Shop Clerk"));// must pass
        assertEquals(jobTitle1,searchJob.searchByTitle("chocolate shop Clerk"));// must pass
        assertEquals(jobTitle2,searchJob.searchByTitle("Chocolate"));
        assertEquals(jobTitle3,searchJob.searchByTitle("Respiratory Therapists"));
        assertEquals(jobTitle4,searchJob.searchByTitle(" Computer Vision Engineer"));

    }

    public void searchCompany(){
        ini();
        ArrayList<Job> jobCom1 = new ArrayList<>();
        jobCom1.add(job1);

        ArrayList<Job> jobCom2 = new ArrayList<>();
        jobCom2.add(job2);

        assertEquals(jobCom1,searchJob.searchByCompany("Leonidas")); // must pass
        assertEquals(jobCom1,searchJob.searchByCompany("leonidas")); // must pass
        assertEquals(jobCom2,searchJob.searchByCompany("Sprüngli"));

    }


}