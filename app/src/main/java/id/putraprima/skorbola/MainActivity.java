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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    public static final String HOMETEAM_KEY = "home";
    public static final String AWAYTEAM_KEY = "away";
    private static final String HOMEIMAGE_KEY = "imageHome";
    private static final String AWAYIMAGE_KEY = "imageAway";

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE_HOME = 1;
    private static final int GALLERY_REQUEST_CODE_AWAY = 2;

    private EditText homeTeam;
    private EditText awayTeam;
    private ImageView homeImage;
    private ImageView awayImage;
    private Bitmap bitmap1;
    private Bitmap bitmap2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeTeam = findViewById(R.id.home_team);
        awayTeam = findViewById(R.id.away_team);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);


        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    public void nextHandler(View view) {
        String home = homeTeam.getText().toString();
        String away = awayTeam.getText().toString();


        if ((home).equals("")) {
            Toast.makeText(this, "Data harus diisi", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MatchActivity.class);
            homeImage.buildDrawingCache();
            awayImage.buildDrawingCache();
            Bitmap imageHome = homeImage.getDrawingCache();
            Bitmap imageAway = awayImage.getDrawingCache();
            Bundle extra = new Bundle();
            extra.putParcelable(HOMEIMAGE_KEY,imageHome);
            extra.putParcelable(AWAYIMAGE_KEY,imageAway);
            intent.putExtras(extra);
            intent.putExtra(HOMETEAM_KEY, home);
            intent.putExtra(AWAYTEAM_KEY, away);
            startActivity(intent);
        }

    }

    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleChangeAvatar2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                   bitmap1= MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homeImage.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if (requestCode == 2) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    awayImage.setImageBitmap(bitmap2);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}