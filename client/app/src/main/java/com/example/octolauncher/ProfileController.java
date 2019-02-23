package com.example.octolauncher;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ProfileController {
    List<Profile> LoadedProfiles;
    String FileName;
    String FileData;
    Boolean dataLoaded;
    Context fileContext;

    ProfileController(String _fileName, Context _fileContext){
        this.LoadedProfiles = new ArrayList<>();
        this.setFileName(_fileName);
        this.fileContext = _fileContext;
        this.setDataLoaded(false);
        this.setFileData("no data");
    }

    void setFileName(String _fileName){
        this.FileName = _fileName;
    }

    void setFileData(String _fileData){
        this.FileData = _fileData;
    }

    String getFileName(){
        return this.FileName;
    }

    void setDataLoaded(boolean _dataLoaded){
        this.dataLoaded = _dataLoaded;
    }

    boolean getIfDataLoaded(){
        return this.dataLoaded;
    }

    String getFileData(){
        return this.FileData;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

    void parseXml(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(this.getFileData()));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("profile");

            // iterate the profiles
            for (int i = 0; i < nodes.getLength(); i++) {
                Profile _profile = new Profile();

                Element element = (Element) nodes.item(i);
                //Id
                NodeList id = element.getElementsByTagName("id");
                Element line = (Element) id.item(0);
                //Log.d("[Id:]", getCharacterDataFromElement(line));
                String _Id = getCharacterDataFromElement(line);

                //Name
                NodeList name = element.getElementsByTagName("name");
                line = (Element) name.item(0);
                //Log.d("[Name:]", getCharacterDataFromElement(line));
                String _Name = getCharacterDataFromElement(line);

                //getapps
                List<String> _Apps = new ArrayList<>();
                NodeList applist = element.getElementsByTagName("app");
                for (int j = 0; j < applist.getLength(); j++) {
                    line = (Element) applist.item(j);
                    //Log.d("[app:]", getCharacterDataFromElement(line));
                    _Apps.add(getCharacterDataFromElement(line));
                }

                NodeList mediaVolume = element.getElementsByTagName("mediaVolume");
                line = (Element) mediaVolume.item(0);
                //Log.d("[Media Volume:]", getCharacterDataFromElement(line));
                String _MediaVolume = getCharacterDataFromElement(line);

                NodeList alarmVolume = element.getElementsByTagName("alarmVolume");
                line = (Element) alarmVolume.item(0);
                //Log.d("[Alarm Volume:]", getCharacterDataFromElement(line));
                String _AlarmVolume = getCharacterDataFromElement(line);

                NodeList ringVolume = element.getElementsByTagName("ringVolume");
                line = (Element) ringVolume.item(0);
                //Log.d("[Ring Volume:]", getCharacterDataFromElement(line));
                String _RingVolume = getCharacterDataFromElement(line);

                NodeList vibrate = element.getElementsByTagName("vibrate");
                line = (Element) vibrate.item(0);
                //Log.d("[Vibrate:]", getCharacterDataFromElement(line));
                String _Vibrate = getCharacterDataFromElement(line);

                NodeList wallpaper = element.getElementsByTagName("wallpaper");
                line = (Element) wallpaper.item(0);
                //Log.d("[Wallpaper:]", getCharacterDataFromElement(line));
                String _Wallpaper = getCharacterDataFromElement(line);

                NodeList ssid = element.getElementsByTagName("ssid");
                line = (Element) ssid.item(0);
                //Log.d("[ssid:]", getCharacterDataFromElement(line));
                String _SSID = getCharacterDataFromElement(line);

                //(int _id, String _name, List<String> _apps, int _MediaVolume, int _AlarmVolume, int _RingVolume, boolean _vibrate, String _wallpaper, String _ssid){
                _profile.setProfile(Integer.parseInt(_Id),_Name,_Apps,Integer.parseInt(_MediaVolume),Integer.parseInt(_AlarmVolume),Integer.parseInt(_RingVolume),Boolean.parseBoolean(_Vibrate),_Wallpaper,_SSID);
                this.AddProfile(_profile);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void LoadProfiles(){
        FileInputStream fis = null;
        InputStreamReader isr = null;

        try {
            fis = fileContext.openFileInput(this.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        isr = new InputStreamReader(fis);

        char[] inputBuffer = null;
        try {
            inputBuffer = new char[fis.available()];
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            isr.read(inputBuffer);
        } catch (IOException e){
            e.printStackTrace();
        }

        String data = new String(inputBuffer);

        try {
            isr.close();
            fis.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        //load data
        this.setFileData(data);
        this.setDataLoaded(true);

        this.parseXml();
    }

    void SaveProfiles(List<Profile> _profiles){
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();

        String filename = this.getFileName();
        FileOutputStream fos = null;

        try {
            fos = fileContext.openFileOutput(filename, Context.MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            serializer.setOutput(fos, "UTF-8");

            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "profileList");

            serializer.attribute("", "count", String.valueOf(_profiles.size()));

            for (Profile p: _profiles){
                serializer.startTag("", "profile");

                serializer.startTag("", "id");
                serializer.text(Integer.toString(p.getId()));
                serializer.endTag("","id");

                serializer.startTag("", "name");
                serializer.text(p.getName());
                serializer.endTag("","name");

                serializer.startTag("", "mediaVolume");
                serializer.text(Integer.toString(p.getMediaVolume()));
                serializer.endTag("","mediaVolume");

                serializer.startTag("", "alarmVolume");
                serializer.text(Integer.toString(p.getAlarmVolume()));
                serializer.endTag("","alarmVolume");

                serializer.startTag("", "ringVolume");
                serializer.text(Integer.toString(p.getRingVolume()));
                serializer.endTag("","ringVolume");

                serializer.startTag("", "vibrate");
                serializer.text(Boolean.toString(p.getVibrate()));
                serializer.endTag("","vibrate");

                serializer.startTag("", "wallpaper");
                serializer.text(p.getWalpaper());
                serializer.endTag("","wallpaper");

                for (String app_package: p.getApps()){
                    serializer.startTag("", "app");
                    serializer.text(app_package);
                    serializer.endTag("", "app");
                }

                serializer.startTag("", "ssid");
                serializer.text(p.getSsid());
                serializer.endTag("","ssid");

                serializer.endTag("", "profile");
            }

            serializer.endTag("", "profileList");
            serializer.endDocument();
            serializer.flush();

            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void AddProfile(Profile _profile) {
        this.LoadedProfiles.add(_profile);
    }

    Profile getProfileBySsid(String _ssid){
        for(Profile p: this.LoadedProfiles){
            if(p.getSsid().equals(_ssid)) {
                return p;
            }
        }

        return this.LoadedProfiles.get(0);
    }

    Profile getProfileByPosition(int _position){
        return this.LoadedProfiles.get(_position);
    }

    List<Profile> getProfileList(){
        return this.LoadedProfiles;
    }

    void debugProfiles(){
        for(Profile p: this.LoadedProfiles){
            Log.d("[PROFILE READ]", p.getName());
        }
    }

}
