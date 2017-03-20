package example.android.my.project1;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button btn_single, btn_multi,bgm, exit,about;
    private MediaPlayer mp, mp_btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // declare buttons
        btn_single = (Button) findViewById(R.id.modeSingle);
        btn_multi = (Button) findViewById(R.id.modeMulti);
        exit = (Button) findViewById(R.id.exit);
        bgm = (Button) findViewById(R.id.bgm);
        about = (Button) findViewById(R.id.about);
        MyOnClickListener listener = new MyOnClickListener();
        //declare MediaPlayer
        mp_btn = MediaPlayer.create(this, R.raw.blaster);
        mp = MediaPlayer.create(this, R.raw.menu);
        mp.start();
        mp.setLooping(true);
        btn_single.setOnClickListener(listener);
        btn_multi.setOnClickListener(listener);
        exit.setOnClickListener(listener);
        bgm.setOnClickListener(listener);
        about.setOnClickListener(listener);

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
                case R.id.bgm: //Control music on/off
                        if (mp.isPlaying()) {
                            mp.pause();
                        } else {
                            mp.start();
                        }
                    break;
                case R.id.exit:
                    MainActivity.this.finish();
                    break;
                case R.id.about: //Pop out "about" information
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("ABOUT")
                            .setMessage("4901 Project1 Verision 1.0.4\n\nThere are three kinds of animals which are mouse, cat and elephant. The mouse can be eaten by the cat, and the cat can be crumpled by the elephant. However, the mouse can beat the elephant.Player can choose an animal to fight against the animal chosen by the computer or another player.\n\nCopyright @ yw2910 & yz3021 ")
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                            .show();
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
