package com.android.danielmontero.earthquakemonitor.request;

import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public class GetSummary extends Request <UsgsFeature>{
    @Override
    String getRequestURL() {
        return "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
    }

    @Override
    void processResponse(RequestResponse<UsgsFeature> requestResponse) throws Exception{

        JSONObject jsonObject = new JSONObject(requestResponse.stringResponse.toString());

        JSONArray jsonArray =jsonObject.getJSONArray("features");
        requestResponse.arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            requestResponse.arrayList.add(new UsgsFeature(jsonArray.getJSONObject(i)));
        }
    }
}
