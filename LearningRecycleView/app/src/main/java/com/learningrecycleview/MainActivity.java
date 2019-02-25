package com.learningrecycleview;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView chromeIcon = (ImageView) findViewById(R.id.chromeButton);
        chromeIcon.setImageDrawable(getActivityIcon("com.android.chrome", "com.google.android.apps.chrome.Main"));

    }

    public void onChromeButtonClick(View v) {
//        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
//        startActivity(launchIntent);
        Intent intent = new Intent(this, AppDrawer.class);
        startActivity(intent);
    }

    public Drawable getActivityIcon(String packageName, String activityName) {
        PackageManager pm = getPackageManager();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);

        return resolveInfo.loadIcon(pm);
    }
}
