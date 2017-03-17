package example.android.my.project1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/16.
 */

public class SingleActivity extends Activity {

    private ImageView imgView;  //the first ImageView in the view
    private ImageButton r_imgBtn, p_imgBtn, s_imgBtn;  // Rock, Paper, Scissors
    private Button back_Btn, pause_Btn;

    private TextView result_tv, count_tv;  //the textView of result and count
    int count = 0; // initialize the count

    //intialize a listener to monitoring the three buttons
    SingleActivity.MyOnClickListener myOnClickListener = new SingleActivity.MyOnClickListener();

    //add two MediaPlayer objects
    private MediaPlayer mp_background;
    private MediaPlayer mp_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        //added

        r_imgBtn = (ImageButton) findViewById(R.id.buttonRock);
        p_imgBtn = (ImageButton) findViewById(R.id.buttonPaper);
        s_imgBtn = (ImageButton) findViewById(R.id.btnSci);
        pause_Btn = (Button) findViewById(R.id.btnPause);
        back_Btn = (Button) findViewById(R.id.btnBack);

        //initialize imgView
        imgView = (ImageView) findViewById(R.id.viewCmp);

        //initialize result and count TextView
        result_tv = (TextView) findViewById(R.id.textResult);
        count_tv = (TextView) findViewById(R.id.textCount);

        r_imgBtn.setOnClickListener(myOnClickListener);
        p_imgBtn.setOnClickListener(myOnClickListener);
        s_imgBtn.setOnClickListener(myOnClickListener);
        pause_Btn.setOnClickListener(myOnClickListener);
        back_Btn.setOnClickListener(myOnClickListener);
        //declare the audio resource to these two MediaPlayer objects
        mp_background = MediaPlayer.create(this, R.raw.single);
        mp_button = MediaPlayer.create(this, R.raw.blaster);


        //play background music here
        mp_background.start();
        mp_background.setLooping(true);
        //end

    }

    //added
    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            //play button sound here


            // get a random number form 1 to 3
            int rand = (int) (Math.random() * 3 + 1);

            //store times of game in device
//            storeData(count+"");
            if (v.getId() == R.id.btnPause) {
                if (mp_background.isPlaying()) {
                    mp_background.pause();
                } else {
                    mp_background.start();
                }
            }
            else if (v.getId() == R.id.btnBack){
                mp_background.stop();
                Intent myIntent3 = new Intent(SingleActivity.this, MainActivity.class);
                SingleActivity.this.finish();
                startActivity(myIntent3);
            }
            else {
                count++;//
                switch (rand) {
                    /**
                     * rand = 1 means computer is Rock,
                     * 2 represents Paper,
                     * 3 represents scissors
                     */
                    case 1:
                        imgView.setImageResource(R.drawable.rock);  //computer choose Rock
                        mp_button.start();
                        switch (v.getId()) {
                            case R.id.buttonRock:   //player choose Rock
                                result_tv.setText("Result: "
                                        + "Tied!");
                                count_tv.setText("Round: " + count);
                                break;
                            case R.id.buttonPaper:  //player choose Paper
                                result_tv.setText("Result: "
                                        + "Win!");
                                count_tv.setText("Round: " + count);
                                break;
                            case R.id.btnSci:  //player choose Scissors
                                result_tv.setText("Result: "
                                        + "Lose!");
                                count_tv.setText("Round: " + count);
                                break;
                        }
                        break;
                    case 2:
                        imgView.setImageResource(R.drawable.paper);  //computer choose Paper
                        mp_button.start();
                        switch (v.getId()) {
                            case R.id.buttonRock:
                                result_tv.setText("Result: "
                                        + "Lose!");
                                count_tv.setText("Round: " + count);
                                break;
                            case R.id.buttonPaper:
                                result_tv.setText("Result: "
                                        + "Tie!");
                                count_tv.setText("Round: " + count);
                                break;
                            case R.id.btnSci:
                                result_tv.setText("Result: "
                                        + "Win!");
                                count_tv.setText("Round: " + count);
                                break;
                        }
                        break;
                    case 3:
                        imgView.setImageResource(R.drawable.scissors);  //computer choose Scissors
                        mp_button.start();
                        switch (v.getId()) {
                            case R.id.buttonRock:
                                result_tv.setText("Result: "
                                        + "Win!");
                                count_tv.setText("Round: " + count);
                                break;
                            case R.id.buttonPaper:
                                result_tv.setText("Result: "
                                        + "Lose!");
                                count_tv.setText("Round: " + count);
                                break;
                            case R.id.btnSci:
                                result_tv.setText("Result: "
                                        + "Tie!");
                                count_tv.setText("Round: " + count);
                                break;
                        }
                        break;
                }
            }
        }
    }

//    public void storeData(String count)
//    {
//        FileOutputStream outputStream;
//        Date date = new Date(System.currentTimeMillis());
//        try {
//            outputStream = openFileOutput("record.txt", Context.MODE_PRIVATE);
//            outputStream.write(date.toString().getBytes());
//            outputStream.write("    ".getBytes());
//            outputStream.write(count.getBytes());
//            outputStream.write(System.getProperty("line.separator").getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //end

    protected void onDestroy() {
        mp_background.stop();
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
