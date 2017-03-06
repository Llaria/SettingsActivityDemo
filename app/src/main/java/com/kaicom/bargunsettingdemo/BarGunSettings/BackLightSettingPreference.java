package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import com.kaicom.bargunsettingdemo.R;


/**
 * 背光设置
 * Created by sundi on 2017/1/6.
 */

@SuppressWarnings("unused")
public class BackLightSettingPreference extends Preference implements Preference.OnPreferenceClickListener {

    private Context context;
    private SeekBar seekBar;

    public BackLightSettingPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.preference_backlight_setting, null);
        seekBar = (SeekBar) layout.findViewById(R.id.back_light_seek_bar);
        seekBar.setMax(255);
        int normal = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);
        seekBar.setProgress(normal);

        new AlertDialog.Builder(context)
                .setTitle("背光调节")
                .setView(layout)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

        seekBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                setScreenLight();
                return false;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                setScreenLight();
            }
        });
        return false;
    }

    /**
     * 设置亮度
     */
    private void setScreenLight() {
        int tmpInt = seekBar.getProgress();
        if (tmpInt < 30) {
            tmpInt = 30;
        }
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, tmpInt);
    }
}
