package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;

/**
 * 查看版本
 * Created by sundi on 2017/1/9.
 */
@SuppressWarnings("unused")
public class VersionSettingPreference extends Preference implements Preference.OnPreferenceClickListener {

    private Context context;

    public VersionSettingPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        new AlertDialog.Builder(context)
                .setTitle("版本信息")
                .setMessage("软件版本："+ MainActivity.getVersionName())
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }
}
