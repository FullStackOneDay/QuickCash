package com.example.quick_cash_grp13;

import org.junit.Test;

import static org.junit.Assert.*;

public class JobTest {

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
        assertEquals( "Lawyer",job3.getJobTitle());
    }

    @Test
    public void testGetCompany(){
        assertEquals(job1.getCompany(), company);
    }

    @Test
    public void testSetCompany() {
        job3.setCompany("QEII");
        assertEquals( "QEII",job3.getCompany());
    }

    @Test
    public void testGetField(){
        assertEquals(job1.getField(), field);
    }

    @Test
    public void testSetField() {
        job3.setField("Law");
        assertEquals("Law",job3.getField());
    }

    @Test
    public void testGetLocation(){
        assertEquals(job1.getLocation(), location);
    }

    @Test
    public void testSetLocation() {
        job3.setLocation("Toronto");
        assertEquals("Toronto",job3.getLocation());
    }

    @Test
    public void testGetSalaryHr(){
        assertEquals(job1.getSalary(), salaryHr, 0);
    }

    @Test
    public void testSetSalaryHr() {
        job1.setSalary(32.0);
        assertEquals( 32.0, job1.getSalary(), 0);
    }

    @Test
    public void testGetSalaryMonth(){
        assertEquals(job2.getSalaryMonth(), salaryMonth);
    }

    @Test
    public void testSetSalaryMonth() {
        job2.setSalaryMonth(5500);
        assertEquals(5500, job2.getSalaryMonth(), 0);
    }
}
