package com.example.gardenweather;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CityListFragment extends Fragment{
//    private Publisher publisher;
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        publisher = ((PublisherGetter)context).getPublisher();
//    }
    ViewModelData modelData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelData = ViewModelProviders.of(getActivity()).get(ViewModelData.class);
    }

    private void initRecycleCityList(DataSourceTextPicTemp sourceData, View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_city);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        AdapterCityList adapter = new AdapterCityList(sourceData);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        FragmentManager fragmentManager = getFragmentManager();
        adapter.SetOnItemClickListener((view1, position) -> {
            assert fragmentManager != null;
            fragmentManager.popBackStack();
            TextView textView = view1.findViewById(R.id.textView_city);
            modelData.selectCity(textView.getText().toString());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        DataSourceTextPicTemp sourceData = new DataSourceTextPicTemp(getResources());
        initRecycleCityList(sourceData.buildCityList(), view);

        return view;
    }

}