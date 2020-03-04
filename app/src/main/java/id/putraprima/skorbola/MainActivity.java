package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String HOMETEAM_KEY = "hometeam";
    public static final String AWAYTEAM_KEY = "awayteam";
    public static final String IMAGE_KEY_HOME = "imghome";
    public static final String IMAGE_KEY_AWAY = "imgaway";
    private EditText homeTeam;
    private EditText awayTeam;
    private ImageView homeLogo;
    private ImageView awayLogo;
    private Uri imageUri;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALERY_REQUEST_CODE = 1;
    private static final int GALERY_REQUEST_CODEX = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTeam = findViewById(R.id.home_team);
        awayTeam = findViewById(R.id.away_team);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homeLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        else if(requestCode == GALERY_REQUEST_CODEX){
            if (data != null) {
                try {
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    awayLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void logoHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERY_REQUEST_CODE);
    }

    public void logoAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERY_REQUEST_CODEX);
    }

    public void handleNext(View view) {
        String homeText = homeTeam.getText().toString();
        String awayText = awayTeam.getText().toString();

        homeLogo.buildDrawingCache();
        awayLogo.buildDrawingCache();
        Bitmap home = homeLogo.getDrawingCache();
        Bitmap away = awayLogo.getDrawingCache();

        Intent intent =  new Intent(this, MatchActivity.class);
        if(!(homeText).equals("") && !(awayText).equals("")){
            intent.putExtra(HOMETEAM_KEY, homeText);
            intent.putExtra(AWAYTEAM_KEY, awayText);
            intent.putExtra(IMAGE_KEY_HOME,home);
            intent.putExtra(IMAGE_KEY_AWAY,away);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Tolong Isi Nama Tim yang Kosong !", Toast.LENGTH_SHORT).show();
        }
    }
}

    //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity