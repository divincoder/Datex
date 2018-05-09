package com.datex.datex.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.datex.datex.R;
import com.datex.datex.activity.NavigationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPatientFragment extends Fragment implements View.OnClickListener {

    // private Button nextButton;
    private ViewFlipper viewFlipper;


    public AddPatientFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Bundle args) {
        Fragment frag = new AddPatientFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_add_patient, container, false);

        // nextButton = rootView.findViewById(R.id.next_button);
        viewFlipper = rootView.findViewById(R.id.view_flipper);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

    private void init(View view, Bundle savedInstanceState) {
        viewFlipper.setDisplayedChild(0);
        // nextButton.setOnClickListener(this);
//        Toolbar toolbar = ((NavigationActivity) getActivity()).getToolbar();
//        toolbar.setVisibility(View.VISIBLE);
//        toolbar.setTitle(getString(R.string.label_add_patient));
    }

    @Override
    public void onClick(View v) {
//       switch (v.getId()){
//           case R.id.next_button:
//               viewFlipper.setDisplayedChild(1);
//               break;
//        }
    }

//    @Override
//    public void onBackPressed() {
//            if (viewFlipper.getDisplayedChild() == 1) {
//                viewFlipper.setDisplayedChild(0);
//                //signupButton.setText(R.string.continue_label);
//            } else {
//                //closeFragment();
//            }
//    }
}
