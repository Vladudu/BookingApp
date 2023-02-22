package com.ueo.licentaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.data.DataHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ueo.licentaapp.DataCatcher;
import com.ueo.licentaapp.R;
import com.ueo.licentaapp.User;

public class PageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_activity);

        TextView textView1 = findViewById(R.id.catchText);
        textView1.setText(DataCatcher.getInstance().getName());

        TextView textView2 = findViewById(R.id.catchText1);
        textView2.setText(DataCatcher.getInstance().getDateAndTime());

        TextView textView3 = findViewById(R.id.catchText2);
        textView3.setText(DataCatcher.getInstance().getPrice());

        TextView textView4 = findViewById(R.id.welcome_message);
        textView4.setText(DataCatcher.getInstance().getUserName());

        TextView timeTextView = findViewById(R.id.catchText4);
        Intent intent = getIntent();
        int hour = intent.getIntExtra("hour", 0);
        int minute = intent.getIntExtra("minute", 0);
        if(hour != 0) {
            timeTextView.setText("Ora programarii: " + hour + ":" + minute);
            timeTextView.setVisibility(View.VISIBLE);
        }else{
            timeTextView.setVisibility(View.GONE);
        }

        TextView dateTextView = findViewById(R.id.catchText3);
        int year = getIntent().getIntExtra("year", 0);
        int month = getIntent().getIntExtra("month", 0);
        int day = getIntent().getIntExtra("day", 0);
        if(year != 0 && month != -1 && day != 0){
            dateTextView.setText("Data programarii: " + day + "/" + (month + 1) + "/" + year);
            dateTextView.setVisibility(View.VISIBLE);
        }else{
            dateTextView.setVisibility(View.GONE);
        }

        Button button = findViewById(R.id.bookButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageActivity.this, ChooseBarber.class);
                startActivity(intent);
            }
        });

        RelativeLayout relativeLayout = findViewById(R.id.accountlayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PageActivity.this, AccountInformation.class);
                startActivity(intent);
            }
        });

    }
}