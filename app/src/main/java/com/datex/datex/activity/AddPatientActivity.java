package com.datex.datex.activity;

import android.os.Bundle;

import com.datex.datex.R;
import com.datex.datex.fragment.AddPatientFragment;

public class AddPatientActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        switchFragment(AddPatientActivity.this, AddPatientFragment.newInstance(null));

    }
}
