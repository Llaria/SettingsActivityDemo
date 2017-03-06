package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;

/**
 * 升级功能
 * Created by sundi on 2017/1/9.
 */
@SuppressWarnings("unused")
public class UpdateSettingPreference extends Preference implements Preference.OnPreferenceClickListener {

    private Context context;

    public UpdateSettingPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        MainActivity.showToast("升级预留！");
        return false;
    }
}
