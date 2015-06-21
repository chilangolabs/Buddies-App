package com.chilangolabs.buddis;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chilangolabs.buddis.Adapters.AdapterListHome;

import org.json.JSONObject;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class HomeFragment extends Fragment {

    EditText edtxFind;
    AdapterListHome adapterListHome;

    RequestQueue rq;
    JsonObjectRequest jsonRequest;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MaterialNavigationDrawer) this.getActivity()).getToolbar().setTitle("");

        edtxFind = (EditText) view.findViewById(R.id.edtxHomeFind);

        rq = Volley.newRequestQueue(getActivity());

        makePetition();

        return view;
    }

    private void makePetition() {

        jsonRequest = new JsonObjectRequest(Request.Method.GET, "", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }


}
