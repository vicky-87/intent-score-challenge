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
    private EditText hometeamInput;
    private EditText awayteamInput;
    private ImageView avatarImage;
    private ImageView avatarImage2;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hometeamInput = findViewById(R.id.home_team);
        awayteamInput = findViewById(R.id.away_team);
        avatarImage = findViewById(R.id.home_logo);
        avatarImage2 = findViewById(R.id.away_logo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void logoHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    public void logoAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    public void handleNext(View view) {
        String homeText = hometeamInput.getText().toString();
        String awayText = awayteamInput.getText().toString();
        Intent intent =  new Intent(this, MatchActivity.class);

        if(!(homeText).equals("") && !(awayText).equals("")){
            intent.putExtra(HOMETEAM_KEY, homeText);
            intent.putExtra(AWAYTEAM_KEY, awayText);
            startActivity(intent);
        }else {
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

