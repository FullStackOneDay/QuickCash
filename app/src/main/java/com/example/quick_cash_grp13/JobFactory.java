package com.example.quick_cash_grp13;

public class JobFactory {
    public JobCollection getMonthJob(String jobType, String jobTitle, String jobField, String jobCompany, String jobExtra, int salary){
        if (jobType==null){
            return null;
        }

        if (jobType.equalsIgnoreCase("JobOffline")){
            return new JobOffline();
        }
        else if (jobType.equalsIgnoreCase("JobOnline")){
            return new JobOnline(jobTitle,jobCompany,jobField,jobExtra,salary);

        }
        return null;
    }

    public JobCollection getDailyJob(String jobType, String jobTitle, String jobField, String jobCompany, String jobExtra, double salary){
        if (jobType==null){
            return null;
        }

        if (jobType.equalsIgnoreCase("JobOffline")){
            return new JobOffline();
        }
        else if (jobType.equalsIgnoreCase("JobOnline")){
            return new JobOnline(jobTitle,jobCompany,jobField,jobExtra,salary);

        }
        return null;
    }
}

