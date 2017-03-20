package example.android.my.project1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.ContentValues;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/16.
 */

public class SingleActivity extends Activity {

    private ImageView imgView;  //the first ImageView in the view
    private ImageButton m_imgBtn, c_imgBtn, e_imgBtn;  // Mouse, Cat, Elephant
    private Button back_Btn, pause_Btn, record_Btn;
    private EditText et; //where players input their names to record
    private TextView result_tv, count_tv;  //the textView of result and count
    int count = 0; // initialize the count
    int wins = 0, loses = 0;    // initialize the count of win and lose

    //intialize a listener to monitoring the three buttons
    SingleActivity.MyOnClickListener myOnClickListener = new SingleActivity.MyOnClickListener();

    //add two MediaPlayer objects
    private MediaPlayer mp_background;
    private MediaPlayer mp_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        //Declare buttons
        et = (EditText) findViewById(R.id.editText);
        m_imgBtn = (ImageButton) findViewById(R.id.btnMouse);
        c_imgBtn = (ImageButton) findViewById(R.id.btnCat);
        e_imgBtn = (ImageButton) findViewById(R.id.btnEle);
        pause_Btn = (Button) findViewById(R.id.btnPause);
        back_Btn = (Button) findViewById(R.id.btnBack);
        record_Btn = (Button) findViewById(R.id.btnRecord);
        //Declare imgView
        imgView = (ImageView) findViewById(R.id.viewCmp);

        //Declare result and count TextView
        result_tv = (TextView) findViewById(R.id.textResult);
        count_tv = (TextView) findViewById(R.id.textCount);

        record_Btn.setOnClickListener(myOnClickListener);
        m_imgBtn.setOnClickListener(myOnClickListener);
        c_imgBtn.setOnClickListener(myOnClickListener);
        e_imgBtn.setOnClickListener(myOnClickListener);
        pause_Btn.setOnClickListener(myOnClickListener);
        back_Btn.setOnClickListener(myOnClickListener);
        //declare the audio resource to these two MediaPlayer objects
        mp_background = MediaPlayer.create(this, R.raw.single);
        mp_button = MediaPlayer.create(this, R.raw.blaster);


        //play background music here with looping setting
        mp_background.start();
        mp_background.setLooping(true);


    }

    private class MyOnClickListener implements View.OnClickListener {

        public void onClick(View v) {
            // get a random number form 1 to 3
            int rand = (int) (Math.random() * 3 + 1);
            // control bgm on/off
            if (v.getId() == R.id.btnPause) {
                if (mp_background.isPlaying()) {
                    mp_background.pause();
                } else {
                    mp_background.start();
                }
            }
            // back to main menu
            else if (v.getId() == R.id.btnBack){
                mp_background.stop();
                Intent myIntent3 = new Intent(SingleActivity.this, MainActivity.class);
                SingleActivity.this.finish();
                startActivity(myIntent3);
            }
            // jump to game record history page
            else if (v.getId() == R.id.btnRecord){
                String name = et.getText().toString();
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
                String date = sDateFormat.format(new java.util.Date());
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("wins", "win:\t\t"+wins);
                values.put("loses", "lose:\t"+loses);
                values.put("time", date);
                HelperActivity helpter = new HelperActivity(SingleActivity.this);
                helpter.insert(values);
                Intent intent = new Intent(SingleActivity.this,
                        RecordActivity.class);
                startActivity(intent);
            }
            // play game
            else {
                count++;//
                switch (rand) {
                    /**
                     * rand = 1 means computer is mouse,
                     * 2 represents cat,
                     * 3 represents elephant
                     */
                    case 1:
                        imgView.setImageResource(R.drawable.mouse);  //computer choose mouse
                        mp_button.start();
                        switch (v.getId()) {
                            case R.id.btnMouse:   //player choose mouse
                                result_tv.setText("Result: " + "Tied!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses); //display round and counts of win & lose
                                break;
                            case R.id.btnCat:  //player choose cat
                                wins++;
                                result_tv.setText("Result: " + "Win!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                            case R.id.btnEle:  //player choose elephant
                                loses++;
                                result_tv.setText("Result: " + "Lose!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                        }
                        break;
                    case 2:
                        imgView.setImageResource(R.drawable.cat);  //computer choose cat
                        mp_button.start();
                        switch (v.getId()) {
                            case R.id.btnMouse:
                                loses++;
                                result_tv.setText("Result: " + "Lose!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                            case R.id.btnCat:
                                result_tv.setText("Result: " + "Tied!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                            case R.id.btnEle:
                                wins++;
                                result_tv.setText("Result: " + "Win!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                        }
                        break;
                    case 3:
                        imgView.setImageResource(R.drawable.ele);  //computer choose elephant
                        mp_button.start();
                        switch (v.getId()) {
                            case R.id.btnMouse:
                                wins++;
                                result_tv.setText("Result: " + "Win!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                            case R.id.btnCat:
                                loses++;
                                result_tv.setText("Result: " + "Lose!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                            case R.id.btnEle:
                                result_tv.setText("Result: " + "Tied!");
                                count_tv.setText("Round: " + count+"\t\t\tWins: " + wins + "\t\t\tLoses: " + loses);
                                break;
                        }
                        break;
                }
            }
        }
    }

    protected void onDestroy() {
        mp_background.stop();
        super.onDestroy();
    }
}
