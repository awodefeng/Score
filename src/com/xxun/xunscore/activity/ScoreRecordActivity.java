package com.xxun.xunscore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.xxun.xunscore.R;
import com.xxun.xunscore.view.ListViewItem;
import com.xxun.xunscore.adapters.RecordListViewAdapter;

import java.util.ArrayList;

/**
 * Created by duanjinqian on 2020/1/11.
 */

public class ScoreRecordActivity extends Activity {

    private ListView recordListView;
    private RecordListViewAdapter recordListViewAdapter;
    private ArrayList<ListViewItem> recordItemArrayList = new ArrayList<ListViewItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initViewData();
    }

    private void initViewData(){
        recordListView = (ListView)findViewById(R.id.record_list_view);
        recordListViewAdapter = new RecordListViewAdapter(ScoreRecordActivity.this, getItemDatas());
        recordListView.setAdapter(recordListViewAdapter);
    }

    private ArrayList<ListViewItem> getItemDatas(){

        return recordItemArrayList;
    }
}
