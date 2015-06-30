package com.chilangolabs.buddis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chilangolabs.buddis.Adapters.AdapterCard;
import com.chilangolabs.buddis.Entitys.ItemCard;
import com.chilangolabs.buddis.OwnElements.TextBuddis;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

                try {
                    txtProfileName.setText(response.getString("displayName"));
                    txtProfileExperience.setText(response.getJSONArray("talents").get(0).toString());
                    Picasso.with(ProfileDetailActivity.this).load("http://buddies.chilangolabs.com" + response.getString("fbPicture")).placeholder(R.drawable.cargando).error(R.drawable.cargando).into(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayList<ItemCard> itemCards = new ArrayList<>();

                itemCards.add(new ItemCard(R.drawable.cantoya, "Globos de cantoya reciclados"));
                itemCards.add(new ItemCard(R.drawable.lampara, "Construcción de lamparas"));
                AdapterCard adapter = new AdapterCard(ProfileDetailActivity.this, R.layout.item_card_job, itemCards);

                listUserProfileJobs.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ProfileDetailActivity.this, "Algo salio mal, intenta de nuevo", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        rq.getCache().clear();
        rq.add(jsonRequest);

    }

}
