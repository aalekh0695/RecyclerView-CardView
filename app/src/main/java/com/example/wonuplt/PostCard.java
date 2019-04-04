package com.example.wonuplt;

import java.io.Serializable;

public class PostCard implements Serializable {
    private String title, desc,time,action;
    private int actionIcon, timeZoneIndex, backgroundColor;

    public PostCard(String title, String desc, String time,int timeZoneIndex, String action, int actionIcon, int backgroundColor) {
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.action = action;
        this.actionIcon = actionIcon;
        this.timeZoneIndex = timeZoneIndex;
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    public int getActionIcon() {
        return actionIcon;
    }

    public int getTimeZoneIndex() {
        return timeZoneIndex;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
