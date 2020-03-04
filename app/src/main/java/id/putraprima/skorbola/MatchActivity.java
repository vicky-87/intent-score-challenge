package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static id.putraprima.skorbola.MainActivity.AWAYTEAM_KEY;
import static id.putraprima.skorbola.MainActivity.HOMETEAM_KEY;

public class MatchActivity extends AppCompatActivity {
    private TextView hometeamText;
    private TextView awayteamText;
    private TextView scoreHome, scoreAway;
    private TextView homeScoretxt,awayScoretxt;
    private ImageView logoHome;
    private ImageView logoAway;

    private int homeScore = 0;
    private int awayScore = 0;

    public static final String HOME_TEAM = "hometeam";
    public static final String AWAY_TEAM = "awayteam";
    public static final String RESULT = "result";
    public static final String SCORE = "score";
    public static final String IMAGE_KEY_HOME = "imghome";
    public static final String IMAGE_KEY_AWAY = "imgaway";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        hometeamText = findViewById(R.id.txt_home);
        awayteamText = findViewById(R.id.txt_away);
        scoreHome = findViewById(R.id.score_home);
        scoreAway = findViewById(R.id.score_away);
        logoHome = findViewById(R.id.home_logo);
        logoAway = findViewById(R.id.away_logo);
        homeScoretxt = findViewById(R.id.txt_home_last);
        awayScoretxt = findViewById(R.id.txt_away_last);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            //TODO : display value here
            hometeamText.setText(extras.getString(HOME_TEAM));
            awayteamText.setText(extras.getString(AWAY_TEAM));
            Bitmap home = extras.getParcelable(IMAGE_KEY_HOME);
            Bitmap away = extras.getParcelable(IMAGE_KEY_AWAY);
            logoHome.setImageBitmap(home);
            logoAway.setImageBitmap(away);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                homeScore++;
                String a = scoreHome.getText().toString();
                String time = data.getStringExtra("time");
                String score = data.getStringExtra("pencetak");
                scoreHome.setText(a + "\n" + score + "-'"+time);
                homeScoretxt.setText(Integer.toString(homeScore));
            }
        }
        if(requestCode == 2)
        {
            if(resultCode == RESULT_OK)
            {
                awayScore++;
                String a = scoreAway.getText().toString();
                String time = data.getStringExtra("time");
                String score = data.getStringExtra("pencetak");
                scoreAway.setText(a + "\n" + score + "'"+time);
                awayScoretxt.setText(Integer.toString(awayScore));
            }
        }
        if(resultCode == RESULT_CANCELED)
        {
            Toast.makeText(this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void handleAddHome(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 1);
    }

    public void handleAddAway(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 2);
    }


    public void handleCek(View view) {
        String nameHome = hometeamText.getText().toString();
        String nameAway = awayteamText.getText().toString();
        String scoreHome = homeScoretxt.getText().toString();
        String scoreAway = awayScoretxt.getText().toString();

        String Draw = " DRAW ";
        Intent intent = new Intent(this,ResultActivity.class);
        if( awayScore > homeScore )
        {
            intent.putExtra(RESULT,nameAway);
            intent.putExtra(SCORE,scoreAway);
            startActivity(intent);
        }
        else if( awayScore < homeScore )
        {
            intent.putExtra(RESULT,nameHome);
            intent.putExtra(SCORE,scoreHome);
            startActivity(intent);
        }else
        {
            intent.putExtra(RESULT,Draw);
            startActivity(intent);
        }
    }
}

    //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",