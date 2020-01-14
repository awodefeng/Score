package com.xxun.xunscore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.xxun.xunscore.R;
import com.xxun.xunscore.adapters.ListViewAdapter;
import com.xxun.xunscore.view.ListViewItem;
import com.xxun.xunscore.provider.ScoreProvider;
import java.util.ArrayList;
import java.util.HashMap;
import com.xxun.xunscore.utils.Const;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private ArrayList<ListViewItem> itemArrayList = new ArrayList<ListViewItem>();

    //test
    SharedPreferences preferences;
    private Editor editor;
    //end
    private int mTotalScore;
    String[] PROJECTION = new String[]{
            "task_name",
            "score_type",
            "task_action",
            "score_use_state",
            "task_get_score",
            "get_score_time",
            "upload_flag",
            "task_completed_num",
            "task_num"
    	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViewData();
    }

    private void initData(){
        mTotalScore = 0;
    }

    private void initViewData(){
        listView = (ListView)findViewById(R.id.list_view);
        listViewAdapter = new ListViewAdapter(MainActivity.this, getItemDatas());
        listView.setAdapter(listViewAdapter);
    }

    private ArrayList<ListViewItem> getItemDatas(){
        ContentResolver cr = getContentResolver();
        //test begin  7 size
        preferences = getSharedPreferences("score", Context.MODE_PRIVATE);
        if (preferences.getBoolean("firststart", true)) {
            editor = preferences.edit();
            editor.putBoolean("firststart", false);
            editor.commit();
            ContentValues values = new ContentValues();
            values.put("task_name", "每日任务");
            values.put("score_type", Const.ITEM_BAG);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 30);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 1);
            values.put("task_num", 3);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
            values.put("task_name", "计步");
            values.put("score_type", Const.ITEM_TASK);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 50);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 3);
            values.put("task_num", 11);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
            values.put("task_name", "运动");
            values.put("score_type", Const.ITEM_TASK);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 44);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 3);
            values.put("task_num", 6);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
            values.put("task_name", "交友");
            values.put("score_type", Const.ITEM_TASK);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 55);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 3);
            values.put("task_num", 3);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
            values.put("task_name", "小爱同学");
            values.put("score_type", Const.ITEM_TASK);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 66);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 3);
            values.put("task_num", 8);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
            values.put("task_name", "拍照识物");
            values.put("score_type", Const.ITEM_TASK);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 88);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 0);
            values.put("task_num", 1);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
            values.put("task_name", "发布朋友圈");
            values.put("score_type", Const.ITEM_TASK);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 77);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 1);
            values.put("task_num", 2);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
            values.put("task_name", "点赞朋友圈");
            values.put("score_type", Const.ITEM_TASK);
            values.put("task_action", 1);
            values.put("score_use_state", 1);
            values.put("task_get_score", 99);
            values.put("get_score_time", "20200109");
            values.put("upload_flag", 0);
            values.put("task_completed_num", 3);
            values.put("task_num", 5);
            cr.insert(ScoreProvider.CONTENT_URI_TASK, values);
            values.clear();
        }
        //end
        Cursor cursor = cr.query(ScoreProvider.CONTENT_URI_TASK, PROJECTION, null, null, null);
        while(cursor.moveToNext()){
            ListViewItem listViewTaskItem = new ListViewItem();
            listViewTaskItem.setName(cursor.getString(cursor.getColumnIndex("task_name")));
            listViewTaskItem.setTaskType(cursor.getInt(cursor.getColumnIndex("score_type")));
            listViewTaskItem.setTaskAction(cursor.getInt(cursor.getColumnIndex("task_action")));
            listViewTaskItem.setUseState(cursor.getInt(cursor.getColumnIndex("score_use_state")));
            listViewTaskItem.setGetTaskScore(cursor.getInt(cursor.getColumnIndex("task_get_score")));
            listViewTaskItem.setGetScoreTime(cursor.getString(cursor.getColumnIndex("get_score_time")));
            listViewTaskItem.setUploadFlag(cursor.getInt(cursor.getColumnIndex("upload_flag")));
            listViewTaskItem.setTaskNum(cursor.getInt(cursor.getColumnIndex("task_num")));
            listViewTaskItem.setTaskCompletedNum(cursor.getInt(cursor.getColumnIndex("task_completed_num")));
            itemArrayList.add(listViewTaskItem);
        }
        cursor.close();
        return itemArrayList;
    }
}
