package com.example.quick_cash_grp13;

public abstract class JobCollection {
    protected String jobTitle;
    protected String company;
    // this "filed" is a drop down menu that has several choices.
    // can reference "https://www.indeed.com/career-advice/finding-a-job/careers-by-field"
    protected String field;
    protected double salary;
    protected int salaryMonth;

    public JobCollection(String jobTitle, String company, String field, double salary) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.field = field;
        this.salary = salary;
    }

    public JobCollection(String jobTitle, String company, String field, int salaryMonth) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.field = field;
        this.salaryMonth = salaryMonth;
    }
    public JobCollection(String jobTitle, String company, String field) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.field = field;
    }
    public JobCollection() {

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
}
