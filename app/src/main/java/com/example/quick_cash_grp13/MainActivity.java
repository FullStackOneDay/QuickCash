package com.example.quick_cash_grp13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        //Test Connection to Firebase
        // Toast.makeText(MainActivity.this,"Firebase connection success", Toast.LENGTH_LONG).show();

        // get user name from user input
        EditText loginEmail = (EditText) findViewById(R.id.loginEmail);
        EditText loginPassword = (EditText) findViewById(R.id.loginPassword);



        // get login button
        Button login = (Button) findViewById(R.id.loginCheck);
        login.setOnClickListener(new View.OnClickListener() {
            // register system
            @Override
            public void onClick(View view) {
                // get user name and password to string
                String EmailAddr = (loginEmail.getText().toString());
                String userPassword = (loginPassword.getText().toString());
                loginCheck(EmailAddr,userPassword);
            }
        });


        // get register button
        Button register = (Button) findViewById(R.id.registerCheck);
        register.setOnClickListener(new View.OnClickListener() {
            // register system
            @Override
            public void onClick(View view) {
                // get email and password to string
                String EmailAddr = (loginEmail.getText().toString());
                String userPassword = (loginPassword.getText().toString());
                registerCheck(EmailAddr,userPassword);
            }
        });
    }

    //checks if email is empty
    protected boolean isEmailEmpty(String email) {
        return email.isEmpty();
    }

    //checks if email is valid by ensuring it contains @ sign
    protected boolean isEmailValid(String email) {
        return !isEmailEmpty(email) && email.contains("@");
    }

    //sets the text of the error message TextView
    protected void setMessage(String msg) {
        TextView errMsg = (TextView) findViewById(R.id.errMsg);
        errMsg.setText(msg);
    }

    //intent switcher to home page
    protected void switch2HomeActivity() {
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
    }

    //User login if user is already registered
    public void loginCheck(String email, String userPassword){
        final String[] errorMessage = {new String()};
        //Error checking prior to logging in
        //Empty email field
        if (isEmailEmpty(email)) {
            errorMessage[0] = "Email field required";
        }
        //Invalid email
        else if (isEmailValid(email) == false) {
            errorMessage[0] = "Invalid email address";
        }
        else {
            mAuth.signInWithEmailAndPassword(email, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        errorMessage[0] = "Login succeed";
                        Toast.makeText(MainActivity.this, "Login succeed", Toast.LENGTH_SHORT).show();
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        //update UI and move to next activity
                        //Start next activity
                        switch2HomeActivity();

                    } else {
                        // If sign in fails, display a message to the user.
                        errorMessage[0] = "Authentication failed";
                    }
                }
            });
        }
        setMessage(errorMessage[0]);
    }

    public void registerCheck(String userEmail, String userPassword){
        final String[] errorMessage = {new String()};
        //Error checking prior to logging in
        //Empty email field
        if (isEmailEmpty(userEmail)) {
            errorMessage[0] = "Email field required";
        }
        //Invalid email
        else if (isEmailValid(userEmail) == false) {
            errorMessage[0] = "Invalid email address";
        }
        else {
            mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign up success, update UI with the signed-up user's information
                        // This is adding users into the authentication section of Firebase
                        errorMessage[0] = "Registration succeed.";
                        FirebaseUser user = mAuth.getCurrentUser();

                        // This is record user's email and password to the database (kinda redundant)
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                        Hashtable<String, String> record = new Hashtable<>();
                        record.put("email", userEmail);
                        record.put("password", userPassword);
                        ref.child(user.getUid()).setValue(record);


                        //update UI and move to next activity
                    } else {
                        // If sign up fails, display a message to the user.
                        errorMessage[0] = "Registration failed.";
                        //update UI and move to next activity
                    }
                }
            });
        }
        setMessage(errorMessage[0]);
    }

}