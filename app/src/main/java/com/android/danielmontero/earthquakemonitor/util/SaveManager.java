package com.android.danielmontero.earthquakemonitor.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by danielmonterocervantes on 08/05/15.
 */
public class SaveManager {
    public static void saveToFile(Context context, String name, Serializable serializable)
    {

        try {
            FileOutputStream fos = context.openFileOutput(name, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(serializable);
            os.close();
        }catch (Exception e)
        {

        }
    }
    public static Object loadFromFile(Context context,String name)
    {
        try{
            FileInputStream fis = context.openFileInput(name);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object object =  is.readObject();
            is.close();
            fis.close();
            return object;
        }
        catch(Exception e)
        {
            return null;

        }

    }


}
