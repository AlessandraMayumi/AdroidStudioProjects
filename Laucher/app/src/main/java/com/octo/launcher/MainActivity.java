package com.octo.launcher;

import android.content.ComponentName;
<<<<<<< HEAD
=======
import android.content.Context;
>>>>>>> 10a76a26c17ea2b9d2244d972ba250fbbe3033ce
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

<<<<<<< HEAD
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
=======
public class MainActivity extends AppCompatActivity {

>>>>>>> 10a76a26c17ea2b9d2244d972ba250fbbe3033ce

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView chromeIcon = (ImageView) findViewById(R.id.chromeButton);
        chromeIcon.setImageDrawable(getActivityIcon("com.android.chrome", "com.google.android.apps.chrome.Main"));
    }

    public void onChromeButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        startActivity(launchIntent);
    }

<<<<<<< HEAD
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_apps) {
            Intent intent = new Intent(this, MyActivity.class);
            startActivity(intent);
        }
    }
=======
>>>>>>> 10a76a26c17ea2b9d2244d972ba250fbbe3033ce
    public Drawable getActivityIcon(String packageName, String activityName) {
        PackageManager pm = getPackageManager();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);

        return resolveInfo.loadIcon(pm);
    }
}
