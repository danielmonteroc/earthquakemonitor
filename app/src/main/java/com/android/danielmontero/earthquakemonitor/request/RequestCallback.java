package com.android.danielmontero.earthquakemonitor.request;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public interface RequestCallback {
    void onRequestBegin();
    void onRequestFinished(RequestResponse requestResponse);
}
