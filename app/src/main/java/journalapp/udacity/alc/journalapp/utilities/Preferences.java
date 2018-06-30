package journalapp.udacity.alc.journalapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String MAIN_CONFIG = "application_settings";
    private static final String TOKEN = "token";
    private static final String LOGGED = "logged";

    private static SharedPreferences settingsSharedPreference;

    public static void initSharedPreferences(Context context) {
        settingsSharedPreference = context.getSharedPreferences(MAIN_CONFIG, Context.MODE_PRIVATE);
    }

    public static void clearData() {
        if (null != settingsSharedPreference) {
            SharedPreferences.Editor editor = settingsSharedPreference.edit();
            editor.clear();
            editor.apply();
        }
    }

    public static void removeData(String key) {
        if (null != settingsSharedPreference) {
            SharedPreferences.Editor editor = settingsSharedPreference.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    public static String getToken() {
        return settingsSharedPreference.getString(TOKEN, "");
    }

    public static void setToken(String token) {
        SharedPreferences.Editor editor = settingsSharedPreference.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static Boolean getLogging() {
        return settingsSharedPreference.getBoolean(LOGGED, false);
    }

    public static void setLogging(Boolean logged) {
        SharedPreferences.Editor editor = settingsSharedPreference.edit();
        editor.putBoolean(LOGGED, logged);
        editor.apply();
    }
}
