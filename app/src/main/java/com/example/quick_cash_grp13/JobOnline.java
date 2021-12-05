package com.example.quick_cash_grp13;

public class JobOnline extends JobCollection{
    private String url;


    public JobOnline(String jobTitle, String company, String field, String url, double salary) {
        super(jobTitle,company,field,salary);
        this.url = url;

    }


    public JobOnline(String jobTitle, String company, String field, String url, int salaryMonth) {
        super(jobTitle,company,field,salaryMonth);
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
