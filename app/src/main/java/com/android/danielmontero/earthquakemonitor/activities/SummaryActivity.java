package com.android.danielmontero.earthquakemonitor.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.danielmontero.earthquakemonitor.R;
import com.android.danielmontero.earthquakemonitor.request.RequestCallback;
import com.android.danielmontero.earthquakemonitor.request.RequestManager;
import com.android.danielmontero.earthquakemonitor.request.RequestResponse;


public class SummaryActivity extends ActionBarActivity implements RequestCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        setupToolbar();
        RequestManager.GET_SUMMARY.onBackground(this);


    }

    private void setupToolbar()
    {
        View toolbarView = findViewById(R.id.toolbar_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_activity);
        toolbar.setTitle("Earthquake Monitor");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onRequestBegin() {

    }

    @Override
    public void onRequestFinished(RequestResponse requestResponse) {
        TextView textView = (TextView)findViewById(R.id.textView);
        if(requestResponse.isSucessfull()) {
            textView.setText(requestResponse.stringResponse);

        }
        else
        {
            textView.setText(requestResponse.exception.getLocalizedMessage());

        }
    }
}
