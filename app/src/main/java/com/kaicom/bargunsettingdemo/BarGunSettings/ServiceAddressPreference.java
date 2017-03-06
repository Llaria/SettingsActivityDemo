package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.kaicom.bargunsettingdemo.R;

/**
 * 接口地址
 * Created by sundi on 2017/1/9.
 */
@SuppressWarnings("unused")
public class ServiceAddressPreference extends Preference implements Preference.OnPreferenceClickListener {

    private Context context;
    private EditText et_address;

    public ServiceAddressPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.preference_service_address_setting, null);
        et_address = (EditText) layout.findViewById(R.id.et_address);

        new AlertDialog.Builder(context)
                .setTitle("接口地址")
                .setView(layout)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BarGunSettingsActivity.showToast("接口地址设置成功！");
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

}
