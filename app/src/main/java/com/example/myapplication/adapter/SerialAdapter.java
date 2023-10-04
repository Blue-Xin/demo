package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class SerialAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;

    public SerialAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view ==null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_serial, viewGroup, false);
            //把视图传入view中
            holder.view = view.findViewById(R.id.serial_name);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.view.setText(data.get(i));
        return view;
    }

    private class ViewHolder {
        TextView view;
    }
}
