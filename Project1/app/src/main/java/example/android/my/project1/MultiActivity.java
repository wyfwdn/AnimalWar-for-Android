package example.android.my.project1;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.media.MediaPlayer;
/**
 * Created by Administrator on 2017/3/17.
 */

public class MultiActivity extends Activity {

    private ImageButton r_imgBtn, p_imgBtn, s_imgBtn;
    private TextView title_view;
    private char player1, player2;
    private Button menu, bgm;
    int count = 0;
    MediaPlayer mp_bgm, mp_btn;
    int countOne, countTwo;


    MultiActivity.MyOnClickListener myOnClickListener1 = new MultiActivity.MyOnClickListener();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        r_imgBtn = (ImageButton) findViewById(R.id.buttonRock);
        p_imgBtn = (ImageButton) findViewById(R.id.buttonPaper);
        s_imgBtn = (ImageButton) findViewById(R.id.buttonScissors);
        title_view = (TextView) findViewById(R.id.titleView);
        menu = (Button) findViewById(R.id.btnMenu);
        bgm = (Button) findViewById(R.id.btnBgm);

        player1 = '0';
        player2 = '0';
        r_imgBtn.setOnClickListener(myOnClickListener1);
        p_imgBtn.setOnClickListener(myOnClickListener1);
        s_imgBtn.setOnClickListener(myOnClickListener1);
        menu.setOnClickListener(myOnClickListener1);
        bgm.setOnClickListener(myOnClickListener1);
        Intent myIntent2 = getIntent();
        countOne = myIntent2.getIntExtra("countOne", 0);
        countTwo = myIntent2.getIntExtra("countTwo", 0);
        mp_bgm = MediaPlayer.create(this, R.raw.multi);
        mp_btn = MediaPlayer.create(this, R.raw.blaster);
        mp_bgm.start();
        mp_bgm.setLooping(true);

    }

    private class MyOnClickListener implements View.OnClickListener {
        public void onClick(View v1) {
            mp_btn.start();
            if (v1.getId() == R.id.btnMenu) {
                Intent myIntent0 = new Intent(MultiActivity.this, MainActivity.class);
                startActivity(myIntent0);
                mp_bgm.stop();
                finish();
            } else if (v1.getId() == R.id.btnBgm) {
                if (mp_bgm.isPlaying()) {
                    mp_bgm.pause();
                } else {
                    mp_bgm.start();
                }
            } else {
                count++;
                if (count % 2 == 1) {
                    title_view.setText("Player2's turn");
                    if (v1.getId() == R.id.buttonRock) {
                        player1 = 'r';
                    } else if (v1.getId() == R.id.buttonPaper) {
                        player1 = 'p';
                    } else if (v1.getId() == R.id.buttonScissors) {
                        player1 = 's';
                    }
                }

                if (count % 2 == 0) {

                    if (v1.getId() == R.id.buttonRock) {
                        player2 = 'r';
                    } else if (v1.getId() == R.id.buttonPaper) {
                        player2 = 'p';
                    } else if (v1.getId() == R.id.buttonScissors) {
                        player2 = 's';
                    }
                    Intent myIntent = new Intent(MultiActivity.this, ResultActivity.class);
                    myIntent.putExtra("choiceOne", player1);
                    myIntent.putExtra("choiceTwo", player2);
                    myIntent.putExtra("countOne", countOne);
                    myIntent.putExtra("countTwo", countTwo);
                    startActivity(myIntent);
                    mp_bgm.stop();
                    finish();
                }
            }
        }


    }
}
