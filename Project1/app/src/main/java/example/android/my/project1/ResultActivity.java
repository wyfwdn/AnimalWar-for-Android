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
    private ImageView imgView1, imgView2;
    private TextView result, point_view, playerId1_view, playerId2_view;
    private Button btn_again, btn_back;
    private MediaPlayer mp, mp_btn;
    int count1 = 0, count2 = 0; //initialize the points of two player
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent myIntent = getIntent();
        char choiceOne = myIntent.getCharExtra("choiceOne",'x'); //receive player1's choice
        char choiceTwo = myIntent.getCharExtra("choiceTwo",'x'); //receive player2's choice
        int pointOne = myIntent.getIntExtra("countOne",0); //receive player1's points
        int pointTwo = myIntent.getIntExtra("countTwo",0); //receive player2's points
        mp_btn = MediaPlayer.create(this, R.raw.blaster);
        mp = MediaPlayer.create(this, R.raw.result);
        mp.start();
        count1 = count1+pointOne; count2 = count2+pointTwo; //sum the points from the last turn
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
        point_view.setText(Integer.toString(count1)+'\t'+'\t'+'\t'+'\t'+':'+'\t'+'\t'+'\t'+'\t'+Integer.toString(count2));
        switch (choiceOne){
            case 'r':
                imgView1.setImageResource(R.drawable.rock);
                switch (choiceTwo) {
                    case 'r':
                        imgView2.setImageResource(R.drawable.rock);
                        result.setText("Tied!");
                        break;
                    case 's':
                        imgView2.setImageResource(R.drawable.scissors);
                        result.setText("Winner is Player1!");
                        count1++;
                        break;
                    case 'p':
                        imgView2.setImageResource(R.drawable.paper);
                        result.setText("Winner is Player2!");
                        count2++;
                        break;
                }
                break;
            case 'p':
                imgView1.setImageResource(R.drawable.paper);
                switch (choiceTwo) {
                    case 'r':
                        imgView2.setImageResource(R.drawable.rock);
                        result.setText("Winner is Player1!");
                        count1++;
                        break;
                    case 's':
                        imgView2.setImageResource(R.drawable.scissors);
                        result.setText("Winner is Player2!");
                        count2++;
                        break;
                    case 'p':
                        imgView2.setImageResource(R.drawable.paper);
                        result.setText("Tied!");
                        break;
                }
                break;
            case 's':
                imgView1.setImageResource(R.drawable.scissors);
                switch (choiceTwo) {
                    case 'r':
                        imgView2.setImageResource(R.drawable.rock);
                        result.setText("Winner is Player2!");
                        count2++;
                        break;
                    case 's':
                        imgView2.setImageResource(R.drawable.scissors);
                        result.setText("Tied!");
                        break;
                    case 'p':
                        imgView2.setImageResource(R.drawable.paper);
                        result.setText("Winner is Player1!");
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
            if (v.getId() == R.id.btnAgain){ //play again option, jump to double players activity
                mp_btn.stop();
                Intent myIntent1 = new Intent(ResultActivity.this, MultiActivity.class);
                myIntent1.putExtra("countOne",count1); //transfer player1 points
                myIntent1.putExtra("countTwo",count2); //transfer player2 points
                startActivity(myIntent1);
                finish();
            }
            else if (v.getId() == R.id.btnBack){
                mp.release();
                Intent myIntent1 = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(myIntent1);
                finish();
            }
        }
    }

}
