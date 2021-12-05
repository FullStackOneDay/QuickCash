package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Job {

    public static ArrayList<Job> allJobs = new ArrayList<Job>();

    private String jobTitle;
    private String company;

    // this "filed" is a drop down menu that has several choices.
    // can reference "https://www.indeed.com/career-advice/finding-a-job/careers-by-field"
    private String field;
    private String location;
    private double salary;
    private int salaryMonth;


    public Job(String jobTitle, String company, String field, String location, double salary) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.field = field;
        this.location = location;
        this.salary = salary;

    }

    public Job(String jobTitle, String company, String field, String location) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.field = field;
        this.location = location;
    }

    public Job(String jobTitle, String company, String field, String location, int salaryMonth) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.field = field;
        this.location = location;
        this.salaryMonth = salaryMonth;
    }

    //needed so maps activity wont crash?
    public Job() {

    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(int salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    @NonNull
    @Override
    public String toString() {
        if (salary != 0 && salaryMonth ==0) {
            return jobTitle + "\nField: " + field + "\nLocation: " + location + "\nSalary: " + salary;
        }
        else if (salary == 0 && salaryMonth !=0) {
            return jobTitle + "\nField: " + field + "\nLocation: " + location + "\nMonthly salary: " + salaryMonth;
        }
        else {
            return jobTitle + "\nField: " + field + "\nLocation: " + location;
        }
    }
}
