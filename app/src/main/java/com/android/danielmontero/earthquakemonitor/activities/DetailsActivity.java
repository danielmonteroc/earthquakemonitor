package com.android.danielmontero.earthquakemonitor.activities;

import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.danielmontero.earthquakemonitor.R;
import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class DetailsActivity extends ActionBarActivity {

    UsgsFeature mUsgsFeature;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mUsgsFeature = (UsgsFeature)getIntent().getExtras().get("item");
        setupToolbar();
        setupMap();
        setupData();
    }

    private void setupData()
    {
        TextView textViewPlaceName = (TextView)findViewById(R.id.textViewPlaceName);
        TextView textViewMagnitude = (TextView)findViewById(R.id.textViewMagnitude);
        TextView textViewTimeAndDate = (TextView)findViewById(R.id.textViewTimeAndDate);
        TextView textViewLocation = (TextView)findViewById(R.id.textViewLocationDetails);

        textViewPlaceName.setText(mUsgsFeature.getPlace());
        textViewMagnitude.setText("Magnitude: "+mUsgsFeature.getMag());
        textViewTimeAndDate.setText("Time: "+new Date(mUsgsFeature.getTime()).toString());
        textViewLocation.setText("Location: "+mUsgsFeature.getCoordinates().toString() + "\nDepth:" + mUsgsFeature.getDepth());


    }

    private void setupToolbar()
    {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_activity);
        toolbar.setTitle("Earthquake Monitor - Details");
        toolbar.setTitleTextColor(getResources().getColor(R.color.primary_light));
        setSupportActionBar(toolbar);
        findViewById(R.id.imageButtonRefresh).setVisibility(View.INVISIBLE);
    }
    private void setupMap()
    {
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                float color;
                if(mUsgsFeature.getMag()<1)
                {
                    color = BitmapDescriptorFactory.HUE_GREEN;
                }
                else if(mUsgsFeature.getMag()>9)
                {
                    color = BitmapDescriptorFactory.HUE_RED;
                }
                else
                {
                    color= BitmapDescriptorFactory.HUE_YELLOW;
                }

                MarkerOptions marker = new MarkerOptions().position(mUsgsFeature.getCoordinates());
                marker.title(mUsgsFeature.getMag() + "");
                marker.icon(BitmapDescriptorFactory.defaultMarker(color));
                mMap.addMarker(marker);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mUsgsFeature.getCoordinates(), 3f));

            }
        }
    }

}
