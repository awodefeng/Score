package com.xxun.xunscore.view;

import java.util.HashMap;

/**
 * Created by duanjinqian on 2020/1/7.
 */

public class ListViewItem {
    private int mId;
    private int mTaskType;//同一个list里显示两种类型的格式 bag/task
    private String mName;
    private int mTaskAction;
    private int mUseState;
    private int mGetTaskScore;
    private String mGetScoreTime;
    private int mUploadFlag;
    private int mTaskNum;
    private int mTaskCompletedNum;

//    public HashMap<String,Object> map ;
//    public ListViewItem(int itemType, HashMap<String, Object> map) {
    public ListViewItem() {

    }
    
    public void setId(int id){
    	mId = id;
    }
    public int getId(){
    		return mId;
    }
    
    public void setTaskType(int tasktype){
        mTaskType = tasktype;
    }
    
    public int getTaskType(){
    	return mTaskType;
    }
    
    public void setName(String name){
    	mName = name;
    }
    
    public String getName(){
    	return mName;
    }

    public void setTaskAction(int action){
        mTaskAction = action;
    }
    
    public int getTaskAction(){
    	return mTaskAction;
    }
    
    public void setUseState(int useState){
        mUseState = useState;
    }
    
    public int getUseState(){
    	return mUseState;
    }
    
    public void setGetTaskScore(int taskScore){
        mGetTaskScore = taskScore;
    }
    
    public int getTaskScore(){
    	return mGetTaskScore;
    }
    
    public void setGetScoreTime(String time){
        mGetScoreTime = time;
    }
    
    public String getGetScoreTime(){
    	return mGetScoreTime;
    }

    public void setUploadFlag(int flag){
        mUploadFlag = flag;
    }

    public int getUploadFlag(){
        return mUploadFlag;
    }

    public void setTaskCompletedNum(int taskCompletedNum){
        mTaskCompletedNum = taskCompletedNum;
    }

    public int getTaskCompletedNum(){
        return mTaskCompletedNum;
    }

    public void setTaskNum(int taskNum){
        mTaskNum = taskNum;
    }

    public int getTaskNum(){
        return mTaskNum;
    }

}
