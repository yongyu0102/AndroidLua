package com.example.zhangpeng.androidlua;

import android.app.Application;

/**
 * good
 * Created by zhangpeng on 2018/3/25.
 */

public class MainApplication extends Application {
    private static MainApplication mainApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication=this;
    }

    public static MainApplication getMainApplication(){
        return mainApplication;
    }
}
