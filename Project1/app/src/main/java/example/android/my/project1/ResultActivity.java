package example.android.my.project1;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by Administrator on 2017/3/17.
 */

public class ResultActivity extends AppCompatActivity {
    private ImageView imgView1, imgView2; //players' choices
    private TextView result, point_view, playerId1_view, playerId2_view;
    private Button btn_again, btn_back; //play again(jump to multiActivity) and back to main menu
    private MediaPlayer mp_1p, mp_2p, mp_tied, mp_btn; //player1 win's sound,player2 win's sound,tied sound and button sound
    int count1 = 0, count2 = 0; //initialize the points of two player

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent myIntent = getIntent();
        char choiceOne = myIntent.getCharExtra("choiceOne",'x'); //receive player1's choice
        char choiceTwo = myIntent.getCharExtra("choiceTwo",'x'); //receive player2's choice
        int pointOne = myIntent.getIntExtra("countOne",0); //receive player1's points
        int pointTwo = myIntent.getIntExtra("countTwo",0); //receive player2's points
        //create MediaPlayers
        mp_btn = MediaPlayer.create(this, R.raw.blaster);
        mp_1p = MediaPlayer.create(this, R.raw.p1win);
        mp_2p = MediaPlayer.create(this, R.raw.p2win);
        mp_tied = MediaPlayer.create(this, R.raw.tied);

        count1 = count1+pointOne; count2 = count2+pointTwo; //sum the points from the last turn
        //declare all views
        point_view = (TextView) findViewById(R.id.point);
        playerId1_view = (TextView) findViewById(R.id.playerId1);
        playerId2_view = (TextView) findViewById(R.id.playerId2);
        result = (TextView)  findViewById(R.id.resultView);
        btn_again = (Button) findViewById(R.id.btnAgain);
        btn_back = (Button) findViewById(R.id.btnBack);
        imgView1 = (ImageView) findViewById(R.id.choice1);
        imgView2 = (ImageView) findViewById(R.id.choice2);

        MyOnClickListener listener1 = new MyOnClickListener();
        btn_again.setOnClickListener(listener1);
        btn_back.setOnClickListener(listener1);
        //display point board
        point_view.setText(Integer.toString(count1)+'\t'+'\t'+'\t'+'\t'+':'+'\t'+'\t'+'\t'+'\t'+Integer.toString(count2));
        //display players' choices and result according to previous activity
        switch (choiceOne){
            case 'm':
                imgView1.setImageResource(R.drawable.mouse);
                switch (choiceTwo) {
                    case 'm':
                        imgView2.setImageResource(R.drawable.mouse);
                        result.setText("Tied!");
                        mp_tied.start();
                        break;
                    case 'e':
                        imgView2.setImageResource(R.drawable.ele);
                        result.setText("Winner is Player1!");
                        count1++;
                        mp_1p.start();
                        break;
                    case 'c':
                        imgView2.setImageResource(R.drawable.cat);
                        result.setText("Winner is Player2!");
                        mp_2p.start();
                        count2++;
                        break;
                }
                break;
            case 'c':
                imgView1.setImageResource(R.drawable.cat);
                switch (choiceTwo) {
                    case 'm':
                        imgView2.setImageResource(R.drawable.mouse);
                        result.setText("Winner is Player1!");
                        count1++;
                        mp_1p.start();
                        break;
                    case 'e':
                        imgView2.setImageResource(R.drawable.ele);
                        result.setText("Winner is Player2!");
                        count2++;
                        mp_2p.start();
                        break;
                    case 'c':
                        imgView2.setImageResource(R.drawable.cat);
                        result.setText("Tied!");
                        mp_tied.start();
                        break;
                }
                break;
            case 'e':
                imgView1.setImageResource(R.drawable.ele);
                switch (choiceTwo) {
                    case 'm':
                        imgView2.setImageResource(R.drawable.mouse);
                        result.setText("Winner is Player2!");
                        count2++;
                        mp_2p.start();
                        break;
                    case 'e':
                        imgView2.setImageResource(R.drawable.ele);
                        result.setText("Tied!");
                        mp_tied.start();
                        break;
                    case 'c':
                        imgView2.setImageResource(R.drawable.cat);
                        result.setText("Winner is Player1!");
                        mp_1p.start();
                        count1++;
                        break;
                }
                break;

                }
        point_view.setText(Integer.toString(count1)+'\t'+'\t'+'\t'+'\t'+':'+'\t'+'\t'+'\t'+'\t'+Integer.toString(count2)); //update points display

    }


    private class MyOnClickListener implements View.OnClickListener{
        public void onClick(View v) {
            mp_btn.start();
            //play again option, jump to multiple players activity
            if (v.getId() == R.id.btnAgain){
                mp_btn.stop();
                Intent myIntent1 = new Intent(ResultActivity.this, MultiActivity.class);
                myIntent1.putExtra("countOne",count1); //transfer player1 points
                myIntent1.putExtra("countTwo",count2); //transfer player2 points
                startActivity(myIntent1);
                finish();
            }
            //go back to main menu
            else if (v.getId() == R.id.btnBack){
                mp_1p.release();
                mp_tied.release();
                mp_2p.release();
                Intent myIntent1 = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(myIntent1);
                finish();
            }
        }
    }

}
