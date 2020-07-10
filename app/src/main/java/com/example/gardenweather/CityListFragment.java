package com.example.gardenweather;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CityListFragment extends Fragment{
    private Publisher publisher;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        publisher = ((PublisherGetter)context).getPublisher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        LinearLayout linearLayout1 = view.findViewById(R.id.linear1);
        TextView textView1 = view.findViewById(R.id.textView24);
        LinearLayout linearLayout2 = view.findViewById(R.id.linear2);
        TextView textView2 = view.findViewById(R.id.textView26);
        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        linearLayout1.setOnClickListener((View) -> {
            fragmentManager.popBackStack();
            publisher.notify(textView1.getText().toString());
        });

        linearLayout2.setOnClickListener((View) -> {
            fragmentManager.popBackStack();
            publisher.notify(textView2.getText().toString());
        });
        return view;
    }

}