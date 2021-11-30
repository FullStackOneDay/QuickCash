package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity  {
    ArrayList<Job> jobs = new ArrayList<>();

    public static String jobEntyty;
    static String jobCom;
    SearchView searchView;
    ListView listView;
    ArrayList<Job> list;
    List<Job> TitleSearchlist;
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

        searchView = (SearchView) findViewById(R.id.searchView);

        jobMap = (Button) findViewById(R.id.gotoMap);

        list = new ArrayList<>();
        Job first = new Job("Developer","Google","IT","Halifax",20.0);
        Job second = new Job("Engineer","Microsoft","IT","Halifax,",25.0);
        Job third = new Job("Researcher","Dalhousie","Communication","Mars",25.0);
        list.add(first);
        list.add(second);
        list.add(third);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(jobs.contains(query)){
                    adapter.getFilter().filter(query);
                }
                if (checkBoxTitle.isChecked()){
                    TitleSearchlist = searchJob.searchByLocation(query);
                    Toast.makeText(HomeActivity.this, "good",Toast.LENGTH_LONG).show();
                }


                else{
                    Toast.makeText(HomeActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

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
                        String pos = Integer.toString(position);
                        intent.putExtra(jobEntyty, pos);

                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}