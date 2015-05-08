package com.android.danielmontero.earthquakemonitor.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.danielmontero.earthquakemonitor.R;
import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;
import com.android.danielmontero.earthquakemonitor.request.RequestCallback;
import com.android.danielmontero.earthquakemonitor.request.RequestManager;
import com.android.danielmontero.earthquakemonitor.request.RequestResponse;



public class SummaryActivity extends ActionBarActivity implements RequestCallback<UsgsFeature>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        setupToolbar();
        RequestManager.GET_SUMMARY.onBackground(this);


    }

    private void setupToolbar()
    {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_activity);
        toolbar.setTitle("Earthquake Monitor");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onRequestBegin() {

    }

    @Override
    public void onRequestFinished(RequestResponse<UsgsFeature> requestResponse) {
        TextView textView = (TextView)findViewById(R.id.textView);
        StringBuilder stringBuilder = new StringBuilder();
        if(requestResponse.isSucessfull()) {

            for(UsgsFeature usgsFeature:requestResponse.arrayList)
            {
                stringBuilder.append(usgsFeature.getPlace());
                stringBuilder.append("\n");

            }
            textView.setText(stringBuilder);

        }
        else
        {
            textView.setText(requestResponse.exception.getLocalizedMessage());

        }
    }
}
