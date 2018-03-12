package org.ecloga.dashbug;

import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo change LinearLayout to ListView

        showFields((ViewGroup) findViewById(R.id.mainLayout));
    }

    private void showFields(final ViewGroup parent) {
        parent.removeAllViews();

        final Dashbug db = new Dashbug(AppConfig.class);

        Map<String, String> fields = db.getFields();

        for(final String name : fields.keySet()) {
            View vField = getLayoutInflater().inflate(R.layout.field_view, null);

            TextView tvName = vField.findViewById(R.id.tvName);
            tvName.setText(name);

            final EditText etValue = vField.findViewById(R.id.etValue);
            etValue.setText(fields.get(name));
            etValue.setHint(name);

            Button btnSet = vField.findViewById(R.id.btnSet);
            btnSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.setField(name, etValue.getText().toString());

                    showFields(parent);
                }
            });

            parent.addView(vField);
        }
    }
}