package io.netbird.client.tool;

import android.content.Context;
import android.util.Log;

import io.netbird.client.tool.networks.NetworkToggleListener;

/**
 * Forwards network type changes (e.g. cellular to WiFi) to the Go core, which
 * debounces them and sweeps the service connections still bound to the old
 * network. Connections that reconnected on their own survive the sweep, so no
 * cancellation is needed here.
 */
class NetworkSwitchNotifier implements NetworkToggleListener {
    private static final String LOGTAG = NetworkSwitchNotifier.class.getSimpleName();

    private final Context context;
    private final EngineRunner engineRunner;

    NetworkSwitchNotifier(Context context, EngineRunner engineRunner) {
        this.context = context;
        this.engineRunner = engineRunner;
    }

    @Override
    public void onNetworkTypeChanged() {
        if (!engineRunner.isRunning()) {
            Log.d(LOGTAG, "engine not running, skipping network change notification");
            return;
        }
        Preferences preferences = new Preferences(context);
        if (preferences.isAutoDisconnectWifiEnabled()) {
            Log.d(LOGTAG, "Auto-disconnect on Wi-Fi enabled; suppressing network switch Go core reconnect");
            return;
        }
        Log.d(LOGTAG, "network type changed, notifying Go core");
        engineRunner.notifyNetworkChange();
    }
}
