package com.chilangolabs.buddis;


import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chilangolabs.buddis.Adapters.AdapterCard;
import com.chilangolabs.buddis.Entitys.ItemCard;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class UserProfileFragment extends Fragment {

    TextView txtIcon;
    ListView listView;

//    JsonObjectRequest jsonReques;
//    RequestQueue rq;


    public UserProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ((MaterialNavigationDrawer) this.getActivity()).getToolbar().setTitle("");

//        rq = Volley.newRequestQueue(getActivity());

//        makePetition();

        txtIcon = (TextView) v.findViewById(R.id.txtProfileIcon);
        listView = (ListView) v.findViewById(R.id.listUserProfileJobs);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/FontAwesome.otf");
        txtIcon.setTypeface(tf);

        ArrayList<ItemCard> itemCards = new ArrayList<>();

        itemCards.add(new ItemCard(R.drawable.cantoya, "Globos de cantoya reciclados"));
        itemCards.add(new ItemCard(R.drawable.lampara, "Construcción de lamparas"));
        AdapterCard adapter = new AdapterCard(getActivity(), R.layout.item_card_job, itemCards);

        listView.setAdapter(adapter);

        return v;
    }

    private void makePetition() {

    }


}
