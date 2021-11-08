package com.example.quick_cash_grp13;

import org.junit.*;

import java.util.ArrayList;

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


    static SearchJob searchJob;
    Job job1= new Job("Chocolate Shop Clerk","Leonidas","Sales","Halifax",12.95);
    Job job2= new Job("Chocolate Lovers Wanted","Lindt & Sprüngli","Sales","Kanata");
    Job job3= new Job("Respiratory Therapists (RRTs) ","Switch Health","Health and medicine","Halifax");
    Job job4= new Job("Image Processing / Computer Vision Engineer (Internship)","iClassifier Inc","Science and technology","Waterloo",4000);


    // Search Test
    public void iniSearch(){
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        jobs.add(job4);
        searchJob = new SearchJob(jobs);
    }

    @Test
    public void checkTitleSearch(){
        iniSearch();

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
    @Test
    public void checkCompanySearch(){
        iniSearch();
        ArrayList<Job> jobCom1 = new ArrayList<>();
        jobCom1.add(job1);

        ArrayList<Job> jobCom2 = new ArrayList<>();
        jobCom2.add(job2);

        assertEquals(jobCom1,searchJob.searchByCompany("Leonidas")); // must pass
        assertEquals(jobCom1,searchJob.searchByCompany("leonidas")); // must pass
        assertEquals(jobCom2,searchJob.searchByCompany("Sprüngli"));

    }

    @Test
    public void checkFieldSearch(){
        iniSearch();
        ArrayList<Job> jobField1 = new ArrayList<>();
        jobField1.add(job1);
        jobField1.add(job2);

        ArrayList<Job> jobField2 = new ArrayList<>();
        jobField2.add(job3);

        assertEquals(jobField1, searchJob.searchByField("Sales"));
        assertEquals(jobField2, searchJob.searchByField("Health and medicine"));
    }

    @Test
    public void checkLocationSearch(){
        iniSearch();
        ArrayList<Job> jobLocation1 = new ArrayList<>();
        jobLocation1.add(job1);
        jobLocation1.add(job3);

        ArrayList<Job> jobLocation2 = new ArrayList<>();
        jobLocation2.add(job4);

        assertEquals(jobLocation1, searchJob.searchByLocation("Halifax"));
        assertEquals(jobLocation2, searchJob.searchByLocation("Waterloo"));
    }

    @Test
    public void checkSalarySearch(){
        iniSearch();
        ArrayList<Job> jobSalary1 = new ArrayList<>();
        jobSalary1.add(job1);

        ArrayList<Job> jobSalary2 = new ArrayList<>();
        jobSalary2.add(job2);
        jobSalary2.add(job3);
        jobSalary2.add(job4);

        assertEquals(jobSalary1,searchJob.searchBySalary(12.95));
        assertEquals(jobSalary2,searchJob.searchBySalary(0));
    }
}
