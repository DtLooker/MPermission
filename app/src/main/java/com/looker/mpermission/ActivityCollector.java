package com.looker.mpermission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by looker on 2017/1/1.
 */

public class ActivityCollector {

    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(List<Activity> activities){
        for (Activity activity : activities) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    public static Activity getTopActivity(){
        if (activities.isEmpty()){
            return null;
        }else {
            return activities.get(activities.size() - 1);
        }
    }

}
