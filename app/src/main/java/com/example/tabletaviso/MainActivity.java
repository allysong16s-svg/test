package com.example.tabletaviso;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String PREFS = "tablet_aviso";
    private static final String KEY_SHOWN = "aviso_lido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);

        if (prefs.getBoolean(KEY_SHOWN, false)) {
            finish();
            return;
        }

        showNotice();
    }

    private void showNotice() {
        Window window = getWindow();
        window.setStatusBarColor(Color.rgb(247, 247, 247));
        window.setNavigationBarColor(Color.rgb(247, 247, 247));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(28), dp(32), dp(28), dp(24));
        root.setBackgroundColor(Color.rgb(247, 247, 247));

        ScrollView scroll = new ScrollView(this);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView icon = new TextView(this);
        icon.setText("ⓘ");
        icon.setTextSize(42);
        icon.setTextColor(Color.rgb(26, 115, 232));
        icon.setGravity(Gravity.CENTER);
        content.addView(icon, new LinearLayout.LayoutParams(-1, dp(65)));

        TextView title = new TextView(this);
        title.setText("Aviso sobre este tablet");
        title.setTextSize(26);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(Color.rgb(32, 33, 36));
        title.setGravity(Gravity.CENTER);
        content.addView(title, marginParams(-1, -2, 0, 0, 0, 22));

        TextView message = new TextView(this);
        message.setText(
                "Este aparelho foi vendido com informações incorretas sobre suas especificações.\n\n" +
                "O armazenamento real do tablet é de aproximadamente 32 GB, apesar das informações apresentadas na venda.\n\n" +
                "O sistema original também foi substituído por uma versão limpa do Android para melhorar a experiência de uso.\n\n" +
                "Este aviso será exibido apenas uma vez."
        );
        message.setTextSize(17);
        message.setTextColor(Color.rgb(60, 60, 60));
        message.setLineSpacing(0f, 1.15f);
        content.addView(message, marginParams(-1, -2, 0, 0, 0, 28));

        scroll.addView(content);

        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        Button button = new Button(this);
        button.setText("ENTENDI");
        button.setTextSize(14);
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        button.setBackgroundColor(Color.rgb(26, 115, 232));

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(-1, dp(52));
        buttonParams.topMargin = dp(16);
        root.addView(button, buttonParams);

        button.setOnClickListener(v -> {
            getSharedPreferences(PREFS, MODE_PRIVATE)
                    .edit()
                    .putBoolean(KEY_SHOWN, true)
                    .apply();

            finishAndRemoveTask();
        });

        setContentView(root);
    }

    private LinearLayout.LayoutParams marginParams(
            int width, int height,
            int left, int top, int right, int bottom) {

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(width, height);
        p.setMargins(dp(left), dp(top), dp(right), dp(bottom));
        return p;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
