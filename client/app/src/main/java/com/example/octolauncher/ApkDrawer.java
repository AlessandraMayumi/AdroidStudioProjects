package com.example.octolauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ApkDrawer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_drawer);

        RecyclerView appDrawer = (RecyclerView) findViewById(R.id.card_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        appDrawer.setAdapter(adapter);
//        appDrawer.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        appDrawer.setLayoutManager(layoutManager);

        //appDrawer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
