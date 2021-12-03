package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ArrayList<Job> jobs = new ArrayList<>();
    ArrayList<Job> searchResult = new ArrayList<>();
    SearchJob searchJob;
    ArrayList<String>job1;
    ArrayList<String>job2;
    ArrayList<String>job3;
    ArrayList<String>job4;
    ArrayList<String>job5;
    public static String jobEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeDatabase();

        searchJob = new SearchJob(jobs);
        EditText searchText = (EditText) findViewById(R.id.searchBox);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        Button backButton = (Button) findViewById(R.id.backButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        RadioButton radioButtonTitle=  (RadioButton) findViewById(R.id.radioButtonTitle);
        RadioButton radioButtonField=  (RadioButton) findViewById(R.id.radioButtonField);
        RadioButton radioButtonCom=  (RadioButton) findViewById(R.id.radioButtonCompany);
        RadioButton radioButtonLoc=  (RadioButton) findViewById(R.id.radioButtonLocation);
        RadioButton radioButtonSalart=  (RadioButton) findViewById(R.id.radioButtonSalary);
        TextView searchText1 = (TextView) findViewById(R.id.searchResul1);
        TextView searchText2 = (TextView) findViewById(R.id.searchResul2);
        TextView searchText3 = (TextView) findViewById(R.id.searchResul3);
        TextView searchText4 = (TextView) findViewById(R.id.searchResul4);
        TextView searchText5 = (TextView) findViewById(R.id.searchResul5);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchResult.clear();
                searchText1.setText("");
                searchText2.setText("");
                searchText3.setText("");
                searchText4.setText("");
                searchText5.setText("");

                String searchedText = (searchText.getText().toString());
                if (radioButtonTitle.isChecked()){
                    searchResult = (ArrayList<Job>) searchJob.searchByTitle(searchedText);
                }
                else if (radioButtonField.isChecked()){
                    searchResult = (ArrayList<Job>) searchJob.searchByField(searchedText);
                }else if (radioButtonCom.isChecked()){
                    searchResult = (ArrayList<Job>) searchJob.searchByCompany(searchedText);
                }else if (radioButtonLoc.isChecked()){
                    searchResult = (ArrayList<Job>) searchJob.searchByLocation(searchedText);
                }else if (radioButtonSalart.isChecked()){
                    try {
                        double st = Double.parseDouble(searchedText);
                        searchResult = (ArrayList<Job>) searchJob.searchBySalary(st);
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(SearchActivity.this,"Please input numbers", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(SearchActivity.this,"Please Select", Toast.LENGTH_LONG).show();
                }

                if (searchResult.isEmpty()){
                    Toast.makeText(SearchActivity.this,"Not Found", Toast.LENGTH_LONG).show();

                }
                else {
                    int searchResultCount = searchResult.size();
                    searchText1.append(searchResult.get(0).toString());
                    searchText1.getText();
                    searchText1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            job1 = addToJob(searchResult,0);
                            Intent intent = new Intent(getApplicationContext(), JobEntityActivity.class);
                            intent.putStringArrayListExtra(jobEntity, job1);

                            startActivity(intent);
                        }
                    });

                    if (searchResultCount>=2 ) {
                        searchText2.append(searchResult.get(1).toString());
                        searchText2.getText();

                        searchText2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                job2 = addToJob(searchResult,1);
                                Intent intent = new Intent(getApplicationContext(), JobEntityActivity.class);
                                intent.putStringArrayListExtra(jobEntity, job2);

                                startActivity(intent);
                            }
                        });
                    }
                    if (searchResultCount>=3 ) {
                        searchText3.append(searchResult.get(2).toString());
                        searchText3.getText();

                        searchText3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                job3 = addToJob(searchResult,2);
                                Intent intent = new Intent(getApplicationContext(), JobEntityActivity.class);
                                intent.putStringArrayListExtra(jobEntity, job3);

                                startActivity(intent);
                            }
                        });

                    }
                    if (searchResultCount>=4 ) {
                        searchText4.append(searchResult.get(3).toString());
                        searchText4.getText();


                        searchText4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                job4 = addToJob(searchResult,3);
                                Intent intent = new Intent(getApplicationContext(), JobEntityActivity.class);
                                intent.putStringArrayListExtra(jobEntity, job4);

                                startActivity(intent);
                            }
                        });
                    }
                    if (searchResultCount>4 ) {
                        searchText5.append(searchResult.get(4).toString());
                        searchText5.getText();
                        searchText5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                job5 = addToJob(searchResult,4);
                                Intent intent = new Intent(getApplicationContext(), JobEntityActivity.class);
                                intent.putStringArrayListExtra(jobEntity, job5);

                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });
    clearButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            searchText1.setText("");
            searchText2.setText("");
            searchText3.setText("");
            searchText4.setText("");
            searchText5.setText("");
            searchText.setText("");
        }
    });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

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
                    searchResult.add(job);
                }
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