package com.ueo.licentaapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ueo.licentaapp.DataCatcher;
import com.ueo.licentaapp.MainActivity;
import com.ueo.licentaapp.R;

public class ChoosePlan extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_plan);

        CheckBox checkBox1 = findViewById(R.id.checkbox_service);
        checkBox1.setOnClickListener(this);

        CheckBox checkBox2 = findViewById(R.id.checkbox_service1);
        checkBox2.setOnClickListener(this);

        CheckBox checkBox3 = findViewById(R.id.checkbox_service2);
        checkBox3.setOnClickListener(this);

        CheckBox checkBox4 = findViewById(R.id.checkbox_service3);
        checkBox4.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        TextView dsit, price;
        String text, textPrice;
        Intent intent;
        switch (view.getId()) {
            case R.id.checkbox_service:
                dsit = findViewById(R.id.textview_service_name);
                price = findViewById(R.id.textview_price);
                text =  dsit.getText().toString();
                textPrice = price.getText().toString();
                DataCatcher.getInstance().setDateAndTime("Serviciu: " + text);
                DataCatcher.getInstance().setPrice("Pret: " + textPrice);
                intent = new Intent(ChoosePlan.this, PickTimeAndDate.class);
                startActivity(intent);
                break;

            case R.id.checkbox_service1:
                dsit = findViewById(R.id.textview_service_name1);
                text =  dsit.getText().toString();
                price = findViewById(R.id.textview_price1);
                textPrice = price.getText().toString();
                DataCatcher.getInstance().setDateAndTime("Serviciu: " + text);
                DataCatcher.getInstance().setPrice("Pret: " + textPrice);
                intent = new Intent(ChoosePlan.this, PickTimeAndDate.class);
                startActivity(intent);
                break;

            case R.id.checkbox_service2:
                dsit = findViewById(R.id.textview_service_name2);
                text =  dsit.getText().toString();
                price = findViewById(R.id.textview_price2);
                textPrice = price.getText().toString();
                DataCatcher.getInstance().setDateAndTime("Serviciu: " + text);
                DataCatcher.getInstance().setPrice("Pret: " + textPrice);
                intent = new Intent(ChoosePlan.this, PickTimeAndDate.class);
                startActivity(intent);
                break;

            case R.id.checkbox_service3:
                dsit = findViewById(R.id.textview_service_name3);
                text =  dsit.getText().toString();
                price = findViewById(R.id.textview_price3);
                textPrice = price.getText().toString();
                DataCatcher.getInstance().setDateAndTime("Serviciu: " + text);
                DataCatcher.getInstance().setPrice("Pret: " + textPrice);
                intent = new Intent(ChoosePlan.this, PickTimeAndDate.class);
                startActivity(intent);
                break;
        }
    }
}
