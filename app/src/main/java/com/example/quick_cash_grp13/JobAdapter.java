package com.example.quick_cash_grp13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class JobAdapter extends ArrayAdapter<JobOffline> {
    private Context mContext;
    private ArrayList<JobOffline> jobOfflines;

    public JobAdapter(Context context, ArrayList<JobOffline> jobOfflines) {
        super(context, 0, jobOfflines);
        mContext = context;
        this.jobOfflines = jobOfflines;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.job_listview,parent,false);

        JobOffline jobOffline = jobOfflines.get(position);

        TextView jobTitle = convertView.findViewById(R.id.jobTitleList);
        TextView jobField = convertView.findViewById(R.id.jobFieldList);
        TextView jobCompany = convertView.findViewById(R.id.jobCompList);
        TextView jobLocation = convertView.findViewById(R.id.jobLocationList);
        TextView jobSalary = convertView.findViewById(R.id.jobSalaryList);

        jobTitle.setText(jobOffline.getJobTitle());
        jobField.setText(jobOffline.getField());
        jobCompany.setText(jobOffline.getCompany());
        jobLocation.setText(jobOffline.getLocation());
        jobSalary.setText((int) jobOffline.getSalary());

        return listItem;
    }


}
