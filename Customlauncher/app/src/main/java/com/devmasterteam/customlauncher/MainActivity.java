package com.devmasterteam.customlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView chrome_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chrome_view = (ImageView) findViewById(R.id.chrome_view);
        this.getActivityIcon(chrome_view);

        chrome_view.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.chrome_view){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
            startActivity(launchIntent);
        }
    }
    private void getActivityIcon(ImageView chrome_view){
        try{
            Drawable chromeIcon = getPackageManager().getApplicationIcon("com.android.chrome");
            chrome_view.setImageDrawable(chromeIcon);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
