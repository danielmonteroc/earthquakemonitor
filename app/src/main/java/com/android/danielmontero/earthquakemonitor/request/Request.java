package com.android.danielmontero.earthquakemonitor.request;

import android.os.AsyncTask;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public abstract class Request {
    protected Request(){}

    public abstract void makeRequest(final RequestResponse requestResponse);

    public final void onBackground(RequestCallback callback) {
        new AsyncRequest(callback).execute();
    }

    private class AsyncRequest extends AsyncTask<Void, Void, Void> {
        RequestCallback mCallback;
        RequestResponse mRequestResponse;


        public AsyncRequest(RequestCallback callback) {

            mCallback = callback;
            mRequestResponse = new RequestResponse();
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
