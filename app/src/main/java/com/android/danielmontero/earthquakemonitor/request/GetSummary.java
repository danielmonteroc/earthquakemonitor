package com.android.danielmontero.earthquakemonitor.request;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */
public class GetSummary extends Request {
    @Override
    public void makeRequest(final RequestResponse requestResponse) {
        try{
            URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine())
            {
                stringBuilder.append(scanner.nextLine());
            }
            requestResponse.stringResponse = stringBuilder;

        }catch (Exception e)
        {
            requestResponse.exception=e;
        }


    }
}
