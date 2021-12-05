package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
    ArrayList<JobOffline> jobOfflines = new ArrayList<>();

    public static String jobEntity;
    static String jobCom;
    ListView listView;
    List<JobOffline> titleSearchlist;
    ArrayAdapter<JobOffline> adapter;
    Button jobMap;
    SearchJob searchJob;
    CheckBox checkBoxTitle;
    private String TAG = "HomeTag";


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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jobOfflines);

        reference1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot adSnapshot: snapshot.getChildren()) {
                    JobOffline jobOffline = adSnapshot.getValue(JobOffline.class);
                    jobOfflines.add(jobOffline);
                }
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), JobEntityActivity.class);
                        ArrayList<String> job1 = addToJob(jobOfflines,position);
                        intent.putExtra(jobEntity, job1);

                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "The read failed: " + error.getCode());
            }
        });

    }
    public ArrayList<String> addToJob(List<JobOffline> jobOfflines, int position){
        ArrayList<String> jobEntityList = new ArrayList<>();
        jobEntityList.add(jobOfflines.get(position).getJobTitle());
        jobEntityList.add(jobOfflines.get(position).getField());
        jobEntityList.add(jobOfflines.get(position).getCompany());
        jobEntityList.add(jobOfflines.get(position).getLocation());
        jobEntityList.add(Integer.toString(jobOfflines.get(position).getSalaryMonth()));
        return jobEntityList;
    }



}