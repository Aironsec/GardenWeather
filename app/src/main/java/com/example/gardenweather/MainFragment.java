package com.example.gardenweather;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MainFragment extends Fragment implements Observer{
    // TODO: 10.07.2020 Костыль для записи значения города updateCity запускается раньше создания создания фрагмента
    private String city;
    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        city = getResources().getString(R.string.city);
        Log.d("-------------------", "инит переменной");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView imageView = view.findViewById(R.id.imageView13);
        textView = view.findViewById(R.id.textView);
        imageView.setOnClickListener((View) -> {
            Uri uri = Uri.parse("https://yandex.ru/pogoda/obninsk");
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });

        textView.setOnClickListener((View) -> {
            assert getFragmentManager() != null;
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            fragmentTransaction.replace(R.id.fragment_main, new CityListFragment()).commit();
        });

        Log.d("-------------------", "создание фрагмента");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText(city);
        Log.d("-------------------", "перед показом фрагмента");
    }

    @Override
    public void updateCity(String city) {
        Log.d("-------------------", "запись значения");
        this.city = city;
    }
}