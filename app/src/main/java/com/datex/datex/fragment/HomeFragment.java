package com.datex.datex.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.datex.datex.DetailsActivity;
import com.datex.datex.R;
import com.datex.datex.activity.AddPatientActivity;
import com.datex.datex.activity.NavigationActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.piechart)
    PieChart pieChart;
    @BindView(R.id.dashboard_all_patients)
    LinearLayout dashboardAllPatients;
    @BindView(R.id.dashboard_gestational)
    LinearLayout dashboardGestattional;
    @BindView(R.id.dashboard_females)
    LinearLayout dashboardFemales;
    @BindView(R.id.dashboard_males)
    LinearLayout dashboardMales;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Bundle args) {
        Fragment frag = new HomeFragment();
        if (args == null) {
            args = new Bundle();
        }
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

    private void init(View view, Bundle savedInstanceState) {
        Toolbar toolbar = ((NavigationActivity) getActivity()).getToolbar();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle(getString(R.string.label_home));
        pieChart.setUsePercentValues(true);
        setUpPieChart();

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPatientActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpPieChart() {
        ArrayList<PieEntry> aList = new ArrayList<>();
        aList.add(new PieEntry(60, "Type 1"));
        aList.add(new PieEntry(30, "Type 2"));
        aList.add(new PieEntry(20, "Gestational"));

        PieDataSet dataSet = new PieDataSet(aList, "Color Code");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        Description description = new Description();
        description.setText("Registered Diabetic Patient Diagnosis");

        pieChart.setDescription(description);
        pieChart.setDrawHoleEnabled(false);

        pieChart.setData(data);
        pieChart.invalidate(); //refresh

        //set the formatter for the pie Chart

    }

    @OnClick({R.id.dashboard_males, R.id.dashboard_females, R.id.dashboard_gestational, R.id.dashboard_all_patients})
    public void onClickHandler(View view) {
        switch (view.getId()) {

            case R.id.dashboard_males:
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("males", "No Males Presently in the Database");
                startActivity(intent);
                break;
            case R.id.dashboard_all_patients:
                Intent intent2 = new Intent(getContext(), DetailsActivity.class);
                intent2.putExtra("all patients", "No Patient Presently in the Database");
                startActivity(intent2);
                break;
            case R.id.dashboard_females:
                Intent intent3 = new Intent(getContext(), DetailsActivity.class);
                intent3.putExtra("females", "No female Presently in the Database");
                startActivity(intent3);
                break;
            case R.id.dashboard_gestational:
                Intent intent4 = new Intent(getContext(), DetailsActivity.class);
                intent4.putExtra("gestational", "No gestational patient Presently in the Database");
                startActivity(intent4);
                break;
        }
    }


}
