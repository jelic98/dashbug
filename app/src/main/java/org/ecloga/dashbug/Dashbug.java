package org.ecloga.dashbug;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.lang.reflect.Field;
import android.support.v4.app.NotificationCompat;
import static android.content.Context.NOTIFICATION_SERVICE;

public class Dashbug implements Serializable {

    private static final String TAG = "Dashbug";
    private static final int NOTIFICATION_ID = 9898;
    private static final int REQUEST_FIELDS = 1;

    private Class config;

    // todo add constructor that accepts configuration object
    // todo enable/disable Dashbug based on BuildConfig debug flag

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

    /**
     * @param activity activity which will be started after saving fields
     * @param context application context for resource access
     */
    public void start(Activity activity, Context context) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("controller", this);

        Intent intent = new Intent(activity, FieldsActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pending = PendingIntent.getActivity(context, REQUEST_FIELDS,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context)
                .setTicker(context.getString(R.string.lib_name))
                .setSmallIcon(R.drawable.ic_notify)
                .setContentTitle(context.getString(R.string.lib_name))
                .setContentText(context.getString(R.string.caption_start))
                .setContentIntent(pending)
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        manager.notify(NOTIFICATION_ID, notification);
    }
}