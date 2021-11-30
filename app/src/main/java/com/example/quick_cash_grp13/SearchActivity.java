package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        TextView searchText1 = (TextView) findViewById(R.id.textSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchResult.clear();
                searchText1.setText("");

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
                    for (Job sr : searchResult) {
                        searchText1.append(sr.toString());
                        searchText1.append("\n");
                    }
                    searchText.getText();

                }


            }

        });
    clearButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            searchText1.setText("");
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




}