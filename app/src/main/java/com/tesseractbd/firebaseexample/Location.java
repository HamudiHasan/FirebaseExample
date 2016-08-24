package com.tesseractbd.firebaseexample;

/**
 * Created by hhson on 8/17/2016.
 */
public class Location {
    long lat;
    long lang;

    public Location() {
    }

    public Location(long lat, long lang) {
        this.lat = lat;
        this.lang = lang;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLang() {
        return lang;
    }

    public void setLang(long lang) {
        this.lang = lang;
    }
}
