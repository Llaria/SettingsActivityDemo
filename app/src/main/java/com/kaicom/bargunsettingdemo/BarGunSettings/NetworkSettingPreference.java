package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * 网络设置
 * Created by sundi on 2017/1/9.
 */
@SuppressWarnings("unused")
public class NetworkSettingPreference extends SwitchPreferenceCompat {

    private Context context;

    public NetworkSettingPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onClick() {
        context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View switchView = holder.findViewById(android.support.v7.preference.R.id.switchWidget);
        switchView.setFocusable(true);
        switchView.setClickable(true);
    }
}
