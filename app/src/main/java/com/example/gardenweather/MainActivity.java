package com.example.gardenweather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Sending{
    private void mess(String... messages) {
        Toast.makeText(getApplicationContext(), messages[0], Toast.LENGTH_SHORT).show();
        Log.d(messages[1], messages[0]);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.title_temp);
        String state;
        if (savedInstanceState == null) {
            state = "Первый запуск onCreate";
            textView.setText("29\u00B0");
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
        TextView textView = findViewById(R.id.title_temp);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != 1) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK){
            TextView textView = findViewById(R.id.textView);
            textView.setText(data != null ? data.getStringExtra("city") : "--//--");
        }
    }

    public void list_cites(View view) {
        Intent intent = new Intent(this, CityList.class);
        TextView textView = findViewById(R.id.textView);
        intent.putExtra("city", textView.getText().toString());
        startActivityForResult(intent, 1);
    }

    public void test1(View view) {
        TextView textView = findViewById(R.id.title_temp);
        Intent intent = new Intent(this, CityList.class);
        intent.putExtra(city, textView.getText().toString());
        startActivity(intent);
    }

    public void test2(View view) {
        Uri uri = Uri.parse("https://yandex.ru/pogoda/obninsk");
        Intent browser = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browser);
    }
}