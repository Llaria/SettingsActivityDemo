package com.kaicom.bargunsettingdemo.BarGunSettings;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.widget.Toast;

import com.kaicom.bargunsettingdemo.MainApp;
import com.kaicom.bargunsettingdemo.R;

import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_setting_contain,new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        private SwitchPreferenceCompat switchPreference;
        private android.support.v7.preference.Preference upgradePreference;
        private static final String SWITCH_KEY = "network";
        private static final String UPGRADE_KEY = "upgrade";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.pref_settings);
            initSwitchPreference();
            initUpgrade();
        }

        private void initSwitchPreference() {
            switchPreference = (SwitchPreferenceCompat) findPreference(SWITCH_KEY);
            switchPreference.setChecked(isWifi());
            switchPreference.setOnPreferenceChangeListener(new android.support.v7.preference.Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(android.support.v7.preference.Preference preference, Object newValue) {
                    final String key = preference.getKey();
                    if (SWITCH_KEY.equals(key)) {
                        if (switchPreference.isChecked() != (Boolean) newValue) {
                            if (!switchPreference.isChecked()) {
                                setWifiEnable(true);
                                showToast("Wifi已开启！");
                            } else {
                                setWifiEnable(false);
                                showToast("Wifi已关闭！");
                            }
                            boolean value = (Boolean) (newValue);
                            switchPreference.setChecked(value);
                        }
                    }
                    return true;
                }
            });
        }

        private void initUpgrade() {
            upgradePreference = findPreference(UPGRADE_KEY);
            upgradePreference.setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                    showToast("升级操作。。。");
                    return false;
                }
            });
        }

        @Override
        public void onResume() {
            super.onResume();
            switchPreference.setChecked(isWifi());
        }

    }

    /**
     * 判断Wifi是否开启
     */
    public static boolean isWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainApp.getMyApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 设置wifi开启还是关闭
     */
    public static void setWifiEnable(boolean enable) {
        WifiManager wifiManager = (WifiManager)MainApp.getMyApplication().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(enable);
    }

    public static void showToast(String content) {
        Toast.makeText(MainApp.getMyApplication(), content, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNumeric(String str) {
        if (isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String getVersionName() {
        String version = "";
        try {
            PackageManager packageManager = MainApp.getMyApplication().getPackageManager();
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(MainApp.getMyApplication().getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
