package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
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

    public void loginCheck(String email, String userPassword){
        mAuth.signInWithEmailAndPassword(email, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    //update UI and move to next activity
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //update UI and move to next activity
                    FirebaseUser user = null;
                }
            }
        });
    }

    public void registerCheck(String userName, String userPassword){
        //TODO
    }
}