package com.chilangolabs.buddis;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chilangolabs.buddis.Adapters.AdapterListHome;
import com.chilangolabs.buddis.Entitys.ItemListHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class HomeFragment extends Fragment {

    EditText edtxFind;
    ListView listHome;
    AdapterListHome adapterListHome;

    RequestQueue rq;
    JsonObjectRequest jsonRequest;

    MaterialDialog.Builder progressDialogBuild;
    MaterialDialog progressDialog;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MaterialNavigationDrawer) this.getActivity()).getToolbar().setTitle("");

        edtxFind = (EditText) view.findViewById(R.id.edtxHomeFind);
        listHome = (ListView) view.findViewById(R.id.listHome);

        rq = Volley.newRequestQueue(getActivity());

        progressDialogBuild = new MaterialDialog.Builder(getActivity())
                .content(R.string.wait_dialog)
                .cancelable(false)
                .progress(true, 0);

        progressDialog = progressDialogBuild.build();

        makePetition();

        return view;
    }

    private void makePetition() {

        progressDialog.show();

        jsonRequest = new JsonObjectRequest(Request.Method.GET, "http://buddies.chilangolabs.com/explore", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                final ArrayList<ItemListHome> item = new ArrayList<>();
                try {
                    JSONArray jsAr = response.getJSONArray("categories");
                    for (int i = 0; i < jsAr.length(); i++) {
                        item.add(new ItemListHome(jsAr.getJSONObject(i)));
                    }
                    adapterListHome = new AdapterListHome(getActivity(), R.layout.item_list_home, item);
                    listHome.setAdapter(adapterListHome);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Algo fallo, intenta de nuevo", Toast.LENGTH_SHORT).show();
            }
        });

        rq.getCache().clear();
        rq.add(jsonRequest);

    }


}
