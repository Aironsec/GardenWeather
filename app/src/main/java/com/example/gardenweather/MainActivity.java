package com.example.gardenweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity {
    private void mess (String ...messages) {
        Toast.makeText(getApplicationContext(), messages[0], Toast.LENGTH_SHORT).show();
        Log.d(messages[1], messages[0]);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.title_template);
        String state;
        if (savedInstanceState == null) {
            state = "Первый запуск onCreate";
            textView.setText("29");
        } else {
            state = "Повторный запуск onCreate";
            MemData memData = MemData.getInstance();
            textView.setText(memData.getTodayTemper());
        }

        mess(state, "OnCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();

        mess("onStart состояние", "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mess("Повторный запуск onRestoreInstanceState", "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mess("onResume состояние", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mess("onPause состояние", "onPause");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mess("onSaveInstanceState состояние", "onSaveInstanceState");
        TextView textView = findViewById(R.id.title_template);
        MemData memData = MemData.getInstance();
        memData.setTodayTemper(textView.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mess("onStop состояние", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mess("onRestart состояние", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mess("onDestroy состояние", "onDestroy");
    }
}