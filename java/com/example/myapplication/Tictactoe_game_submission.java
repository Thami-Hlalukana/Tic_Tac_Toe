package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tictactoe_game_submission extends AppCompatActivity implements View.OnClickListener {
    private TextView Player1;
    private TextView Player2;

    private int player1_points;
    private int player2_points;

    private final Button[][] buttons = new Button[3][3];
    private boolean player1_turn = true;

    private int Counter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Player1 = findViewById(R.id.player1_textview);
        Player2 = findViewById(R.id.player2_textview);

        for(int i =0; i < 3; i++){
            for(int j =0; j<3; j++){
                String Btn_ID = "button_" + i + j;
                @SuppressLint("DiscouragedApi") int resourceID = getResources().getIdentifier(Btn_ID,"id", getPackageName());
                buttons[i][j] = findViewById(resourceID);
                buttons[i][j].setOnClickListener(this);

            }
        }

        Button Reset_btn = findViewById(R.id.reset_button);
        Reset_btn.setOnClickListener(view -> Game_reset());
    }

    @Override
    public void onClick(View v){
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(player1_turn){
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        Counter++;

        if(winner()) {
            if (player1_turn) {
                player1_winner();
            } else {
                player2_winner();
            }
        }
        else if(Counter==9){
                draw();
            }
        else{
            player1_turn=!player1_turn;
        }
    }


    private void Game_reset(){
        player1_points=0;
        player2_points=0;
        updatepoints();
        resetBoard();
    }
    @SuppressLint("SetTextI18n")
    private void updatepoints(){
        Player1.setText("Player1: " + player1_points);
        Player2.setText("Player 2: " + player2_points);
    }

    private void resetBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                buttons[i][j].setText("");
            }
            Counter=0;
            player1_turn=true;
        }
    }
    private void player1_winner(){
        player1_points++;
        Toast.makeText(this,"player 1 wins!! :)", Toast.LENGTH_SHORT).show();
        updatepoints();
        resetBoard();

    }

    private void player2_winner(){
        player2_points++;
        Toast.makeText(this,"player 2 wins!! :)", Toast.LENGTH_SHORT).show();
        updatepoints();
        resetBoard();

    }

    private void draw(){
        Toast.makeText(this, "Its a draw. :0", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private boolean winner(){
        String [][] square= new String [3][3];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                square[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0; i<3; i++){
            if(square[0][i].equals(square[1][i])&&square[0][i].equals(square[2][i])&&square[0][i].equals("")){
                return true;
            }
        }

        for(int i=0; i<3; i++){
            if(square[i][0].equals(square[i][1])&&square[i][0].equals(square[i][2])&&square[i][0].equals("")){
                return true;
            }
        }


        if(square[0][0].equals(square[1][1])&&square[0][0].equals(square[2][2])&&square[0][0].equals("")){
            return true;
        }

        if(square[0][2].equals(square[1][1])&&square[0][2].equals(square[2][0])&&square[0][2].equals("")){
            return true;
        }

        return false;


    }
}