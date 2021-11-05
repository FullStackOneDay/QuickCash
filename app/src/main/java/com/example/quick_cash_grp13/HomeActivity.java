package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity  {
    static String jobCom;
    SearchView searchView;
    ListView listView;
    ArrayList<Job> list;
    ArrayAdapter<Job> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // initializeDatabase();



        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        Job first = new Job("Developer","Google","IT","Halifax",20.0);
        Job second = new Job("Engineer","Microsoft","IT","Halifax",25.0);
        Job third = new Job("Researcher","Dalhousie","Communication","Mars",25.0);
        list.add(first);
        list.add(second);
        list.add(third);

        adapter = new ArrayAdapter<Job>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
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


    }

    // initialize database
    // TODO
    /*
    protected void initializeDatabase(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://quick-cash-grp13-default-rtdb.firebaseio.com/");
        DatabaseReference reference1 = db.getReference("jobs");
        Toast.makeText(HomeActivity.this,"Firebase connection success", Toast.LENGTH_LONG).show();
        reference1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot adSnapshot: snapshot.getChildren()) {
                    Job job = adSnapshot.getValue(Job.class);
                    Toast.makeText(getApplicationContext(), job.toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //initialize the database and the two references related to banner ID and email address.
    }*/


}