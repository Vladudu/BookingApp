package com.ueo.licentaapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ueo.licentaapp.MainActivity;
import com.ueo.licentaapp.R;
import com.ueo.licentaapp.User;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);



        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://licentaapp-b9723-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("message");



    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;

        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if (fullName.isEmpty()) {
            editTextFullName.setError("Numele intreg este necesar!");
            editTextFullName.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            editTextAge.setError("Varsta necesara!");
            editTextAge.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email necesar!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email incorect!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Parola necesara!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Parola trebuie sa aiba cel putin 6 caractere!");
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() { // verificat daca a functionat
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            User user = new User(fullName,age,email); // am creat un obiect pt database

                            FirebaseDatabase.getInstance("https://licentaapp-b9723-default-rtdb.europe-west1.firebasedatabase.app/").getReference("User")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) //am luat id-ul userului creat si l am atribuit la cel de la linia 120
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,"Inregistrare cu succes! Revino la pagina de login", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "Inregistrare esuata", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(RegisterActivity.this, "Inregistrare esuata", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
