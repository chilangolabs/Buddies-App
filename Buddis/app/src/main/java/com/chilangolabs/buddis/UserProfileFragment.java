package com.chilangolabs.buddis;


import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class UserProfileFragment extends Fragment {

    TextView txtIcon;


    public UserProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ((MaterialNavigationDrawer) this.getActivity()).getToolbar().setTitle("");

        txtIcon = (TextView) v.findViewById(R.id.txtProfileIcon);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/FontAwesome.otf");
        txtIcon.setTypeface(tf);
        return v;
    }


}
