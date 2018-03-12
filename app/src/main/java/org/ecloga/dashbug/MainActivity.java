package org.ecloga.dashbug;

import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo change LinearLayout to ListView

        showFields((ViewGroup) findViewById(R.id.mainLayout));
    }

    private void showFields(ViewGroup parent) {
        Dashbug db = new Dashbug(AppConfig.class);

        Map<String, String> fields = db.getFields();

        for(String name : fields.keySet()) {
            View vField = getLayoutInflater().inflate(R.layout.field_view, null);

            TextView tvName = vField.findViewById(R.id.tvName);
            tvName.setText(name);

            TextView tvValue = vField.findViewById(R.id.tvValue);
            tvValue.setText(fields.get(name));

            parent.addView(vField);
        }
    }
}