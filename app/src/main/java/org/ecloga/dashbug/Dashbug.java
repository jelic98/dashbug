package org.ecloga.dashbug;

import android.util.Log;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;

public class Dashbug {

    private static final String TAG = "Dashbug";

    private Class config;

    // todo add constructor that accepts object
    // todo enable/disable Dasbug based on BuildConfig debug flag

    /**
     * @param config configuration class
     * @throws  NullPointerException if configuration class is null
     */
    public Dashbug(Class config) {
        if(config == null) {
            throw new NullPointerException();
        }

        this.config = config;
    }

    /**
     * @return map of field name and field value pairs in configuration class
     */
    public HashMap<String, String> getFields() {
        HashMap<String, String> map = new HashMap<>();

        Field[] fields = this.config.getDeclaredFields();

        for(Field f : fields) {
            boolean access = f.isAccessible();

            f.setAccessible(true);

            try {
                map.put(f.getName(), f.get(this.config).toString());
            }catch(Exception e) {
                Log.e(TAG, e.getMessage());
            }

            f.setAccessible(access);
        }

        return map;
    }

    /**
     * @param name field name which value will be set
     * @param value new field value
     */
    public void setField(String name, String value) {
        // todo accept Object value

        Field f;

        try {
            f = this.config.getDeclaredField(name);
        }catch(Exception e) {
            Log.e(TAG, e.getMessage());
            return;
        }

        boolean access = f.isAccessible();

        f.setAccessible(true);

        try {
            if(f.getType().equals(Boolean.TYPE)) {
                f.setBoolean(this.config, Boolean.parseBoolean(value));
            }else if(f.getType().equals(Byte.TYPE)) {
                f.setByte(this.config, Byte.parseByte(value));
            }else if(f.getType().equals(Character.TYPE)) {
                f.setChar(this.config, value.charAt(0));
            }else if(f.getType().equals(Short.TYPE)) {
                f.setShort(this.config, Short.parseShort(value));
            }else if(f.getType().equals(Integer.TYPE)) {
                f.setInt(this.config, Integer.parseInt(value));
            }else if(f.getType().equals(Long.TYPE)) {
                f.setLong(this.config, Long.parseLong(value));
            }else if(f.getType().equals(Float.TYPE)) {
                f.setFloat(this.config, Float.parseFloat(value));
            }else if(f.getType().equals(Double.TYPE)) {
                f.setDouble(this.config, Double.parseDouble(value));
            }else if(f.getType().equals(String.class)) {
                f.set(this.config, value);
            }
        }catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }finally {
            f.setAccessible(access);
        }
    }
}