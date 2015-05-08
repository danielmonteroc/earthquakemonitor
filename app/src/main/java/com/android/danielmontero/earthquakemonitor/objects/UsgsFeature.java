package com.android.danielmontero.earthquakemonitor.objects;


import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public class UsgsFeature implements Serializable{



    private static final long serialVersionUID = 1L;

    private double mag;
    private String place;
    private long time;

    private String magType;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private double latitude;
    private double longitude;


    private double depth;

    public UsgsFeature(JSONObject object) throws JSONException
    {
        JSONObject properties = object.getJSONObject("properties");
        mag = properties.getDouble("mag");
        place = properties.getString("place");
        time = properties.getLong("time");
        magType= properties.getString("magType");

        JSONArray geometry = object.getJSONObject("geometry").getJSONArray("coordinates");
        latitude=geometry.getDouble(1);
        longitude=geometry.getDouble(0);
        depth = geometry.getDouble(2);

    }



    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public String getMagType() {
        return magType;
    }

    public LatLng getCoordinates() {
        return  new LatLng(latitude,longitude);
    }

    public double getDepth() {
        return depth;
    }

}
