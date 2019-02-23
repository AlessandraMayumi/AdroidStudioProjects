package com.example.octolauncher;

import java.util.List;

public class Profile {
    private int id;
    private String name;
    private List<String> apps;

    private int MediaVolume;
    private int AlarmVolume;
    private int RingVolume;
    private boolean vibrate;

    private String walpaper;
    private String ssid;

    ///////////////////////////////////////
    Profile(){

    }

    Profile(int _id, String _name, List<String> _apps, int _MediaVolume, int _AlarmVolume, int _RingVolume, boolean _vibrate, String _wallpaper, String _ssid){
        this.setId(_id);
        this.setName(_name);
        this.setApps(_apps);
        this.setMediaVolume(_MediaVolume);
        this.setAlarmVolume(_AlarmVolume);
        this.setRingVolume(_RingVolume);
        this.setVibrate(_vibrate);
        this.setWalpaper(_wallpaper);
        this.setSsid(_ssid);
    }
    //SET

    void setProfile(int _id, String _name, List<String> _apps, int _MediaVolume, int _AlarmVolume, int _RingVolume, boolean _vibrate, String _wallpaper, String _ssid){
        this.setId(_id);
        this.setName(_name);
        this.setApps(_apps);
        this.setMediaVolume(_MediaVolume);
        this.setAlarmVolume(_AlarmVolume);
        this.setRingVolume(_RingVolume);
        this.setVibrate(_vibrate);
        this.setWalpaper(_wallpaper);
        this.setSsid(_ssid);
    }

        void setId(int _id){
        this.id = _id;
    }

    void setName(String _name){
        this.name = _name;
    }

    void setApps(List<String> _apps){
        this.apps = _apps;
    }
    void addApp(String _app){
        this.apps.add(_app);
    }

    void setMediaVolume(int _MediaVolume){
        this.MediaVolume = _MediaVolume;
    }

    void setAlarmVolume(int _AlarmVolume){
        this.AlarmVolume = _AlarmVolume;
    }

    void setRingVolume(int _RingVolume){
        this.RingVolume = _RingVolume;
    }

    void setVibrate(boolean _vibrate){
        this.vibrate = _vibrate;
    }

    void setWalpaper(String _walpaper) {
        this.walpaper = _walpaper;
    }

    void setSsid(String _ssid){
        this.ssid = _ssid;
    }

    //GET
    int getId(){
        return this.id;
    }

    String getName(){
        return this.name;
    }

    List<String> getApps(){
        return this.apps;
    }
    String getAppByPosition(int p){
        return this.apps.get(p);
    }

    int getMediaVolume(){
        return this.MediaVolume;
    }

    int getAlarmVolume(){
        return this.AlarmVolume;
    }

    int getRingVolume(){
        return this.RingVolume;
    }

    String getSsid(){
        return this.ssid;
    }

    boolean getVibrate(){
        return this.vibrate;
    }

    String getWalpaper(){
        return this.walpaper;
    }

}
