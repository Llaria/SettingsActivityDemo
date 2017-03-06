package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.kaicom.bargunsettingdemo.R;


/**
 * 自动上传时间设置
 * Created by sundi on 2017/1/6.
 */

@SuppressWarnings("unused")
public class AutoUploadPreference extends Preference implements Preference.OnPreferenceClickListener{

    private Context context;
    private EditText etLockKeyboard;

    public AutoUploadPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnPreferenceClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onPreferenceClick(Preference preference) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.preference_auto_lock_setting, null);
        etLockKeyboard = (EditText) layout.findViewById(R.id.et_lock_keyboard);
        TextView textView = (TextView) layout.findViewById(R.id.tv_time);
        textView.setText("分");
        etLockKeyboard.setText(/*CXPreferences.getInstance().getAutoUploadTime()+*/"");
        etLockKeyboard.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        new AlertDialog.Builder(context)
                .setTitle("自动上传时间")
                .setView(layout)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            /*CXPreferences.getInstance().setAutoUploadTime(Integer.parseInt(etLockKeyboard.getText().toString().trim()));
                            AlarmClockManager.getInstance(context).changeUploadAlarm(Integer.parseInt(etLockKeyboard.getText().toString().trim()));*/
                            BarGunSettingsActivity.showToast("上传时间设置成功！");
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                }).show();
        return false;
    }
}
