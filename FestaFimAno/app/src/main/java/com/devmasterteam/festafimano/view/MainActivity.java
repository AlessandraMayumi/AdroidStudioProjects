package com.devmasterteam.festafimano.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devmasterteam.festafimano.R;
import com.devmasterteam.festafimano.constants.FimDeAnoConstants;
import com.devmasterteam.festafimano.util.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_foreground);

        this.mViewHolder.textDaysLeft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHolder.textToday = (TextView) findViewById(R.id.text_today);

        this.mViewHolder.buttonConfirm = (Button) findViewById(R.id.button_confirm);
        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndOfYear()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_confirm) {
            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE, presence);
            startActivity(intent);
        }
    }

    private int getDaysLeftToEndOfYear() {
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayDecember31 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayDecember31 - today;
    }

<<<<<<< Updated upstream
    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WONT_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

||||||| merged common ancestors
    private void verifyPresence() {
        String presence = this.mSecutiryPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WONT_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_confirm) {
            String presence = this.mSecutiryPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE, presence);
            startActivity(intent);
        }
    }

=======
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_confirm) {
            String presence = this.mSecutiryPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE, presence);
            startActivity(intent);
        }
    }

    private void verifyPresence() {
        String presence = this.mSecutiryPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
        if (presence.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else if (presence.equals(FimDeAnoConstants.CONFIRMED_WONT_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }

>>>>>>> Stashed changes
    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
