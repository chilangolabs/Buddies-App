package com.chilangolabs.buddis.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chilangolabs.buddis.Entitys.ItemListHome;
import com.chilangolabs.buddis.Entitys.ItemProfesionals;
import com.chilangolabs.buddis.R;
import com.squareup.picasso.Picasso;

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
    Typeface tp;

    public AdapterListHome(Context context, int layoutResourceId, ArrayList<ItemListHome> data) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
        tp = Typeface.createFromAsset(context.getAssets(), "fonts/FontAwesome.otf");
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
        LinearLayout horizontalListView;
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
            holder.horizontalListView = (LinearLayout) fila.findViewById(R.id.content_profesionals);
            fila.setTag(holder);
        } else {
            holder = (HolderView) fila.getTag();
        }

        ItemListHome item = (ItemListHome) data.get(position);
        try {
            holder.txtCode.setText(item.getJson().getString("icon"));
            holder.txtCode.setTypeface(tp);
            holder.txtName.setText(item.getJson().getString("title"));
            final JSONArray jsonProfesionalsAr = item.getJson().getJSONArray("users");

            for (int i = 0; i < jsonProfesionalsAr.length(); i++) {
                View profesionalItem = LayoutInflater.from(context).inflate(R.layout.item_profesionals, null);
                TextView txtProfesional = (TextView) profesionalItem.findViewById(R.id.txtProfesional);
                ImageView imgProfesional = (ImageView) profesionalItem.findViewById(R.id.imgProfesional);
                txtProfesional.setText(jsonProfesionalsAr.getJSONObject(i).getString("name"));
                Picasso.with(context).load("http://buddies.chilangolabs.com" + jsonProfesionalsAr.getJSONObject(i).getString("img")).placeholder(R.drawable.cargando).error(R.drawable.cargando).into(imgProfesional);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(10, 0, 10, 0);
//                imgProfesional.setImageResource(R.drawable.mario);
                holder.horizontalListView.addView(profesionalItem, layoutParams);
                final int pos = i;
                holder.horizontalListView.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Toast.makeText(context, jsonProfesionalsAr.getJSONObject(pos).getString("id"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fila;
    }
}
