package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;

/**
 * 接口地址
 * Created by sundi on 2017/1/9.
 */
@SuppressWarnings("unused")
public class ServiceAddressPreference extends Preference implements Preference.OnPreferenceClickListener {

    private Context context;

    public ServiceAddressPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        //Intent intent = new Intent(context,ServiceAddressActivity.class);
        return false;
    }

}
