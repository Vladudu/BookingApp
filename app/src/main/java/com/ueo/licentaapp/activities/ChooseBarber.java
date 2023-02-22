package com.ueo.licentaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.data.DataHolder;
import com.ueo.licentaapp.DataCatcher;
import com.ueo.licentaapp.R;

import org.w3c.dom.Text;

public class ChooseBarber extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_barber);

        LinearLayout layout1 = findViewById(R.id.lay1);
        LinearLayout layout2 = findViewById(R.id.lay2);
        LinearLayout layout3 = findViewById(R.id.lay3);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = findViewById(R.id.firstName);
                String text =  name.getText().toString();
                DataCatcher.getInstance().setName("Frizer: " + text);
                Intent intent = new Intent(ChooseBarber.this, ChoosePlan.class);
                startActivity(intent);

            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = findViewById(R.id.secondName);
                String text =  name.getText().toString();
                DataCatcher.getInstance().setName("Frizer: " + text);
                Intent intent = new Intent(ChooseBarber.this, ChoosePlan.class);
                startActivity(intent);
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = findViewById(R.id.thirdName);
                String text =  name.getText().toString();
                DataCatcher.getInstance().setName("Frizer: " + text);
                Intent intent = new Intent(ChooseBarber.this, ChoosePlan.class);
                startActivity(intent);
            }
        });
    }
}
