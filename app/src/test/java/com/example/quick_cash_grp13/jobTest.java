package com.example.quick_cash_grp13;

import org.junit.Test;

import static org.junit.Assert.*;

public class jobTest {

    String jobTitle = "Nurse";
    String company = "IWK Hospital";
    String location = "Halifax";
    String field = "Health and Medicine";
    double salaryHr = 30.5;
    int salaryMonth = 4900;
    Job job1 = new Job(jobTitle, company, field, location, salaryHr);
    Job job2 = new Job(jobTitle, company, field, location, salaryMonth);
    Job job3 = new Job(jobTitle, company, field, location);

    @Test
    public void testGetJobTitle(){
        assertEquals(job1.getJobTitle(), jobTitle);
    }

    @Test
    public void testSetJobTitle() {
        job3.setJobTitle("Lawyer");
        assertEquals(job3.getJobTitle(), "Lawyer");
    }

    @Test
    public void testGetCompany(){
        assertEquals(job1.getCompany(), company);
    }

    @Test
    public void testSetCompany() {
        job3.setCompany("QEII");
        assertEquals(job3.getCompany(), "QEII");
    }

    @Test
    public void testGetField(){
        assertEquals(job1.getField(), field);
    }

    @Test
    public void testSetField() {
        job3.setField("Law");
        assertEquals(job3.getField(), "Law");
    }

    @Test
    public void testGetLocation(){
        assertEquals(job1.getLocation(), location);
    }

    @Test
    public void testSetLocation() {
        job3.setLocation("Toronto");
        assertEquals(job3.getLocation(), "Toronto");
    }

    @Test
    public void testGetSalaryHr(){
        assertEquals(job1.getSalary(), salaryHr, 0);
    }

    @Test
    public void testSetSalaryHr() {
        job1.setSalary(32.0);
        assertEquals(job1.getSalary(), 32.0, 0);
    }

    @Test
    public void testGetSalaryMonth(){
        assertEquals(job2.getSalaryMonth(), salaryMonth);
    }

    @Test
    public void testSetSalaryMonth() {
        job2.setSalaryMonth(5500);
        assertEquals(job2.getSalaryMonth(), 5500);
    }
}
