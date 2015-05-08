package com.android.danielmontero.earthquakemonitor.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.danielmontero.earthquakemonitor.R;
import com.android.danielmontero.earthquakemonitor.adapters.SummaryAdapter;
import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;
import com.android.danielmontero.earthquakemonitor.request.RequestCallback;
import com.android.danielmontero.earthquakemonitor.request.RequestManager;
import com.android.danielmontero.earthquakemonitor.request.RequestResponse;

import java.util.ArrayList;


public class SummaryActivity extends ActionBarActivity implements RequestCallback<UsgsFeature>{

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    SummaryAdapter mAdapter;
    ArrayList<UsgsFeature> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        setupToolbar();
        mList = new ArrayList<>();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_summary);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SummaryAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);


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
       if(requestResponse.isSucessfull()) {

           mList.clear();
           mList.addAll(requestResponse.arrayList);
           mAdapter.notifyDataSetChanged();
       }
        else
        {

        }
    }
}
