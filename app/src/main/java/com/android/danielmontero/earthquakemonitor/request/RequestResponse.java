package com.android.danielmontero.earthquakemonitor.request;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public class RequestResponse {
    public Exception exception;
    public StringBuilder stringResponse;
    public boolean isSucessfull()
    {
        return exception==null;
    }

}
