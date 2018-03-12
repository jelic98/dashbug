package org.ecloga.dashbug;

import android.util.Log;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

public class Dashbug {

    private static final String TAG = "Dashbug";

    private Class config;

    // todo add constructor that accepts object
    // todo enable/disable Dasbug based on BuildConfig debug flag

    /**
        @param config configuration class
        @throws  NullPointerException if configuration class is null
     */
    public Dashbug(Class config) {
        if(config == null) {
            throw new NullPointerException();
        }

        this.config = config;
    }

    /**
     @return map of field name and field value pairs in configuration class
     */
    public HashMap<String, String> getFields() {
        HashMap<String, String> map = new HashMap<>();

        Field[] fields = config.getDeclaredFields();

        for(Field f : fields) {
            f.setAccessible(true);

            try {
                map.put(f.getName(), f.get(config).toString());
            }catch(Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return map;
    }
}