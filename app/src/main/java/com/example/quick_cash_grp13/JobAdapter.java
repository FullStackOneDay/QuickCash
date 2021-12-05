package com.example.quick_cash_grp13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class JobAdapter extends ArrayAdapter<Job> {
    private Context mContext;
    private ArrayList<Job> jobs;

    public JobAdapter(Context context, ArrayList<Job> jobs) {
        super(context, 0, jobs);
        mContext = context;
        this.jobs = jobs;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.job_listview,parent,false);

        Job job = jobs.get(position);

        TextView jobTitle = convertView.findViewById(R.id.jobTitleList);
        TextView jobField = convertView.findViewById(R.id.jobFieldList);
        TextView jobCompany = convertView.findViewById(R.id.jobCompList);
        TextView jobLocation = convertView.findViewById(R.id.jobLocationList);
        TextView jobSalary = convertView.findViewById(R.id.jobSalaryList);

        jobTitle.setText(job.getJobTitle());
        jobField.setText(job.getField());
        jobCompany.setText(job.getCompany());
        jobLocation.setText(job.getLocation());
        jobSalary.setText((int) job.getSalary());

        return listItem;
    }


}
