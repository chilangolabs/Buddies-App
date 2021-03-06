package com.chilangolabs.buddis.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilangolabs.buddis.Entitys.ItemProfesionals;
import com.chilangolabs.buddis.R;

import java.util.ArrayList;

/**
 * Created by gorro on 21/06/15.
 */
public class AdapterProfesional extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ItemProfesionals> data = new ArrayList<>();

    public AdapterProfesional(Context context, int layoutResourceId, ArrayList<ItemProfesionals> data) {
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
        TextView txtName;
        ImageView imgProfesional;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View fila = convertView;
        HolderView holder = null;

        if (fila == null) {
//            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            fila = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, null); //inflater.inflate(layoutResourceId, parent, false);
            holder = new HolderView();
            holder.txtName = (TextView) fila.findViewById(R.id.txtProfesional);
            holder.imgProfesional = (ImageView) fila.findViewById(R.id.imgProfesional);
            fila.setTag(holder);
        } else {
            holder = (HolderView) fila.getTag();
        }

        ItemProfesionals item = (ItemProfesionals) data.get(position);
        Log.e("name", item.getName());
//        holder.txtName.setText(item.getName());
//        holder.imgProfesional.setImageResource(R.drawable.mario);
//        Picasso.with(context).load("http://buddies.chilangolabs.com" + item.getImage()).placeholder(R.drawable.mario).error(R.drawable.mario).into(holder.imgProfesional);

        return fila;
    }
}
