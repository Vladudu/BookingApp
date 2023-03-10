package com.ueo.licentaapp.activities;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ueo.licentaapp.R;

import org.w3c.dom.Text;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);


        emailEditText = (EditText) findViewById(R.id.email);
        resetPasswordButton = (Button) findViewById(R.id.resetPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }


        private void resetPassword(){
            String email = emailEditText.getText().toString().trim();
            if ( email.isEmpty ( ) ) {
                emailEditText.setError ( " Email is required ! " ) ;
                emailEditText.requestFocus();
                return ;
            }
            if ( ! Patterns.EMAIL_ADDRESS.matcher ( email ) .matches ( ) ) {
                emailEditText.setError ( " Please provide valid email ! " ) ;
                emailEditText.requestFocus ( ) ;
                return ;
            }

            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPassword.this,"Chek your email to reset your password",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(ForgotPassword.this,"Try again, smth went wrong",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

