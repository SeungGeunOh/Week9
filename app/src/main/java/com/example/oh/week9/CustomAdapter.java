package com.example.oh.week9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by OH on 2017-04-27.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<FruitData> fruitDatas;


    public CustomAdapter (Context context, ArrayList<FruitData> fruitDatas) {
        this.context = context;
        this.fruitDatas = fruitDatas;
    }
    @Override
    public int getCount() {
        return fruitDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return fruitDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.img1);
        TextView name = (TextView)convertView.findViewById(R.id.t1);
        TextView price = (TextView)convertView.findViewById(R.id.t2);

        imageView.setImageResource(fruitDatas.get(position).fImage);
        name.setText(fruitDatas.get(position).fName);
        price.setText(fruitDatas.get(position).fPrice + "원");

        if (MainActivity.check == false)
            price.setVisibility(View.INVISIBLE);
        else
            price.setVisibility(View.VISIBLE);
        return convertView;
    }
    public void setItem (int position, FruitData data){
        fruitDatas.set(position, data);
    }
}
