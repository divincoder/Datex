package com.datex.datex;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datex.datex.activity.AllPatientActivity;
import com.datex.datex.model.Patient;

import java.util.ArrayList;

/**
 * Created by Ofoegbu Valentine on 24/04/2018.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientViewHolder> {

    private ArrayList<Patient> mPatientList;
    private LayoutInflater mInflater;
    private Context mContext;

    public PatientListAdapter(Context context, ArrayList<Patient> patientLinkedList) {
        mInflater = LayoutInflater.from(context);
        this.mPatientList = patientLinkedList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.patient_item, parent, false);
        return new PatientViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient currentPatient = mPatientList.get(position);
        String patientFullName = currentPatient.getLastName() + ", " + currentPatient.getFirstName();
        holder.patientName.setText(patientFullName);
    }

    @Override
    public int getItemCount() {
        return mPatientList.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView patientName;
        private PatientListAdapter mAdapter;

        public PatientViewHolder(View itemView, PatientListAdapter adapter) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patient_name);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(mContext, AllPatientActivity.class);
//            mContext.startActivity(intent);
        }
    }
}
