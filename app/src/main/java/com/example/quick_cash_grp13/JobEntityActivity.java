package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobEntityActivity extends AppCompatActivity {

    ArrayList<Job> jobs = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_entity);

        initializeDatabase();
    }

    private void initializeDatabase(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference1 = db.getReference("jobs");

        reference1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot adSnapshot: snapshot.getChildren()) {
                    Job job = adSnapshot.getValue(Job.class);
                    jobs.add(job);
                }
                TextView TextViewTitle = (TextView) findViewById(R.id.jobTitile);
                TextView TextViewField=  (TextView) findViewById(R.id.jobField);
                TextView TextViewCom=  (TextView) findViewById(R.id.jobCompany);
                TextView TextViewLoc=  (TextView) findViewById(R.id.jonLocation);
                TextView TextViewSalary=  (TextView) findViewById(R.id.jobSalary);



//                Intent mainIntent = getIntent();
//                String wel =mainIntent.getStringExtra(HomeActivity.jobEntyty);
//                int postion = Integer.parseInt(wel);
////                Toast.makeText(JobEntityActivity.this,wel, Toast.LENGTH_LONG).show();
//                Job currJob = jobs.get(postion);
////                Toast.makeText(JobEntityActivity.this, currJob.toString(), Toast.LENGTH_LONG).show();
                Intent mainIntent = getIntent();
                ArrayList<String> job1 = mainIntent.getStringArrayListExtra(SearchActivity.jobEntity);
                if (job1.isEmpty()) {
                    job1 = mainIntent.getStringArrayListExtra(HomeActivity.jobEntity);
                }

                String title = job1.get(0);
                TextViewTitle.append(title);

                String field = "Field: "+job1.get(1);
                TextViewField.append(field);

                String company = "Company: "+job1.get(2);
                TextViewCom.append(company);

                String location = "Location: "+job1.get(3);
                TextViewLoc.append(location);

                double salary1 = Integer.parseInt(job1.get(4));
                String salary = "Salary: " + salary1;
                if (salary1 == 0.0){
                    salary = "Salary: No information provided";
                }
                TextViewSalary.append(salary);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }





}