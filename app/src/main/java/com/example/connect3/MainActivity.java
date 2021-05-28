package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        int player=0;    //yellow=0 and red=1
        boolean gameactive=true;
        int[] game={2,2,2,2,2,2,2,2,2}; //not played is denoted by 2
        int [][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        public void dropIn(View view){
            ImageView counter=(ImageView) view;
            System.out.println(counter.getTag().toString()); //to show which tag is being selected
            int tapped=Integer.parseInt(counter.getTag().toString());
            if(game[tapped]==2) {
                game[tapped]=player;
                counter.setTranslationY(-1000f);
                if (player == 0) {
                    counter.setImageResource(R.drawable.yellow_counter);
                    player = 1;

                } else {
                    counter.setImageResource(R.drawable.red_counter);
                    player = 0;
                }
                counter.animate().translationYBy(1000f).setDuration(200);
                for(int[] winner:winPos){                    //loop for finding the winning combos..
                    if(game[winner[0]]==game[winner[1]]
                            && game[winner[1]]==game[winner[2]]
                            && game[winner[0]]!=2 ){
                        //System.out.println("winner is "+game[winner[0]]);
                        gameactive=false; //the game is finished
                        String win="Red";
                        if(game[winner[0]]==0){
                            win="yellow";
                        }
                        TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText(win+" has won!!");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgain);
                        layout.setVisibility(View.VISIBLE);
                        layout.animate().alpha(1f).setDuration(3000);
                    }
                }
            }
            else{
                boolean gameover=true; //to check whether the game is over or not
                for(int countstate:game){
                    if(countstate==2){  //if any of this is 2 then the game is still not finished
                        gameover=false;
                    }
                    if(gameover){
                        TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("its a draw");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgain);
                        layout.setVisibility(View.VISIBLE);
                        layout.animate().alpha(1f).setDuration(3000);

                    }
                }
            }
        }
        public void playagain(View view){
            gameactive=true;
            LinearLayout layout=(LinearLayout)findViewById(R.id.playAgain);
            layout.setVisibility(View.INVISIBLE);
            layout.animate().alpha(1f).setDuration(3000);
            player=0; //changing or updating to default
            for(int i=0;i<game.length;i++){     //for updating each part of the array to 2 that is unplayed
                game[i]=2;
            }
            //this is to change the images to null that is have no source since we need to change it to default
            //androidx.gridlayout.widget.GridLayout grid= findViewById(R.id.grid);
            GridLayout grid=(GridLayout)findViewById(R.id.grid);
            for(int i=0;i<grid.getChildCount();i++){
                ((ImageView) grid.getChildAt(i)).setImageResource(0);
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
