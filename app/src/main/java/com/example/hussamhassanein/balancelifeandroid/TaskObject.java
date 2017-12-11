package com.example.hussamhassanein.balancelifeandroid;

/**
 * Created by HussamHassanein on 2017-12-06.
 */

public class TaskObject {
    private String  id;
    private String task;

    public TaskObject(String key,String task){
        this.id=key;
        this.task=task;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
