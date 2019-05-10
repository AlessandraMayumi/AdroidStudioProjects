package com.devmasterteam.relogiodecabeceira;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private ViewHolder mViewHolder = new ViewHolder();
    private Handler mHandler = new Handler();
    private Runnable mRunnable;
    private boolean mRunnableStopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.mViewHolder.mTextHourMinute = (TextView) this.findViewById(R.id.text_hour_minute);
        this.mViewHolder.mTextSecond = (TextView) this.findViewById(R.id.text_second);
        this.mViewHolder.mCheckBattery = (CheckBox) this.findViewById(R.id.check_battery);
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
                String hourMinuteFormat = String.format(Locale.getDefault(),"%02d:%02d",
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
        CheckBox mCheckBattery;
    }
}
