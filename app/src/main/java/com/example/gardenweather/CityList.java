package com.example.gardenweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CityList extends AppCompatActivity implements Sending{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);
        AutoCompleteTextView textView = findViewById(R.id.autoCompleteTextView3);
        textView.setText(getIntent().getExtras().getString(city));
    }

    public void thisCity1(View view) {
        TextView textView = findViewById(R.id.textView24);
        Intent intent = new Intent();
        intent.putExtra("city", textView.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void thisCity2(View view) {
        TextView textView = findViewById(R.id.textView26);
        Intent intent = new Intent();
        intent.putExtra("city", textView.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
