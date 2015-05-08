package com.android.danielmontero.earthquakemonitor.request;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public interface RequestCallback <PObject extends  Object>{
    void onRequestBegin();
    void onRequestFinished(RequestResponse<PObject> requestResponse);
}
