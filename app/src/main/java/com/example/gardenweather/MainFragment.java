package com.example.gardenweather;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MainFragment extends Fragment/* implements Observer*/{
    private ViewModelData modelData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelData = ViewModelProviders.of(getActivity()).get(ViewModelData.class);
    }

    private void initRecycleLineHours(DataSourceTextPicTemp sourceData, View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        AdapterLineClock adapter = new AdapterLineClock(sourceData);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        DataSourceTextPicTemp sourceData = new DataSourceTextPicTemp(getResources());
        initRecycleLineHours(sourceData.buildLineByClock(), view);

        ImageView imageView = view.findViewById(R.id.imageView13);
        TextView textView = view.findViewById(R.id.textView);

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

        modelData.getCity().observe(this, textView::setText);

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        textView.setText(city);
//        Log.d("-------------------", "перед показом фрагмента");
//    }
//
//    @Override
//    public void updateCity(String city) {
//        Log.d("-------------------", "запись значения");
//        this.city = city;
//    }
}