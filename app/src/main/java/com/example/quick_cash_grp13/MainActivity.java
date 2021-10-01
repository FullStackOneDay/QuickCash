package com.example.quick_cash_grp13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Test Connection to Firebase
//        Toast.makeText(MainActivity.this,"Firebase connection success", Toast.LENGTH_LONG).show();

        // get user name from user input
        EditText loginUserName = (EditText) findViewById(R.id.loginUserName);
        EditText loginPassword = (EditText) findViewById(R.id.loginPassword);

        // get user name and password to string
        String userName = (loginUserName.getText().toString());
        String userPassword = (loginPassword.getText().toString());

        // get login button
        Button login = (Button) findViewById(R.id.loginCheck);
        login.setOnClickListener(new View.OnClickListener() {
            // register system
            @Override
            public void onClick(View view) {
                loginCheck(userName,userPassword);
            }
        });


        // get register button
        Button register = (Button) findViewById(R.id.registerCheck);
        register.setOnClickListener(new View.OnClickListener() {
            // register system
            @Override
            public void onClick(View view) {
                registerCheck(userName,userPassword);

            }
        });
    }

    public void loginCheck(String userName, String userPassword){
        //TODO
    }

    public void registerCheck(String userName, String userPassword){
        //TODO
    }
}