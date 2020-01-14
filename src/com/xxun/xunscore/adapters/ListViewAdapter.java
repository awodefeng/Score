package com.xxun.xunscore.adapters;

/**
 * Created by duanjinqian on 2020/1/7.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xxun.xunscore.R;
import com.xxun.xunscore.utils.Const;
import com.xxun.xunscore.view.ListViewItem;

import java.util.ArrayList;

import com.xxun.xunscore.activity.ScoreRecordActivity;

public class ListViewAdapter extends BaseAdapter {
    private static final String TAG = "ListViewAdapter";

    private Context mConText;

    private static final int TYPE_ITEM_COUNT = 2;

    private ArrayList<ListViewItem> listDatas;
    private LayoutInflater mLayoutInflater;
    private int mTotalScore;
    public ListViewAdapter(Context context, ArrayList<ListViewItem> listdatas) {
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
        ListViewItem listItem = listDatas.get(position);
        int itemType = getItemViewType(position);
        ViewHolderBag viewHolderBag ;
        ViewHolderTask viewHolderTask ;
        Log.d(TAG,"viewHolderTask itemType = "+itemType);
        Log.d(TAG,"getView listItem = "+listItem);
        Log.d(TAG,"getView listDatas = "+listDatas.size());
        Log.d(TAG,"getView listItem.getNam = "+listItem.getName());
        if (convertView == null) {
            switch (itemType){
                case Const.ITEM_BAG:
                    viewHolderBag = new ViewHolderBag();
                    convertView = mLayoutInflater.inflate(R.layout.list_item_bag, null);
                    viewHolderBag.myScore = (TextView)convertView.findViewById(R.id.myscore);
                    viewHolderBag.bagView = (ImageView)convertView.findViewById(R.id.bag);
                    viewHolderBag.scoreUseState = (ImageView)convertView.findViewById(R.id.use_state);
                    viewHolderBag.bagPageScore = (TextView)convertView.findViewById(R.id.bag_page_score);
                    viewHolderBag.bagPageScore.setText(getTotalScore()+"");
                    viewHolderBag.scoreUseState.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mConText, ScoreRecordActivity.class);
                            mConText.startActivity(intent);
                        }
                    });
                    viewHolderBag.myScore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mConText, ScoreRecordActivity.class);
                            mConText.startActivity(intent);
                        }
                    });
                    convertView.setTag(viewHolderBag);
                break;
                case Const.ITEM_TASK:
                    viewHolderTask = new ViewHolderTask();
                    convertView = mLayoutInflater.inflate(R.layout.list_item_task, null);
                    viewHolderTask.taskBtn = (Button)convertView.findViewById(R.id.taskbtn);
                    viewHolderTask.title = (TextView)convertView.findViewById(R.id.title);
                    viewHolderTask.title.setText(listItem.getName());
                    viewHolderTask.completed = (TextView)convertView.findViewById(R.id.completed);
                    viewHolderTask.completed.setText(mConText.getResources().getString(R.string.task_completed_detail,listItem.getTaskCompletedNum(),listItem.getTaskNum()));
                    if(listItem.getTaskScore()!=0){
                    	viewHolderTask.taskBtn.setText(mConText.getResources().getString(R.string.task_completed,Const.COMPLETED_NOT_OK));
                    }else{
                    	viewHolderTask.taskBtn.setText(mConText.getResources().getString(R.string.task_uncompleted,Const.COMPLETED_NOT_OK));
                    }
                    convertView.setTag(viewHolderTask);
                break;
            }
        }else{
            switch (itemType){
                case Const.ITEM_BAG:
                    viewHolderBag = (ViewHolderBag)convertView.getTag();
                    viewHolderBag.bagPageScore.setText(getTotalScore()+"");
                    viewHolderBag.scoreUseState = (ImageView)convertView.findViewById(R.id.use_state);
                break;
                case Const.ITEM_TASK:
                    viewHolderTask = (ViewHolderTask)convertView.getTag();
                    viewHolderTask.taskBtn = (Button)convertView.findViewById(R.id.taskbtn);
                    viewHolderTask.title = (TextView)convertView.findViewById(R.id.title);
                    viewHolderTask.title.setText(listItem.getName());
                    viewHolderTask.completed = (TextView)convertView.findViewById(R.id.completed);
                    viewHolderTask.completed.setText(mConText.getResources().getString(R.string.task_completed_detail,listItem.getTaskCompletedNum(),listItem.getTaskNum()));
                    if(listItem.getTaskCompletedNum() == listItem.getTaskNum()){
                        viewHolderTask.taskBtn.setText(mConText.getResources().getString(R.string.task_completed,Const.COMPLETED_OK));
                    }else{
                        viewHolderTask.taskBtn.setText(mConText.getResources().getString(R.string.task_uncompleted,Const.COMPLETED_NOT_OK));
                    }
                break;
            }
        }

        return convertView;
    }

    private int getTotalScore(){
        mTotalScore = 300;
        return mTotalScore;
    }

    class ViewHolderBag{
        TextView myScore;
        ImageView bagView;
        ImageView scoreUseState;
        TextView bagPageScore;
    }

    class ViewHolderTask{
        TextView title;
        TextView completed;
        Button taskBtn;
    }
}
