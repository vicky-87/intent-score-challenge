package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {
    EditText score;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        score = findViewById(R.id.text_score);
        time = findViewById(R.id.text_time);
    }

    public void submitScore(View view) {
        Intent resultIntent = new Intent();
        String timer = time.getText().toString();
        String scorer = score.getText().toString();

        if(score.equals("") && time.equals(""))
        {
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        }else {
            resultIntent.putExtra("time", timer);
            resultIntent.putExtra("pencetak", scorer);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
