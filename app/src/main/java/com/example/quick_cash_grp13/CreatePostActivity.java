package com.example.quick_cash_grp13;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class CreatePostActivity extends Activity {
    private FirebaseDatabase database;
    private DatabaseReference jobRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        EditText jobTitle = (EditText) findViewById(R.id.jobTitle);
        EditText company = (EditText) findViewById(R.id.company);
        EditText location = (EditText) findViewById(R.id.location);
        EditText salary = (EditText) findViewById(R.id.salary);
        Button submit = (Button) findViewById(R.id.postJobButton);
        RadioButton monthly = findViewById(R.id.monthly);
        //create field dropdown menu
        Spinner field = (Spinner) findViewById(R.id.fieldSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fieldArray, R.layout.support_simple_spinner_dropdown_item);
        field.setAdapter(adapter);
        TextView outMsg = (TextView) findViewById(R.id.outputMsg);

        //create a job posting when button is pressed
        submit.setOnClickListener(new View.OnClickListener() {
            //get text from fields and load into database
            @Override
            public void onClick(View view) {
                String jobTitleText = jobTitle.getText().toString();
                String companyText = company.getText().toString();
                String locationText = location.getText().toString();
                String fieldText = field.getSelectedItem().toString();
                double salaryAmount = Double.parseDouble(salary.getText().toString());
                boolean monthlySalary = monthly.isChecked();

                Job job;
                if (jobTitleText.isEmpty() || companyText.isEmpty() || locationText.isEmpty()
                        || fieldText.isEmpty() || !(isSalaryValid(salaryAmount))) {
                    outMsg.setText(R.string.job_missing_fields );

                }
                else {
                    //create a job from field data and push it to a list on the database
                    if(monthlySalary) { //salary is monthly,
                        job = new Job(jobTitleText, companyText, fieldText, locationText, (int)salaryAmount);
                    } else {    //salary is hourly
                        job = new Job(jobTitleText, companyText, fieldText, locationText, salaryAmount);
                    }
                    jobRef.push().setValue(job);
                    outMsg.setText(R.string.job_post_success);
                }
            }
        });

        initializeDatabase();
    }


    public boolean isSalaryValid(double salary) {
        return salary > 0;
    }


    private void initializeDatabase() {
        database = FirebaseDatabase.getInstance();
        jobRef = database.getReference("jobs");
    }
}
