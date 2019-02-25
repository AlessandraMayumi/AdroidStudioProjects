package com.example.octolauncher;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Connection connection = new Connection();
    private static final int REQUEST_COARSE_LOCATION = 999;
    String network = "TP-Link77";

    static ArrayList<ApkInfo> appsList;
    ArrayList<ApkInfo> homeList;
    List<String> packagesHome;
    List<Profile> profilesAppsList;
    ProfileController profileControler;
    Profile ptest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COARSE_LOCATION);
        }

        //================================= CAIO ===========================================//
        //profile to save

        //Work apps: Maps,Gmail,Drive,Calculator,Calendar
        List<String> workProfileApps = new ArrayList<>();
        workProfileApps.add("com.google.android.apps.maps");
        workProfileApps.add("com.google.android.gm");
        workProfileApps.add("com.google.android.apps.docs");
        workProfileApps.add("com.google.android.calendar");
        workProfileApps.add("com.google.android.calculator");
        Profile workProfile = new Profile(777,"Work", workProfileApps,1,2,3,false,"work.jpg","MM-Guest");

        //Default: Zap,Facebook,Insagram,9gag,Spotfy
        List<String> defaultProfileApps = new ArrayList<>();
        defaultProfileApps.add("com.whatsapp");
        defaultProfileApps.add("com.facebook.katana");
        defaultProfileApps.add("com.instagram.android");
        defaultProfileApps.add("com.ninegag.android.app");
        defaultProfileApps.add("com.spotify.music");
        Profile defaultProfile = new Profile(428,"Default", defaultProfileApps,5,6,7,true,"home.jpg","TP-Link77");

        profilesAppsList = new ArrayList<>();
        profilesAppsList.add(workProfile);
        profilesAppsList.add(defaultProfile);

        //Controller instance
        profileControler = new ProfileController("octoProfiles.xml",this);

        profileControler.SaveProfiles(profilesAppsList); //save to xml
        profileControler.LoadProfiles();

        Log.d("[DATA READ]", profileControler.getFileData());

        ptest = profileControler.getProfileBySsid("TP-Link77");
        if(ptest!=null)
            Log.d("[P]", ptest.getName());
            Log.d("[P]", ptest.getApps().get(0)+","+ptest.getApps().get(1)+","+ptest.getApps().get(2)+","+ptest.getApps().get(3)+","+ptest.getApps().get(4));

        //Profile ptest = profileControler.getProfileBySsid("MM-Guest");

        //Profile ptest = profileControler.getProfileByPosition(0);
        profileControler.debugProfiles();

        //================================= CAIO ===========================================//

        appsList = getAllApps();
       // packagesHome = new ArrayList<String>();
//        packagesHome.add(new String("com.google.android.dialer"));
//        packagesHome.add(new String("com.google.android.apps.messaging"));
//        packagesHome.add(new String("com.android.chrome"));
//        packagesHome.add(new String("com.google.android.apps.photos"));
//        packagesHome.add(new String("com.motorola.camera2"));
//        packagesHome.add(new String("com.google.android.dialer"));
//        packagesHome.add(new String("com.google.android.apps.messaging"));
//        packagesHome.add(new String("com.android.chrome"));
//        packagesHome.add(new String("com.google.android.apps.photos"));
//        packagesHome.add(new String("com.motorola.camera2"));
//        fillHomeList(packagesHome);
        monitorSsdi();

    }

    public void onRestart(View v) {
        onResume();
    }

    // All hotseat views
    @Override
    protected void onResume() {
        super.onResume();
        monitorSsdi();

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

        // Home APPs
        //setIcons();
        ImageView icon1_1 = (ImageView) findViewById(R.id.iv_icon1_1);
        TextView textView1_1 = (TextView) findViewById(R.id.tv_label1_1);
        String appLabel = homeList.get(0).getApkLabel().toString();
        Drawable appIcon = homeList.get(0).getApkIcon();
        textView1_1.setText(appLabel);
        textView1_1.setTextColor(Color.WHITE);
        icon1_1.setImageDrawable(appIcon);
        ImageView icon1_2 = (ImageView) findViewById(R.id.iv_icon1_2);
        TextView textView1_2 = (TextView) findViewById(R.id.tv_label1_2);
        appLabel = homeList.get(1).getApkLabel().toString();
        appIcon = homeList.get(1).getApkIcon();
        textView1_2.setText(appLabel);
        textView1_2.setTextColor(Color.WHITE);
        icon1_2.setImageDrawable(appIcon);
        ImageView icon1_3 = (ImageView) findViewById(R.id.iv_icon1_3);
        TextView textView1_3 = (TextView) findViewById(R.id.tv_label1_3);
        appLabel = homeList.get(2).getApkLabel().toString();
        appIcon = homeList.get(2).getApkIcon();
        textView1_3.setText(appLabel);
        textView1_3.setTextColor(Color.WHITE);
        icon1_3.setImageDrawable(appIcon);
        ImageView icon1_4 = (ImageView) findViewById(R.id.iv_icon1_4);
        TextView textView1_4 = (TextView) findViewById(R.id.tv_label1_4);
        appLabel = homeList.get(3).getApkLabel().toString();
        appIcon = homeList.get(3).getApkIcon();
        textView1_4.setText(appLabel);
        textView1_4.setTextColor(Color.WHITE);
        icon1_4.setImageDrawable(appIcon);
        ImageView icon1_5 = (ImageView) findViewById(R.id.iv_icon1_5);
        TextView textView1_5 = (TextView) findViewById(R.id.tv_label1_5);
        appLabel = homeList.get(4).getApkLabel().toString();
        appIcon = homeList.get(4).getApkIcon();
        textView1_5.setText(appLabel);
        textView1_5.setTextColor(Color.WHITE);
        icon1_5.setImageDrawable(appIcon);

    }

    // open package
    public void onButtonClick1(View v) {

        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
        launchIntent.setPackage(homeList.get(0).getApkPackage().toString());
        startActivity(launchIntent);
    }

    public void onButtonClick2(View v) {

        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
        launchIntent.setPackage(homeList.get(1).getApkPackage().toString());
        startActivity(launchIntent);
    }

    public void onButtonClick3(View v) {

        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
        launchIntent.setPackage(homeList.get(2).getApkPackage().toString());
        startActivity(launchIntent);
    }

    public void onButtonClick4(View v) {

        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
        launchIntent.setPackage(homeList.get(3).getApkPackage().toString());
        startActivity(launchIntent);
    }

    public void onButtonClick5(View v) {

        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
        launchIntent.setPackage(homeList.get(4).getApkPackage().toString());
        startActivity(launchIntent);
    }


    // App drawer activity
    public void onAppsDrawerBtn(View v) {
        Intent intent = new Intent(this, ApkDrawer.class);
        startActivity(intent);
    }

    public void onWallpaperBtn(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.wallpaper");
        startActivity(launchIntent);
    }

    //HotSeat Buttons
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

    public static Drawable getActivityIcon(Context context, String packageName, String activityName) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);
        return resolveInfo.loadIcon(pm);
    }

    public ArrayList<ApkInfo> getAllApps(){
        PackageManager pm = getPackageManager();
        appsList = new ArrayList<ApkInfo>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
        for(ResolveInfo ri:allApps) {
            ApkInfo app = new ApkInfo();
            app.setApkLabel(ri.loadLabel(pm));
            app.setApkPackage(ri.activityInfo.packageName);
            app.setApkIcon(ri.activityInfo.loadIcon(pm));
            appsList.add(app);
        }
        return appsList;
    }

    public static ArrayList<ApkInfo> getAppsList() {
        return appsList;
    }

    public void fillHomeList(List<String> packagesHome){
        homeList = new ArrayList<>();
        for (String packageName: packagesHome) {
            for (ApkInfo app: appsList) {
                if (app.getApkPackage().equals(packageName)){
                    homeList.add(app);
                    Log.i("HOME", app.getApkPackage().toString());
                }
            }
        }
    }

    public void monitorSsdi(){
        //Profile ptest = new Profile();
        if(connection.checkNow(this)&&connection.getCurrentSsid(this)!="<unknown ssid>"){
            Log.d("NETWORK","ssid:IF");
            network = connection.getCurrentSsid(this);
            TextView text_connection = (TextView) findViewById(R.id.text_connection);
            String ssid = connection.getCurrentSsid(this);
            text_connection.setText(ssid);
        }else{
            Log.d("NETWORK","ssid:ELSE");
            network="TP-Link77";
            TextView text_connection = (TextView) findViewById(R.id.text_connection);
            text_connection.setText("Home");
        }
        ptest = profileControler.getProfileBySsid(network);
        Log.d("NETWORK","ssid:"+network);
        Log.d("NETWORK","profile:"+ptest.getName());
        Log.d("NETWORK", ptest.getApps().get(0)+","+ptest.getApps().get(1)+","+ptest.getApps().get(2)+","+ptest.getApps().get(3)+","+ptest.getApps().get(4));

        changeProfile(ptest);
    }

    public void changeProfile(Profile profile){
        packagesHome = profile.getApps();
        fillHomeList(packagesHome);

        InputStream is = getResources().openRawResource(+ R.drawable.home);

        if(profile.getSsid().equals("MM-Guest")){
            is = getResources().openRawResource(+ R.drawable.work);
        }

        Bitmap b = BitmapFactory.decodeStream(is);
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallpaperManager.setBitmap(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
