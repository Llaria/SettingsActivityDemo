package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.kaicom.bargunsettingdemo.R;

import static android.content.Context.AUDIO_SERVICE;

/**
 * 声音设置
 * Created by sundi on 2017/1/6.
 */
@SuppressWarnings("unused")
public class SoundSettingPreference extends Preference implements Preference.OnPreferenceClickListener {

    private Context context;
    private AudioManager am;
    private ProgressBar progressBarSetting;

    public SoundSettingPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnPreferenceClickListener(this);
        am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.preference_sound_setting, null);
        Button btn_minus = (Button) layout.findViewById(R.id.btn_setting_minus);
        Button btn_plus = (Button) layout.findViewById(R.id.btn_setting_plus);
        btn_plus.requestFocus();
        progressBarSetting = (ProgressBar) layout.findViewById(R.id.progress_bar_setting);
        progressBarSetting.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        // 获取当前铃声模式以及音量大小,设置到进度条
        progressBarSetting.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        new AlertDialog.Builder(context)
                .setTitle("声音调节")
                .setView(layout)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus();
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus();
            }
        });
        return false;
    }

    private void minus() {
        // 减少音量,更新进度条
        am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_LOWER, 0);
        progressBarSetting.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        //SoundManager.getInstance().playKeyAndTouchSound();
    }

    private void plus() {
        am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE, 0);
        progressBarSetting.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        //SoundManager.getInstance().playKeyAndTouchSound();
    }
}

