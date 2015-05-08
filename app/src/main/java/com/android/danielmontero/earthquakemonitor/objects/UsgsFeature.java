package com.android.danielmontero.earthquakemonitor.objects;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public class UsgsFeature {




    private double mag;
    private String place;
    private long time;

    private String magType;
    private LatLng coordinates;

    private double depth;

    public UsgsFeature(JSONObject object) throws JSONException
    {
        JSONObject properties = object.getJSONObject("properties");
        mag = properties.getDouble("mag");
        place = properties.getString("place");
        time = properties.getLong("time");
        magType= properties.getString("magType");

        JSONArray geometry = object.getJSONObject("geometry").getJSONArray("coordinates");
        coordinates = new LatLng(geometry.getDouble(0),geometry.getDouble(1));
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
        return coordinates;
    }

    public double getDepth() {
        return depth;
    }

}
