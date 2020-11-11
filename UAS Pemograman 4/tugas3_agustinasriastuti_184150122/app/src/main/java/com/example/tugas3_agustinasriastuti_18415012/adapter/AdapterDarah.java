package com.example.tugas3_agustinasriastuti_18415012.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugas3_agustinasriastuti_18415012.model.ModelDarah;
import com.example.tugas3_agustinasriastuti_18415012.R;

import java.util.List;

public class AdapterDarah extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelDarah> item;

    public AdapterDarah(Activity activity, List<ModelDarah> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_darah, null);


        TextView name = (TextView) convertView.findViewById(R.id.textGolongan);
        TextView qty = (TextView) convertView.findViewById(R.id.textQty);
        TextView harga = (TextView) convertView.findViewById(R.id.textHarga);

        name.setText(item.get(position).getName());
        qty.setText(item.get(position).getQty());
        harga.setText("Rp." + item.get(position).getHarga());

        return convertView;
    }

}
