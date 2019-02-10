package com.devmasterteam.festafimano.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.devmasterteam.festafimano.R;
import com.devmasterteam.festafimano.constants.FimDeAnoConstants;
import com.devmasterteam.festafimano.util.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_foreground);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkboxParticipate = (CheckBox) findViewById(R.id.checkbox_participate);
        this.mViewHolder.checkboxParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.checkbox_participate) {
            if (this.mViewHolder.checkboxParticipate.isChecked())
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
            else
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
                this.mViewHolder.checkboxParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkboxParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkboxParticipate;
    }
}


