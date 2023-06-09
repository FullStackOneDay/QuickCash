package com.example.quick_cash_grp13;

import java.util.ArrayList;
import java.util.List;

public class SearchJob {

    private List<Job> jobs;

    public SearchJob(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "SearchJob{" +
                "jobs=" + jobs +
                "}\n";
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    // add one job
    public void addJob(Job jobAdd){
        jobs.add(jobAdd);
    }

    // add an arraylist of jobs
    public void addJobs(List<Job> jobsAdd){
        jobs.addAll(jobsAdd);
    }

    //Method that returns an ArrayList of jobs that have titles that contain the users' search.
    public List<Job> searchByTitle(String title){
        ArrayList<Job> jobSearched = new ArrayList<>();
        for (Job job : jobs){
            if(job.getJobTitle().toLowerCase().contains(title.toLowerCase())){
                jobSearched.add(job);
            }
        }
        return jobSearched;
    }

    //Method that returns an ArrayList of jobs that have job fields that contain the users' search.
    public  List<Job> searchByField(String field){
        ArrayList<Job> fieldSearched = new ArrayList<>();
        for(Job job : jobs){
            if(job.getField().toLowerCase().contains(field.toLowerCase())){
                fieldSearched.add(job);
            }
        }
        return fieldSearched;
    }

    //Method that returns an ArrayList of jobs that have companies that contain the users' search.
    public  List<Job> searchByCompany(String company){
        ArrayList<Job> companySearched = new ArrayList<>();
        for(Job job : jobs){
            if(job.getCompany().toLowerCase().contains(company.toLowerCase())){
                companySearched.add(job);
            }
        }
        return companySearched;
    }

    //Method that returns an ArrayList of jobs that have locations that contain the users' search.
    public  List<Job> searchByLocation(String location){
        ArrayList<Job> locationSearched = new ArrayList<>();
        for(Job job : jobs){
            if(job.getLocation().toLowerCase().contains(location.toLowerCase())){
                locationSearched.add(job);
            }
        }
        return locationSearched;

    }

    //Method that returns an ArrayList of jobs that have salaries that are exactly like the users' search.
    public  List<Job> searchBySalary(double salary){
        ArrayList<Job> salarySearched = new ArrayList<>();
        for(Job job : jobs){
            if(job.getSalary() == (salary)){
                salarySearched.add(job);
            }
        }
        return salarySearched;

    }

}
