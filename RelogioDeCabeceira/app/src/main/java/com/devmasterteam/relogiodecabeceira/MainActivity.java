package com.devmasterteam.relogiodecabeceira;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewHolder mViewHolder = new ViewHolder();
    private Handler mHandler = new Handler();
    private Runnable mRunnable;
    private boolean mRunnableStopped = false;
    private boolean mIsBatteryOn = true;

    private BroadcastReceiver mBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            mViewHolder.mTextBatteryLevel.setText(String.valueOf(level) + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.mViewHolder.mTextHourMinute = (TextView) this.findViewById(R.id.text_hour_minute);
        this.mViewHolder.mTextSecond = (TextView) this.findViewById(R.id.text_second);
        this.mViewHolder.mTextBatteryLevel = (TextView) this.findViewById(R.id.text_battery_level);
        this.mViewHolder.mCheckBattery = (CheckBox) this.findViewById(R.id.check_battery);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.registerReceiver(this.mBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        this.mViewHolder.mCheckBattery.setChecked(true);

        this.setListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

        mRunnableStopped = false;
        Log.d("resume", "resume");

        this.startBedside();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mRunnableStopped = true;
        Log.d("stop=", "stop");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_battery) {
            this.toggleCheckBattery();
        }
    }

    private void toggleCheckBattery() {
        if (this.mIsBatteryOn) {
            this.mIsBatteryOn = false;
            this.mViewHolder.mTextBatteryLevel.setVisibility(View.GONE);
        } else {
            this.mIsBatteryOn = true;
            this.mViewHolder.mTextBatteryLevel.setVisibility(View.VISIBLE);
        }
    }

    private void setListener() {
        this.mViewHolder.mCheckBattery.setOnClickListener(this);
    }

    private void startBedside() {
        final Calendar calendar = Calendar.getInstance();

        this.mRunnable = new Runnable() {
            @Override
            public void run() {
                if (mRunnableStopped) {
                    Log.d("saiu=", "saiu");
                    return;
                }
                Log.d("run=", "run");
                calendar.setTimeInMillis(System.currentTimeMillis());
                String hourMinuteFormat = String.format(Locale.getDefault(), "%02d:%02d",
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                String secondFormat = String.format(Locale.getDefault(), "%02d",
                        calendar.get(Calendar.SECOND));

                mViewHolder.mTextHourMinute.setText(hourMinuteFormat);
                mViewHolder.mTextSecond.setText(secondFormat);

                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - (now % 1000));

                mHandler.postAtTime(mRunnable, next);
            }
        };
        this.mRunnable.run();
    }

    private static class ViewHolder {
        TextView mTextHourMinute;
        TextView mTextSecond;
        TextView mTextBatteryLevel;
        CheckBox mCheckBattery;
    }
}
