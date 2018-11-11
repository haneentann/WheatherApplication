package com.example.hp1.wheatherapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInSignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FIREBASE";
    EditText etPassword, etEmail, etExtra;
    Button btSignUp, btLogin;
    private FirebaseAuth mAuth;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_sign_up);

        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etExtra = findViewById(R.id.etExtra);

        btLogin = findViewById(R.id.btLogIn);
        btLogin.setOnClickListener(this);

        btSignUp = findViewById(R.id.btSignUp);
        btSignUp.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid();
                            myRef.child(userId).child("extra").setValue(etExtra.getText().toString());
                            Toast.makeText(LogInSignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LogInSignUpActivity.this, CameraGalleryActivity.class);
                            startActivity(i);

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LogInSignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LogInSignUpActivity.this, CameraGalleryActivity.class);
                            i.putExtra("userId", user.getUid());
                            startActivity(i);
                            Toast.makeText(LogInSignUpActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInSignUpActivity.this, "Authentication failed: "+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(email.equals("") || password.equals("")){
            Toast.makeText(this, "Email or Password is emapy", Toast.LENGTH_LONG).show();
        }else {
            if (view == btSignUp) {
                createAccount(email, password);
            } else {
                signIn("haneen1@gmail.com", "123456789");
            }
        }
    }

}
