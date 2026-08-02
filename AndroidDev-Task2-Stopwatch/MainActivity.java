package com.example.unitconverter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStart, btnPause, btnReset, btnLap;
    private LinearLayout lapListLayout;

    private Handler handler = new Handler(Looper.getMainLooper());
    private long startTime = 0L;
    private long timeBuff = 0L;
    private long updateTime = 0L;
    private boolean isRunning = false;
    private int lapCount = 1;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long timeInMillis = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + timeInMillis;

            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            int hrs = mins / 60;
            secs = secs % 60;
            mins = mins % 60;
            int milliseconds = (int) (updateTime % 1000) / 10;

            tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d.%02d", hrs, mins, secs, milliseconds));
            handler.postDelayed(this, 30);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);
        btnLap = findViewById(R.id.btnLap);
        lapListLayout = findViewById(R.id.lapListLayout);

        btnStart.setOnClickListener(v -> startTimer());
        btnPause.setOnClickListener(v -> pauseTimer());
        btnReset.setOnClickListener(v -> resetTimer());
        btnLap.setOnClickListener(v -> recordLap());
    }

    private void startTimer() {
        if (!isRunning) {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            isRunning = true;

            btnStart.setEnabled(false);
            btnPause.setEnabled(true);
            btnReset.setEnabled(true);
            btnLap.setEnabled(true);
        }
    }

    private void pauseTimer() {
        if (isRunning) {
            timeBuff += SystemClock.uptimeMillis() - startTime;
            handler.removeCallbacks(runnable);
            isRunning = false;

            btnStart.setEnabled(true);
            btnPause.setEnabled(false);
            btnReset.setEnabled(true);
            btnLap.setEnabled(false);
        }
    }

    private void resetTimer() {
        handler.removeCallbacks(runnable);
        startTime = 0L;
        timeBuff = 0L;
        updateTime = 0L;
        isRunning = false;
        lapCount = 1;

        tvTimer.setText("00:00:00.00");
        lapListLayout.removeAllViews();

        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
        btnReset.setEnabled(false);
        btnLap.setEnabled(false);
    }

    private void recordLap() {
        if (isRunning) {
            TextView lapItem = new TextView(this);
            lapItem.setText(String.format(Locale.getDefault(), "Lap %d: %s", lapCount++, tvTimer.getText().toString()));
            lapItem.setTextColor(0xFFFFFFFF);
            lapItem.setTextSize(16);
            lapItem.setPadding(0, 8, 0, 8);
            lapListLayout.addView(lapItem, 0);
        }
    }
}

