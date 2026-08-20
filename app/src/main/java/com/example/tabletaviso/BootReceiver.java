package com.example.tabletaviso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

public class BootReceiver extends BroadcastReceiver {

    private static final String PREFS = "tablet_aviso";
    private static final String KEY_SHOWN = "aviso_lido";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (!Intent.ACTION_BOOT_COMPLETED.equals(action)
                && !Intent.ACTION_USER_UNLOCKED.equals(action)) {
            return;
        }

        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        if (prefs.getBoolean(KEY_SHOWN, false)) {
            return;
        }

        Intent launch = new Intent(context, MainActivity.class);
        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);

        try {
            context.startActivity(launch);
        } catch (Exception ignored) {
            // Em Android 10, o sistema pode impedir uma Activity de
            // iniciar diretamente do background. Nesse caso, abra o app
            // manualmente uma vez ou instale-o como app de sistema.
        }
    }
}
