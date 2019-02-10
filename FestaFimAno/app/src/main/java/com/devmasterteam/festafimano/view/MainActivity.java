package com.devmasterteam.festafimano.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.devmasterteam.festafimano.R;
import com.devmasterteam.festafimano.constants.FimDeAnoConstants;
import com.devmasterteam.festafimano.util.SecutityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecutityPreferences mSecutiryPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.buttonConfirm = (Button) findViewById(R.id.button_confirm);
        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mSecutiryPreferences = new SecutityPreferences(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
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

    private static class ViewHolder {
        Button buttonConfirm;
    }
}
