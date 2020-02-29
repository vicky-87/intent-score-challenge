package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Result = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("result");

        if(bundle != null) {
            Result.setText(value);
        }
    }
}
