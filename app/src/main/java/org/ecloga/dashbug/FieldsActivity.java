package org.ecloga.dashbug;

import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FieldsActivity extends Activity {

    private static final String TAG = "FieldsActivity";

    private Dashbug db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        try {
            db = (Dashbug) getIntent().getExtras().getSerializable("controller");
        }catch(ClassCastException e) {
            Log.e(TAG, e.getMessage());

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

            return;
        }

        showFields((ViewGroup) findViewById(R.id.mainLayout));
    }

    private void showFields(ViewGroup parent) {
        parent.removeAllViews();

        Map<String, String> fields = db.getFields();

        for(String name : fields.keySet()) {
            View vField = getLayoutInflater().inflate(R.layout.field_view, null);

            TextView tvName = vField.findViewById(R.id.tvName);
            tvName.setText(name);

            EditText etValue = vField.findViewById(R.id.etValue);
            etValue.setText(fields.get(name));
            etValue.setHint(name);

            parent.addView(vField);
        }
    }

    public void saveFields(View v) {
        Map<String, String> fields = db.getFields();

        for(String name : fields.keySet()) {
            // todo db.setField(name, etValue.getText().toString());
        }

        finish();
    }
}