package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MatchActivity extends AppCompatActivity {
    private static final String HASIL_KEY = "hasil";
    private TextView homeTeam;
    private TextView awayTeam;
    private ImageView homeImage;
    private ImageView awayImage;
    int skorHome=0;
    int skorAway=0;

    //1.Menampilkan detail match sesuai data dari main activity
    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        homeTeam = findViewById(R.id.txt_home);
        awayTeam = findViewById(R.id.txt_away);
        homeImage=findViewById(R.id.home_logo);
        awayImage=findViewById(R.id.away_logo);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            // TODO: display value here
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("imageHome");
            Bitmap bmp2 = extra.getParcelable("imageAway");

            homeImage.setImageBitmap(bmp);
            awayImage.setImageBitmap(bmp2);

            homeTeam.setText(extras.getString(MainActivity.HOMETEAM_KEY));
            awayTeam.setText(extras.getString(MainActivity.AWAYTEAM_KEY));

        }

    }
    public void addHome(View view) {
        skorHome++;
        addskorHome(skorHome);
    }

    public void addskorHome(int scoreHome) {
        TextView scoreView=findViewById(R.id.score_home);
        scoreView.setText(String.valueOf(scoreHome));
    }
    public void addAway(View view){
        skorAway++;
        addskorAway(skorAway);
    }
    public void addskorAway(int scoreAway){
        TextView scoreView=findViewById(R.id.score_away);
        scoreView.setText(String.valueOf(scoreAway));
    }
    public void cekSkor(View view) {
        String hasil=null;
        if(skorHome==skorAway){
            hasil = "DRAW";
        }else if(skorHome > skorAway){
            hasil=homeTeam.getText().toString();
        }else if(skorAway > skorHome){
            hasil=homeTeam.getText().toString();
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);
    }
}
