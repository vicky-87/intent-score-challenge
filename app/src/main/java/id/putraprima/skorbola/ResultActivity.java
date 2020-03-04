package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static id.putraprima.skorbola.MatchActivity.RESULT;
import static id.putraprima.skorbola.MatchActivity.SCORE;

public class ResultActivity extends AppCompatActivity {
    private TextView Result;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Result = findViewById(R.id.tv_winner);
        score = findViewById(R.id.tv_list);
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            //TODO : Display Value
            String text = Result.getText().toString();
            String textScore = score.getText().toString();
            Result.setText(text +" "+extras.getString(RESULT));
            score.setText(extras.getString(textScore+"\n"+SCORE));
        }
    }
}