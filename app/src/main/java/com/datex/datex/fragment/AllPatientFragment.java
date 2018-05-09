package com.datex.datex.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datex.datex.PatientListAdapter;
import com.datex.datex.R;
import com.datex.datex.model.Patient;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllPatientFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private PatientListAdapter mAdapter;
    private ArrayList<Patient> mPatientList;


    public AllPatientFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Bundle args) {
        Fragment frag = new AllPatientFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_all_patient, container, false);
        createDummyList();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

    private void init(View view, Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new PatientListAdapter(getActivity(), mPatientList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        Toolbar toolbar = ((Na) getActivity()).getToolbar();
//        toolbar.setVisibility(View.VISIBLE);
//        toolbar.setTitle(getString(R.string.label_all_patients));
    }

    public void createDummyList() {
        mPatientList = new ArrayList<>(4);
        mPatientList.add(new Patient("Ugochukwu", "Valentine", "Ofoegbu"));
        mPatientList.add(new Patient("Emeka", "Joel", "Aniegbo"));
        mPatientList.add(new Patient("Maduka", "Micheal", "Nsude"));
    }

}
