package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class JobOffline extends JobCollection {

    public static List<JobOffline> allJobOfflines = new ArrayList<>();

    private String location;


    public JobOffline(String jobTitle, String company, String field, String location, double salary) {
        super(jobTitle,company,field,salary);
        this.location = location;

    }

    public JobOffline(String jobTitle, String company, String field, String location) {
        super(jobTitle,company,field);
        this.location = location;
    }

    public JobOffline(String jobTitle, String company, String field, String location, int salaryMonth) {
        super(jobTitle,company,field,salaryMonth);

        this.location = location;
    }

    public JobOffline() {
    }
    //needed so maps activity wont crash?


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
