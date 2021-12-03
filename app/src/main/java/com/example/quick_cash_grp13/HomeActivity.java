package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity  {
    ArrayList<Job> jobs = new ArrayList<>();

    public static String jobEntity;
    static String jobCom;
    ListView listView;
    List<Job> titleSearchlist;
    ArrayAdapter<Job> adapter;
    Button jobMap;
    SearchJob searchJob;
    CheckBox checkBoxTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button adSearch = (Button) findViewById(R.id.adSearch);
        initializeDatabase();
        jobMap = (Button) findViewById(R.id.gotoMap);
        jobMap.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        adSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });




    }

    // initialize database
    private void initializeDatabase(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference1 = db.getReference("jobs");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jobs);

        reference1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot adSnapshot: snapshot.getChildren()) {
                    Job job = adSnapshot.getValue(Job.class);
                    jobs.add(job);
                }
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), JobEntityActivity.class);
                        ArrayList<String> job1 = addToJob(jobs,position);
                        intent.putExtra(jobEntity, job1);

                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public ArrayList<String> addToJob(List<Job> jobs, int position){
        ArrayList<String> jobEntity = new ArrayList<>();
        jobEntity.add(jobs.get(position).getJobTitle());
        jobEntity.add(jobs.get(position).getField());
        jobEntity.add(jobs.get(position).getCompany());
        jobEntity.add(jobs.get(position).getLocation());
        jobEntity.add(Integer.toString(jobs.get(position).getSalaryMonth()));
        return jobEntity;
    }



}