package org.ecloga.demo;

import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.ecloga.dashbug.Dashbug;

public class MainActivity extends Activity {

    private Dashbug db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Dashbug(AppConfig.class);
        db.start(this, getApplicationContext());

        showFields((ViewGroup) findViewById(R.id.mainLayout));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        showFields((ViewGroup) findViewById(R.id.mainLayout));
    }

    private void showFields(ViewGroup parent) {
        parent.removeAllViews();

        Map<String, String> fields = db.getFields();

        for(String name : fields.keySet()) {
            View vField = getLayoutInflater().inflate(R.layout.field, null);

            TextView tvName = vField.findViewById(R.id.tvName);
            tvName.setText(name);

            TextView tvValue = vField.findViewById(R.id.tvValue);
            tvValue.setText(fields.get(name));

            parent.addView(vField);
        }
    }
}