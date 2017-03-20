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
    private ImageButton m_imgBtn, c_imgBtn, e_imgBtn;
    private TextView title_view;
    private char player1, player2;
    private Button menu, bgm;
    int count = 0;
    MediaPlayer mp_bgm, mp_btn1, mp_btn2;
    int countOne, countTwo;

    MultiActivity.MyOnClickListener myOnClickListener1 = new MultiActivity.MyOnClickListener();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        m_imgBtn = (ImageButton) findViewById(R.id.buttonMouse);
        c_imgBtn = (ImageButton) findViewById(R.id.buttonCat);
        e_imgBtn = (ImageButton) findViewById(R.id.buttonEle);
        title_view = (TextView) findViewById(R.id.titleView);
        menu = (Button) findViewById(R.id.btnMenu);
        bgm = (Button) findViewById(R.id.btnBgm);
        player1 = '0';
        player2 = '0';
        m_imgBtn.setOnClickListener(myOnClickListener1);
        c_imgBtn.setOnClickListener(myOnClickListener1);
        e_imgBtn.setOnClickListener(myOnClickListener1);
        menu.setOnClickListener(myOnClickListener1);
        bgm.setOnClickListener(myOnClickListener1);
        Intent myIntent2 = getIntent(); ////receive both players'points from last result
        countOne = myIntent2.getIntExtra("countOne", 0);
        countTwo = myIntent2.getIntExtra("countTwo", 0);
        mp_bgm = MediaPlayer.create(this, R.raw.multi);
        mp_btn1 = MediaPlayer.create(this, R.raw.blaster);
        mp_btn2 = MediaPlayer.create(this, R.raw.blaster);

        mp_bgm.start();
        mp_bgm.setLooping(true);

    }

    private class MyOnClickListener implements View.OnClickListener {
        public void onClick(View v1) {
            if (v1.getId() == R.id.btnMenu) {
                Intent myIntent0 = new Intent(MultiActivity.this, MainActivity.class);
                startActivity(myIntent0);
                mp_bgm.stop();
                finish();
            } else {
                if (v1.getId() == R.id.btnBgm) {
                    if (mp_bgm.isPlaying()) {
                        mp_bgm.pause();
                    } else {
                        mp_bgm.start();
                    }
                } else {
                    count++;
                    if (count % 2 == 1) {
                        mp_btn1.start();
                        title_view.setText("Player2's turn");
                        if (v1.getId() == R.id.buttonMouse) {
                            player1 = 'm';
                        } else if (v1.getId() == R.id.buttonCat) {
                            player1 = 'c';
                        } else if (v1.getId() == R.id.buttonEle) {
                            player1 = 'e';
                        }
                    }
                    //count % 2 == 0 means both players have made their choice
                    if (count % 2 == 0) {
                        mp_btn1.stop();
                        mp_btn1.release();
                        mp_btn2.start();
                        if (v1.getId() == R.id.buttonMouse) {
                            player2 = 'm';
                        } else if (v1.getId() == R.id.buttonCat) {
                            player2 = 'c';
                        } else if (v1.getId() == R.id.buttonEle) {
                            player2 = 'e';
                        }
                        Intent myIntent = new Intent(MultiActivity.this, ResultActivity.class); //send both players' choice and sum points to result activity
                        myIntent.putExtra("choiceOne", player1);
                        myIntent.putExtra("choiceTwo", player2);
                        myIntent.putExtra("countOne", countOne);
                        myIntent.putExtra("countTwo", countTwo);
                        startActivity(myIntent);
                        mp_bgm.stop();
                        mp_bgm.release();
                        mp_btn2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp_btn2) {
                                mp_btn2.release();
                            }
                        });
                                finish();
                        }
                    }
                }
            }


    }
}
