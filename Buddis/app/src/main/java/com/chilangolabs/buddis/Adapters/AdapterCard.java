package com.chilangolabs.buddis.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilangolabs.buddis.Entitys.ItemCard;
import com.chilangolabs.buddis.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gorro on 21/06/15.
 */
public class AdapterCard extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ItemCard> data = new ArrayList<>();

    public AdapterCard(Context context, int layoutResourceId, ArrayList<ItemCard> data) {
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
        TextView txtJob;
        ImageView imgJob;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View fila = convertView;
        HolderView holder = null;

        if (fila == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            fila = inflater.inflate(layoutResourceId, parent, false);
            holder = new HolderView();
            holder.txtJob = (TextView) fila.findViewById(R.id.txtItemCard);
            holder.imgJob = (ImageView) fila.findViewById(R.id.imgItemCard);
            fila.setTag(holder);
        } else {
            holder = (HolderView) fila.getTag();
        }

        ItemCard item = data.get(position);

        holder.txtJob.setText(item.getTitle());
//        if (item.getImg().isEmpty()) {
            Picasso.with(context).load(item.getImgInt()).placeholder(R.drawable.cargando).error(R.drawable.cargando).into(holder.imgJob);
//        } else {
//            Picasso.with(context).load("http://buddies.chilangolabs.com" + item.getImg()).placeholder(R.drawable.cargando).error(R.drawable.cargando).into(holder.imgJob);
//        }

        return fila;
    }
}
