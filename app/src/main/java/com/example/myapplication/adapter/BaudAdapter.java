package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.myapplication.R;

import java.util.List;

public class BaudAdapter extends BaseAdapter {
    private List<String> datas;
    private Context context;

    public BaudAdapter(List<String > datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            //下拉视图
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_baud, viewGroup, false);
            viewHolder.textView = view.findViewById(R.id.baud_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(datas.get(i));
        return view;
    }

    private class ViewHolder {
        TextView textView;
    }
}
