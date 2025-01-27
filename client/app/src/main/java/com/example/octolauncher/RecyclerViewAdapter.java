package com.example.octolauncher;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.octolauncher.ApkInfo;
import com.example.octolauncher.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<ApkInfo> appsList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ImageView img;


        //This is the subclass ViewHolder which simply
        //'holds the views' for us to show on each row
        public ViewHolder(View itemView) {
            super(itemView);

            //Finds the views from our row.xml
            textView = (TextView) itemView.findViewById(R.id.tv_label);
            img = (ImageView) itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick (View v) {
            int pos = getAdapterPosition();
            Context context = v.getContext();

            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appsList.get(pos).getApkPackage().toString());
            context.startActivity(launchIntent);
            Toast.makeText(v.getContext(), appsList.get(pos).getApkLabel().toString(), Toast.LENGTH_LONG).show();

        }
    }



    public RecyclerViewAdapter(Context c) {

        //This is where we build our list of app details, using the app
        //object we created to store the label, package name and icon
        appsList = MainActivity.getAppsList();
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int i) {

        //Here we use the information in the list we created to define the views

        String appLabel = appsList.get(i).getApkLabel().toString();
        String appPackage = appsList.get(i).getApkPackage().toString();
        Drawable appIcon = appsList.get(i).getApkIcon();

        TextView textView = viewHolder.textView;
        Log.i("Debug", "appLabel: " + appLabel);
        textView.setText(appLabel);
        textView.setTextColor(Color.WHITE);
        ImageView imageView = viewHolder.img;
        imageView.setImageDrawable(appIcon);
    }


    @Override
    public int getItemCount() {

        //This method needs to be overridden so that Androids knows how many items
        //will be making it into the list

        return appsList.size();
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
}