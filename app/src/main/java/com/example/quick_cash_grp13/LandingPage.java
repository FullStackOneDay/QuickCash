package com.example.quick_cash_grp13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPage extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        // get buttons
        Button employee = (Button) findViewById(R.id.Employee);
        Button employer = (Button) findViewById(R.id.Employer);

        //set button actions
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(employeeIntent);
            }
        });
        employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employerIntent = new Intent(getApplicationContext(), createPostActivity.class);
                startActivity(employerIntent);
            }
        });

    }

}