package org.ecloga.dashbug;

import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
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
    private Map<String, String> fields;

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

        fields = db.getFields();

        for(final String name : fields.keySet()) {
            View vField = getLayoutInflater().inflate(R.layout.field_view, null);

            TextView tvName = vField.findViewById(R.id.tvName);
            tvName.setText(name);

            EditText etValue = vField.findViewById(R.id.etValue);
            etValue.setText(fields.get(name));
            etValue.setHint(getString(R.string.caption_value) + " " + name);
            etValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    fields.put(name, s.toString());
                }
            });

            parent.addView(vField);
        }
    }

    public void saveFields(View v) {
        db.setFields(fields);

        finish();
    }
}