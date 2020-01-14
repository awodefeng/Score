package com.xxun.xunscore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.xxun.xunscore.view.ListViewItem;
import java.util.ArrayList;

/**
 * Created by duanjinqian on 2020/1/11.
 */

public class RecordListViewAdapter extends BaseAdapter {

    private static final String TAG = "RecordListViewAdapter";
    private static final int TYPE_ITEM_COUNT = 2;

    private Context mConText;
    private ArrayList<ListViewItem> listDatas;
    private LayoutInflater mLayoutInflater;
    public RecordListViewAdapter(Context context, ArrayList<ListViewItem> listdatas) {
        mConText = context;
        listDatas = listdatas;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position){
        return listDatas.get(position).getTaskType();
    }

    @Override
    public int getViewTypeCount(){
        return TYPE_ITEM_COUNT;
    }

    @Override
    public int getCount(){
        return listDatas.size();
    }

    @Override
    public Object getItem(int position){
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        return convertView;
    }
}
