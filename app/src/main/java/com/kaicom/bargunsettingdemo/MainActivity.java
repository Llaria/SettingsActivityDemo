package com.kaicom.bargunsettingdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kaicom.bargunsettingdemo.AutoSettings.SettingsActivity;
import com.kaicom.bargunsettingdemo.BarGunSettings.BarGunSettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1(View view) {
        startActivity(new Intent(this,SettingsActivity.class));
    }

    public void btn2(View view) {

    }

    public void btn3(View view) {
        startActivity(new Intent(this, BarGunSettingsActivity.class));
    }
}
