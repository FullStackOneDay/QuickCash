package com.example.quick_cash_grp13;

import org.junit.Test;

import static org.junit.Assert.*;

public class JobOfflineTest {

    String jobTitle = "Nurse";
    String company = "IWK Hospital";
    String location = "Halifax";
    String field = "Health and Medicine";
    double salaryHr = 30.5;
    int salaryMonth = 4900;
    JobOffline jobOffline1 = new JobOffline(jobTitle, company, field, location, salaryHr);
    JobOffline jobOffline2 = new JobOffline(jobTitle, company, field, location, salaryMonth);
    JobOffline jobOffline3 = new JobOffline(jobTitle, company, field, location);

    @Test
    public void testGetJobTitle(){
        assertEquals(jobOffline1.getJobTitle(), jobTitle);
    }

    @Test
    public void testSetJobTitle() {
        jobOffline3.setJobTitle("Lawyer");
        assertEquals( "Lawyer", jobOffline3.getJobTitle());
    }

    @Test
    public void testGetCompany(){
        assertEquals(jobOffline1.getCompany(), company);
    }

    @Test
    public void testSetCompany() {
        jobOffline3.setCompany("QEII");
        assertEquals( "QEII", jobOffline3.getCompany());
    }

    @Test
    public void testGetField(){
        assertEquals(jobOffline1.getField(), field);
    }

    @Test
    public void testSetField() {
        jobOffline3.setField("Law");
        assertEquals("Law", jobOffline3.getField());
    }

    @Test
    public void testGetLocation(){
        assertEquals(jobOffline1.getLocation(), location);
    }

    @Test
    public void testSetLocation() {
        jobOffline3.setLocation("Toronto");
        assertEquals("Toronto", jobOffline3.getLocation());
    }

    @Test
    public void testGetSalaryHr(){
        assertEquals(jobOffline1.getSalary(), salaryHr, 0);
    }

    @Test
    public void testSetSalaryHr() {
        jobOffline1.setSalary(32.0);
        assertEquals( 32.0, jobOffline1.getSalary(), 0);
    }

    @Test
    public void testGetSalaryMonth(){
        assertEquals(jobOffline2.getSalaryMonth(), salaryMonth);
    }

    @Test
    public void testSetSalaryMonth() {
        jobOffline2.setSalaryMonth(5500);
        assertEquals(5500, jobOffline2.getSalaryMonth(), 0);
    }
}
