package com.android.danielmontero.earthquakemonitor.request;

import android.os.AsyncTask;

import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public abstract class Request <PObject extends Object>{
    protected Request(){}


    abstract String getRequestURL();
    abstract void processResponse(final RequestResponse<PObject> requestResponse) throws Exception;



    public void makeRequest(final RequestResponse<PObject> requestResponse) {
        try{
            URL url = new URL(getRequestURL());
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine())
            {
                stringBuilder.append(scanner.nextLine());
            }
            requestResponse.stringResponse = stringBuilder;


            processResponse(requestResponse);


        }catch (Exception e)
        {
            requestResponse.exception=e;
        }


    }

    public final void onBackground(RequestCallback callback) {
        new AsyncRequest(callback).execute();
    }

    private class AsyncRequest extends AsyncTask<Void, Void, Void> {
        RequestCallback<PObject> mCallback;
        RequestResponse<PObject> mRequestResponse;


        public AsyncRequest(RequestCallback<PObject> callback) {

            mCallback = callback;
            mRequestResponse = new RequestResponse<>();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCallback.onRequestBegin();

        }

        @Override
        protected Void doInBackground(Void... params) {
            makeRequest(mRequestResponse);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mCallback.onRequestFinished(mRequestResponse);
        }
    }
}
