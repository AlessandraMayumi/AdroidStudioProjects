package com.example.octolauncher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    ArrayList<Apps> apps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Connection connection = new Connection();
        if(connection.checkNow(this)){
            TextView text_connection = (TextView) findViewById(R.id.text_connection);
            text_connection.setText("peixe");
        }else{
            TextView text_connection = (TextView) findViewById(R.id.text_connection);
            text_connection.setText("bola");
        }
    }

    // All hotseat views
    @Override
    protected void onResume() {
        super.onResume();
        Connection connection = new Connection();
        if(connection.checkNow(this)){
            TextView text_connection = (TextView) findViewById(R.id.text_connection);
            text_connection.setText("gato");
        }else{
            TextView text_connection = (TextView) findViewById(R.id.text_connection);
            text_connection.setText("fim");
        }

        // Dialer
        ImageView hotseat1Icon = (ImageView) findViewById(R.id.hotseat1);
        hotseat1Icon.setImageDrawable(getActivityIcon(this, "com.google.android.dialer", "com.google.android.dialer.extensions.GoogleDialtactsActivity"));

        // Messages
        ImageView hotseat2Icon = (ImageView) findViewById(R.id.hotseat2);
        hotseat2Icon.setImageDrawable(getActivityIcon(this, "com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.ConversationListActivity"));

        // Chrome
        ImageView hotseat3Icon = (ImageView) findViewById(R.id.hotseat3);
        hotseat3Icon.setImageDrawable(getActivityIcon(this, "com.android.chrome", "com.google.android.apps.chrome.Main"));

        // Photos
        ImageView hotseat4Icon = (ImageView) findViewById(R.id.hotseat4);
        hotseat4Icon.setImageDrawable(getActivityIcon(this, "com.google.android.apps.photos", "com.google.android.apps.photos.home.HomeActivity"));

        // Chrome
        ImageView hotseat5Icon = (ImageView) findViewById(R.id.hotseat5);
        hotseat5Icon.setImageDrawable(getActivityIcon(this, "com.motorola.camera2", "com.motorola.camera.Camera"));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);

    }
        public void onClickhotseat1 (View view){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.dialer");
            startActivity(launchIntent);
        }
        public void onClickhotseat2 (View view){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.messaging");
            startActivity(launchIntent);
        }
        public void onClickhotseat3 (View view){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
            startActivity(launchIntent);
        }
        public void onClickhotseat4 (View view){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.photos");
            startActivity(launchIntent);
        }
        public void onClickhotseat5 (View view){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.motorola.camera2");
            startActivity(launchIntent);
        }


        // App drawer activity
        public void onAppsDrawerBtn (View v){
            Intent intent = new Intent(this, ApkDrawer.class);
            startActivity(intent);
        }

        public void onWallpaperBtn (View v){
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.wallpaper");
            startActivity(launchIntent);
        }


        public static Drawable getActivityIcon (Context context, String packageName, String
        activityName){
            PackageManager pm = context.getPackageManager();
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, activityName));
            ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);
            return resolveInfo.loadIcon(pm);
        }
    }
