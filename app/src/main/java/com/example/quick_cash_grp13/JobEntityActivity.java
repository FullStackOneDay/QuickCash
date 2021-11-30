package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
                TextView TextViewnSalart=  (TextView) findViewById(R.id.jobSalary);



                Intent mainIntent = getIntent();
                String wel =mainIntent.getStringExtra(HomeActivity.jobEntyty);
                int postion = Integer.parseInt(wel);
//                Toast.makeText(JobEntityActivity.this,wel, Toast.LENGTH_LONG).show();
                Job currJob = jobs.get(postion);
                Toast.makeText(JobEntityActivity.this, currJob.toString(), Toast.LENGTH_LONG).show();

                String title = "Title: "+currJob.getJobTitle();
                TextViewTitle.append(title);

                String fielfd = "Field: "+currJob.getField();
                TextViewField.append(fielfd);

                String company = "Company: "+currJob.getCompany();
                TextViewCom.append(company);

                String location = "Location: "+currJob.getLocation();
                TextViewLoc.append(location);

                double salary1 = currJob.getSalary();
                String salary = "Salary: " + salary1;
                if (salary1 == 0){
                    salary = "No information";
                    TextViewnSalart.append(salary);

                }
                else {
                    TextViewnSalart.append(salary);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }





}