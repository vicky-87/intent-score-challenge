package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static id.putraprima.skorbola.MainActivity.AWAYTEAM_KEY;
import static id.putraprima.skorbola.MainActivity.HOMETEAM_KEY;

public class MatchActivity extends AppCompatActivity {
    private TextView hometeamText;
    private TextView awayteamText;
    private TextView homeCount;
    private TextView awayCount;
    private static final String RESULT_KEY = "result";



    int counterHome = 0;
    int counterAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        hometeamText = findViewById(R.id.txt_home);
        awayteamText = findViewById(R.id.txt_away);
        //COUNT
        homeCount = findViewById(R.id.score_home);
        awayCount = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // TODO: display value here
            hometeamText.setText(extras.getString(HOMETEAM_KEY));
            awayteamText.setText(extras.getString(AWAYTEAM_KEY));
        }
    }

    public void handleAddHome(View view) {
        counterHome++;
        homeCount.setText(Integer.toString(counterHome));
    }

    public void handleAddAway(View view) {
        counterAway++;
        awayCount.setText(Integer.toString(counterAway));
    }

    public void handleCek(View view) {
        String result;
        Intent intent = new Intent(this, ResultActivity.class);

        if (counterHome > counterAway) {
            result = hometeamText.getText().toString() + " Is the Winner";
        } else if (counterHome < counterAway) {
            result = awayteamText.getText().toString() + " Is the Winner";
        } else {
            result = " Is Draw";
        }
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}

    //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
