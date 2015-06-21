package com.chilangolabs.buddis;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


public class ValidationActivity extends Fragment {

    EditText edtxSms;
    Button btnValidate;

    RequestQueue rq;
    JsonObjectRequest jsonRequest, jsonValidate;


    public ValidationActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_validation, container, false);

        ((MaterialNavigationDrawer) this.getActivity()).getToolbar().setTitle("");

        edtxSms = (EditText) v.findViewById(R.id.edtxValidationNumber);
        btnValidate = (Button) v.findViewById(R.id.btnValidateSms);

        rq = Volley.newRequestQueue(getActivity());

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepetitio(edtxSms.getText().toString());
            }
        });

        return v;
    }

    private void makepetitio(String number) {

        JSONObject json = new JSONObject();
        try {
            json.put("phone", "+52" + number);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonRequest = new JsonObjectRequest(Request.Method.POST, "http://buddies.chilangolabs.com/config/phone", json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                new MaterialDialog.Builder(getActivity())
                        .title("Ingresa el codigo de verificacion")
                        .customView(R.layout.dialog_sms, true)
                        .positiveText("Ok")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                EditText validate = (EditText) dialog.findViewById(R.id.edtxDialog);
                                makeValidation(validate.getText().toString());
                            }
                        }).build().show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(jsonRequest);

    }

    private void makeValidation(String validate) {

        JSONObject jsonvalidate = new JSONObject();
        try {
            jsonvalidate.put("code", validate);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonRequest = new JsonObjectRequest(Request.Method.POST, "http://buddies.chilangolabs.com/config/sms/validate", jsonvalidate, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getActivity(), "Numero telefonico validado", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }


}
