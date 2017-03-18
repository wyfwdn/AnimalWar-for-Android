package example.android.my.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button btn_single, btn_multi,exit;
    private TextView title, mode;
    private MediaPlayer mp, mp_btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_single = (Button) findViewById(R.id.modeSingle);
        btn_multi = (Button) findViewById(R.id.modeMulti);
        exit = (Button) findViewById(R.id.exit);
        title = (TextView) findViewById(R.id.gameTitle);
        mode = (TextView) findViewById(R.id.gameMode);
        MyOnClickListener listener = new MyOnClickListener();
        mp_btn = MediaPlayer.create(this, R.raw.blaster);
        mp = MediaPlayer.create(this, R.raw.menu);
        mp.start();
        mp.setLooping(true);
        btn_single.setOnClickListener(listener);
        btn_multi.setOnClickListener(listener);
        exit.setOnClickListener(listener);
    }
    private class MyOnClickListener implements View.OnClickListener{

        public void onClick(View v) {
            mp_btn.start();
            switch(v.getId()){
                case R.id.modeSingle: //jump to single players activity
                    mp.stop();
                    Intent myIntent1 = new Intent(MainActivity.this, SingleActivity.class);
                    MainActivity.this.finish();
                    startActivity(myIntent1);
                    break;
                case R.id.modeMulti: // jump to double players activity
                    mp.stop();
                    Intent myIntent2 = new Intent(MainActivity.this, MultiActivity.class);
                    MainActivity.this.finish();
                    startActivity(myIntent2);
                    break;
                case R.id.exit:
                    MainActivity.this.finish();
                    break;
            }
        }
    }
    protected void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }
}
