package com.example.octolauncher;

import java.util.ArrayList;

public class Apps {
    private String mName;
    private String mIcon;

    public Apps(String name, String icon) {
        mName = name;
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public String getIcon() {
        return mIcon;
    }

    public static ArrayList<Apps> createAppsList(int numApps) {
        ArrayList<Apps> apps = new ArrayList<>();
        int lastContactId = 0;
        for (int i = 1; i <= numApps; i++) {
            apps.add(new Apps("Person " + ++lastContactId, "placeholder"));
        }
        return apps;
    }
}