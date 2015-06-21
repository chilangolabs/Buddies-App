package com.chilangolabs.buddis.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chilangolabs.buddis.Entitys.ItemListHome;
import com.chilangolabs.buddis.Entitys.ItemProfesionals;
import com.chilangolabs.buddis.R;
import com.devsmart.android.ui.HorizontalListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by gorro on 21/06/15.
 */
public class AdapterListHome extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ItemListHome> data = new ArrayList<>();

    public AdapterListHome(Context context, int layoutResourceId, ArrayList<ItemListHome> data) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class HolderView {
        TextView txtCode, txtName;
        HorizontalListView horizontalListView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View fila = convertView;
        HolderView holder = null;
        AdapterProfesional adapterProfesional;
        final ArrayList<ItemProfesionals> itemProfesionalses = new ArrayList<>();

        if (fila == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            fila = inflater.inflate(layoutResourceId, parent, false);
            holder = new HolderView();
            holder.txtCode = (TextView) fila.findViewById(R.id.txtItemHomeCode);
            holder.txtName = (TextView) fila.findViewById(R.id.txtItemHomeName);
            holder.horizontalListView = (HorizontalListView) fila.findViewById(R.id.listviewItemHome);
            fila.setTag(holder);
        } else {
            holder = (HolderView) fila.getTag();
        }

        ItemListHome item = (ItemListHome) data.get(position);
        try {
            holder.txtCode.setText(item.getJson().getString("code"));
            holder.txtName.setText(item.getJson().getString("name"));
            JSONArray jsonProfesionalsAr = item.getJson().getJSONArray("profesionals");
            for (int i = 0; i < jsonProfesionalsAr.length(); i++) {
                itemProfesionalses.add(new ItemProfesionals(jsonProfesionalsAr.getJSONObject(i).getString("name"), jsonProfesionalsAr.getJSONObject(i).getString("id"), jsonProfesionalsAr.getJSONObject(i).getString("image")));
            }
            adapterProfesional = new AdapterProfesional(context, R.layout.item_profesionals, itemProfesionalses);
            holder.horizontalListView.setAdapter(adapterProfesional);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, itemProfesionalses.get(i).getId(), Toast.LENGTH_SHORT).show();
            }
        });

        return fila;
    }
}
