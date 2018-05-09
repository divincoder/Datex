package com.datex.datex.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.datex.datex.R;
import com.datex.datex.fragment.AllPatientFragment;

public class AllPatientActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_patient);

        //switchFragment(this, AllPatientFragment.newInstance(null));
    }
}
