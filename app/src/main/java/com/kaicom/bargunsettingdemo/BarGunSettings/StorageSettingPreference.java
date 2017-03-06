package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;

import com.kaicom.bargunsettingdemo.R;


/**
 * 剩余存储
 * Created by sundi on 2017/1/9.
 */
@SuppressWarnings("unused")
public class StorageSettingPreference extends Preference implements Preference.OnPreferenceClickListener {

    private Context context;

    public StorageSettingPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        int mb = 1024 * 1024;
//        double systemSurplus = MemorySpaceCheck.getInstance()
//                .getSystemAvailableSize() / mb;
//        double dataNumber = MemorySpaceCheck.getInstance()
//                .getSystemAvailableSize() / 200;
        String byteString = context.getString(R.string.megabytes_about);
        String sb = context.getString(R.string.current_surplus) +
                "0" + byteString +
                "0" +
                context.getString(R.string.memory_hint);

        new AlertDialog.Builder(context)
                .setTitle("剩余存储")
                .setMessage(sb)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

}
