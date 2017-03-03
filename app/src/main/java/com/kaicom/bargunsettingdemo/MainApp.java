package com.kaicom.bargunsettingdemo;

import android.app.Application;

/**
 * @author Sundy
 * create at 2017/3/3 15:51
 */

public class MainApp extends Application{

    private static MainApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    protected static Application getMyApplication(){
        return app;
    }
}
