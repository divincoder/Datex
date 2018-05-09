package com.datex.datex.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datex.datex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HbAlcTabFragment extends Fragment {


    public static Fragment newInstance(Bundle args) {
        Fragment frag = new HbAlcTabFragment();
        if (args == null) {
            args = new Bundle();
        }
        frag.setArguments(args);
        return frag;
    }

    public HbAlcTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hb_alc_tab, container, false);
    }

}
