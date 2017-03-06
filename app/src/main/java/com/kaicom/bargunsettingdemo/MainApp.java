package com.kaicom.bargunsettingdemo;

import android.app.Application;

/**
 * @author Sundy
 * create at 2017/3/3 15:51
 */

public class MainApp extends Application{

    public static MainApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication(this);
    }

    private static void initApplication(MainApp mainApp) {
        app = mainApp;
    }

    public static Application getMyApplication(){
        return app;
    }
}
