package com.chilangolabs.buddis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chilangolabs.buddis.OwnElements.TextBuddis;
import com.pkmmte.view.CircularImageView;

import org.json.JSONObject;

public class ProfileDetailActivity extends AppCompatActivity {


    RequestQueue rq;
    JsonObjectRequest jsonRequest;

    MaterialDialog.Builder progressDialogBuild;
    MaterialDialog progressDialog;

    TextBuddis txtProfileName, txtProfileVerificated, txtProfileExperience;
    CircularImageView view;
    ListView listUserProfileJobs;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        rq = Volley.newRequestQueue(ProfileDetailActivity.this);

        txtProfileName = (TextBuddis) findViewById(R.id.txtProfileName);
        txtProfileVerificated = (TextBuddis) findViewById(R.id.txtProfileVerificated);
        txtProfileExperience = (TextBuddis) findViewById(R.id.txtProfileExperience);

        view = (CircularImageView) findViewById(R.id.view);

        listUserProfileJobs = (ListView) findViewById(R.id.listUserProfileJobs);

        progressDialogBuild = new MaterialDialog.Builder(ProfileDetailActivity.this)
                .content(R.string.wait_dialog)
                .cancelable(false)
                .progress(true, 0);

        progressDialog = progressDialogBuild.build();

        id = getIntent().getExtras().getString("id");

        makePetition();

    }

    private void makePetition() {
        progressDialog.show();

        jsonRequest = new JsonObjectRequest(Request.Method.GET, "http://buddies.chilangolabs.com/profile?id=" + id, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();
                Log.e("response ", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });

        rq.add(jsonRequest);

    }

}
