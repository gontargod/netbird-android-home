package io.netbird.client.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String ANONYMIZE_LEVEL_NONE = "none";

    public static final String ANONYMIZE_LEVEL_DEFAULT = "default";

    public static final String ANONYMIZE_LEVEL_STRICT = "strict";

    private final String keyTraceLog = "tracelog";

    private final String keyForceRelayConnection = "isConnectionForceRelayed";

    private final String keyAnonymizeLevel = "anonymizeLevel";

    private final String keyAutoConnectMobileData = "autoConnectMobileData";

    private final String keyAutoDisconnectWifi = "autoDisconnectWifi";

    private final String keyTrustedWifiSSID = "trustedWifiSSID";

    private final String keyUserManuallyDisconnected = "userManuallyDisconnected";

    private final SharedPreferences sharedPref;

    public Preferences(Context context) {
       sharedPref = context.getSharedPreferences("netbird", Context.MODE_PRIVATE);
    }

    public boolean isTraceLogEnabled() {
       return sharedPref.getBoolean(keyTraceLog, false);
    }
    public void enableTraceLog() {
        sharedPref.edit().putBoolean(keyTraceLog, true).apply();
    }

    public void disableTraceLog() {
        sharedPref.edit().putBoolean(keyTraceLog, false).apply();
    }

    public String getAnonymizeLevel() {
        return sharedPref.getString(keyAnonymizeLevel, ANONYMIZE_LEVEL_DEFAULT);
    }

    public void setAnonymizeLevel(String level) {
        sharedPref.edit().putString(keyAnonymizeLevel, level).apply();
    }

    public boolean isConnectionForceRelayed() {
        return sharedPref.getBoolean(keyForceRelayConnection, true);
    }

    public void enableForcedRelayConnection() {
        sharedPref.edit().putBoolean(keyForceRelayConnection, true).apply();
    }

    public void disableForcedRelayConnection() {
        sharedPref.edit().putBoolean(keyForceRelayConnection, false).apply();
    }

    public boolean isAutoConnectMobileDataEnabled() {
        return sharedPref.getBoolean(keyAutoConnectMobileData, false);
    }

    public void setAutoConnectMobileDataEnabled(boolean enabled) {
        sharedPref.edit().putBoolean(keyAutoConnectMobileData, enabled).apply();
    }

    public boolean isAutoDisconnectWifiEnabled() {
        return sharedPref.getBoolean(keyAutoDisconnectWifi, false);
    }

    public void setAutoDisconnectWifiEnabled(boolean enabled) {
        sharedPref.edit().putBoolean(keyAutoDisconnectWifi, enabled).apply();
    }

    public String getTrustedWifiSSID() {
        return sharedPref.getString(keyTrustedWifiSSID, "");
    }

    public void setTrustedWifiSSID(String ssid) {
        sharedPref.edit().putString(keyTrustedWifiSSID, ssid != null ? ssid.trim() : "").apply();
    }

    public boolean isUserManuallyDisconnected() {
        return sharedPref.getBoolean(keyUserManuallyDisconnected, false);
    }

    public void setUserManuallyDisconnected(boolean disconnected) {
        sharedPref.edit().putBoolean(keyUserManuallyDisconnected, disconnected).apply();
    }

    public static String defaultServer() {
        return "https://api.netbird.io";
    }
}
