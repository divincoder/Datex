package com.datex.datex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.datex.datex.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void switchFragment(Activity activity, Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = ((AppCompatActivity) activity).getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            //((NavigationActivity) this).onFragmentChanged(fragment);
            fragmentTransaction.replace(R.id.container, fragment, backStateName);
            fragmentTransaction.commit();
        }
    }
}
