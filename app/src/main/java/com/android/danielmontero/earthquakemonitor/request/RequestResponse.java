package com.android.danielmontero.earthquakemonitor.request;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public class RequestResponse <PObject extends Object>{
    public Exception exception;
    public StringBuilder stringResponse;
    public ArrayList<PObject> arrayList;
    public boolean isSucessfull()
    {
        return exception==null;
    }

}
