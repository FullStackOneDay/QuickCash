package com.example.quick_cash_grp13;


import static org.junit.Assert.*;

import org.junit.*;

import java.util.ArrayList;


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
    JobOffline jobOffline1 = new JobOffline("Chocolate Shop Clerk","Leonidas","Sales","Halifax",12.95);
    JobOffline jobOffline2 = new JobOffline("Chocolate Lovers Wanted","Lindt & Sprüngli","Sales","Kanata");
    JobOffline jobOffline3 = new JobOffline("Respiratory Therapists (RRTs) ","Switch Health","Health and medicine","Halifax");
    JobOffline jobOffline4 = new JobOffline("Image Processing / Computer Vision Engineer (Internship)","iClassifier Inc","Science and technology","Waterloo",4000);


    // Search Test
    public void iniSearch(){
        ArrayList<JobOffline> jobOfflines = new ArrayList<>();
        jobOfflines.add(jobOffline1);
        jobOfflines.add(jobOffline2);
        jobOfflines.add(jobOffline3);
        jobOfflines.add(jobOffline4);
        searchJob = new SearchJob(jobOfflines);
    }

    @Test
    public void checkTitleSearch(){
        iniSearch();

        ArrayList<JobOffline> jobOfflineTitle1 = new ArrayList<>();
        jobOfflineTitle1.add(jobOffline1);

        ArrayList<JobOffline> jobOfflineTitle2 = new ArrayList<>();
        jobOfflineTitle2.add(jobOffline1);
        jobOfflineTitle2.add(jobOffline2);

        ArrayList<JobOffline> jobOfflineTitle3 = new ArrayList<>();
        jobOfflineTitle3.add(jobOffline3);

        ArrayList<JobOffline> jobOfflineTitle4 = new ArrayList<>();
        jobOfflineTitle4.add(jobOffline4);

        assertEquals(jobOfflineTitle1,searchJob.searchByTitle("Chocolate Shop Clerk"));// must pass
        assertEquals(jobOfflineTitle1,searchJob.searchByTitle("chocolate shop Clerk"));// must pass
        assertEquals(jobOfflineTitle2,searchJob.searchByTitle("Chocolate"));
        assertEquals(jobOfflineTitle3,searchJob.searchByTitle("Respiratory Therapists"));
        assertEquals(jobOfflineTitle4,searchJob.searchByTitle(" Computer Vision Engineer"));

    }
    @Test
    public void checkCompanySearch(){
        iniSearch();
        ArrayList<JobOffline> jobOfflineCom1 = new ArrayList<>();
        jobOfflineCom1.add(jobOffline1);

        ArrayList<JobOffline> jobOfflineCom2 = new ArrayList<>();
        jobOfflineCom2.add(jobOffline2);

        assertEquals(jobOfflineCom1,searchJob.searchByCompany("Leonidas")); // must pass
        assertEquals(jobOfflineCom1,searchJob.searchByCompany("leonidas")); // must pass
        assertEquals(jobOfflineCom2,searchJob.searchByCompany("Sprüngli"));

    }

    @Test
    public void checkFieldSearch(){
        iniSearch();
        ArrayList<JobOffline> jobOfflineField1 = new ArrayList<>();
        jobOfflineField1.add(jobOffline1);
        jobOfflineField1.add(jobOffline2);

        ArrayList<JobOffline> jobOfflineField2 = new ArrayList<>();
        jobOfflineField2.add(jobOffline3);

        assertEquals(jobOfflineField1, searchJob.searchByField("Sales"));
        assertEquals(jobOfflineField2, searchJob.searchByField("Health and medicine"));
    }

    @Test
    public void checkLocationSearch(){
        iniSearch();
        ArrayList<JobOffline> jobOfflineLocation1 = new ArrayList<>();
        jobOfflineLocation1.add(jobOffline1);
        jobOfflineLocation1.add(jobOffline3);

        ArrayList<JobOffline> jobOfflineLocation2 = new ArrayList<>();
        jobOfflineLocation2.add(jobOffline4);

        assertEquals(jobOfflineLocation1, searchJob.searchByLocation("Halifax"));
        assertEquals(jobOfflineLocation2, searchJob.searchByLocation("Waterloo"));
    }

    @Test
    public void checkSalarySearch(){
        iniSearch();
        ArrayList<JobOffline> jobOfflineSalary1 = new ArrayList<>();
        jobOfflineSalary1.add(jobOffline1);

        ArrayList<JobOffline> jobOfflineSalary2 = new ArrayList<>();
        jobOfflineSalary2.add(jobOffline2);
        jobOfflineSalary2.add(jobOffline3);
        jobOfflineSalary2.add(jobOffline4);

        assertEquals(jobOfflineSalary1,searchJob.searchBySalary(12.95));
        assertEquals(jobOfflineSalary2,searchJob.searchBySalary(0));
    }
}
