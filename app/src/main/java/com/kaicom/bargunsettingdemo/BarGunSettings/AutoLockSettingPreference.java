package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.kaicom.bargunsettingdemo.R;

/**
 * 自动锁屏时间
 * Created by sundi on 2017/1/6.
 */

@SuppressWarnings("unused")
public class AutoLockSettingPreference extends Preference implements Preference.OnPreferenceClickListener{

    private Context context;
    private EditText etLockKeyboard;

    public AutoLockSettingPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.preference_auto_lock_setting, null);
        etLockKeyboard = (EditText) layout.findViewById(R.id.et_lock_keyboard);
        try {
            int time = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_OFF_TIMEOUT);
            etLockKeyboard.setText(String.valueOf(time / 1000));
        } catch (Settings.SettingNotFoundException e) {
            etLockKeyboard.setText("");
        }
        new AlertDialog.Builder(context)
                .setTitle("锁屏时间")
                .setView(layout)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setupKeyboardTimeout();
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

    /* 设置锁屏时间 */
    private void setupKeyboardTimeout() {
        if (checkTimeoutRule()) {
            int time = Integer.parseInt(etLockKeyboard.getText().toString());
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_OFF_TIMEOUT, time * 1000);
            MainActivity.showToast("保存成功！");
        } else {
            MainActivity.showToast("时间间隔大于等于180秒小于等于1200秒，请输入正确的时间间隔");
        }

    }

    /**
     * 检测输入框中输入的内容是否符合规范
     */
    private boolean checkTimeoutRule() {
        if (!MainActivity.isNumeric(etLockKeyboard.getText().toString().trim()))
            return false;
        int time = Integer.parseInt(etLockKeyboard.getText().toString().trim());
        return (time >= 180 && time <= 1200);
    }
}
