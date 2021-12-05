package com.example.quick_cash_grp13;

import java.util.ArrayList;
import java.util.List;

public class SearchJob {

    private List<JobOffline> jobOfflines;

    public SearchJob(List<JobOffline> jobOfflines) {
        this.jobOfflines = jobOfflines;
    }

    @Override
    public String toString() {
        return "SearchJob{" +
                "jobs=" + jobOfflines +
                "}\n";
    }

    public List<JobOffline> getJobs() {
        return jobOfflines;
    }

    public void setJobs(List<JobOffline> jobOfflines) {
        this.jobOfflines = jobOfflines;
    }

    // add one job
    public void addJob(JobOffline jobOfflineAdd){
        jobOfflines.add(jobOfflineAdd);
    }

    // add an arraylist of jobs
    public void addJobs(List<JobOffline> jobsAdd){
        jobOfflines.addAll(jobsAdd);
    }

    //Method that returns an ArrayList of jobs that have titles that contain the users' search.
    public List<JobOffline> searchByTitle(String title){
        ArrayList<JobOffline> jobOfflineSearched = new ArrayList<>();
        for (JobOffline jobOffline : jobOfflines){
            if(jobOffline.getJobTitle().toLowerCase().contains(title.toLowerCase())){
                jobOfflineSearched.add(jobOffline);
            }
        }
        return jobOfflineSearched;
    }

    //Method that returns an ArrayList of jobs that have job fields that contain the users' search.
    public  List<JobOffline> searchByField(String field){
        ArrayList<JobOffline> fieldSearched = new ArrayList<>();
        for(JobOffline jobOffline : jobOfflines){
            if(jobOffline.getField().toLowerCase().contains(field.toLowerCase())){
                fieldSearched.add(jobOffline);
            }
        }
        return fieldSearched;
    }

    //Method that returns an ArrayList of jobs that have companies that contain the users' search.
    public  List<JobOffline> searchByCompany(String company){
        ArrayList<JobOffline> companySearched = new ArrayList<>();
        for(JobOffline jobOffline : jobOfflines){
            if(jobOffline.getCompany().toLowerCase().contains(company.toLowerCase())){
                companySearched.add(jobOffline);
            }
        }
        return companySearched;
    }

    //Method that returns an ArrayList of jobs that have locations that contain the users' search.
    public  List<JobOffline> searchByLocation(String location){
        ArrayList<JobOffline> locationSearched = new ArrayList<>();
        for(JobOffline jobOffline : jobOfflines){
            if(jobOffline.getLocation().toLowerCase().contains(location.toLowerCase())){
                locationSearched.add(jobOffline);
            }
        }
        return locationSearched;

    }

    //Method that returns an ArrayList of jobs that have salaries that are exactly like the users' search.
    public  List<JobOffline> searchBySalary(double salary){
        ArrayList<JobOffline> salarySearched = new ArrayList<>();
        for(JobOffline jobOffline : jobOfflines){
            if(jobOffline.getSalary() == (salary)){
                salarySearched.add(jobOffline);
            }
        }
        return salarySearched;

    }

}
