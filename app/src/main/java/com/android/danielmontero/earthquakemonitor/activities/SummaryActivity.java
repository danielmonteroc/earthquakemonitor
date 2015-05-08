package com.android.danielmontero.earthquakemonitor.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.android.danielmontero.earthquakemonitor.R;
import com.android.danielmontero.earthquakemonitor.adapters.SummaryAdapter;
import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;
import com.android.danielmontero.earthquakemonitor.request.RequestCallback;
import com.android.danielmontero.earthquakemonitor.request.RequestManager;
import com.android.danielmontero.earthquakemonitor.request.RequestResponse;

import java.util.ArrayList;


public class SummaryActivity extends ActionBarActivity implements RequestCallback<UsgsFeature>,SwipeRefreshLayout.OnRefreshListener{

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    SummaryAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
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
        mAdapter = new SummaryAdapter(mList,this);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RequestManager.GET_SUMMARY.onBackground(this);


    }

    private void setupToolbar()
    {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_activity);
        toolbar.setTitle("Earthquake Monitor");
        toolbar.setTitleTextColor(getResources().getColor(R.color.primary_light));
        setSupportActionBar(toolbar);
    }

    @Override
    public void onRequestBegin() {
        mSwipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void onRequestFinished(RequestResponse<UsgsFeature> requestResponse) {
        mSwipeRefreshLayout.setRefreshing(false);
       if(requestResponse.isSucessfull()) {

           mList.clear();
           mList.addAll(requestResponse.arrayList);
           mAdapter.notifyDataSetChanged();
       }
        else
        {

        }
    }

    @Override
    public void onRefresh() {
        RequestManager.GET_SUMMARY.onBackground(this);
    }
}
