package com.company.numberguessingame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView textViewLast,textViewRight,textviewhint;
    private Button buttonconfirmed;
    private EditText edittextguess;

    boolean twoDigits,threeDigits,fourDigits;

    Random r= new Random();
    int random;
    int remainingRight = 10;

    ArrayList<Integer>guessesList = new ArrayList<>();
    int UserAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textviewhint= findViewById(R.id.textViewhint);
        textViewLast= findViewById(R.id.textViewlast);
        textViewRight= findViewById(R.id.textViewRight);
        buttonconfirmed= findViewById(R.id.buttonconfirmed);
        edittextguess= findViewById(R.id.edittextguess);

        twoDigits= getIntent().getBooleanExtra("two",false);
        threeDigits= getIntent().getBooleanExtra("three",false);
        fourDigits= getIntent().getBooleanExtra("four",false);

        if (twoDigits)
        {
            random = r.nextInt(90)+10;
        }

        if (threeDigits)
        {
            random = r.nextInt(900)+100;
        }
        if (fourDigits)
        {
            random = r.nextInt(9000)+1000;
        }

        buttonconfirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = edittextguess.getText().toString();
                if (guess.equals(""))
                {
                    Toast.makeText(GameActivity.this,"please enter a guess",Toast.LENGTH_LONG).show();

                }
                else
                {
                    textViewLast.setVisibility(View.VISIBLE);
                    textviewhint.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    UserAttempts++;
                    remainingRight--;


                    int UserGuess= Integer.parseInt(guess);
                    guessesList.add(UserGuess);

                    textViewLast.setText("Your last guess is : " +guess);
                    textViewRight.setText("Your remaining right : " +remainingRight);
                    if (random == UserGuess )
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("congratulations. My guess was "+random
                                + "\n\n You know my number in  "+UserAttempts
                                + " attempts.  \n\n Your guesses : " +guessesList
                                + " \n\n Would you like to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();


                    }
                    if (random < UserGuess )
                    {
                        textviewhint.setText("Decrease your guess");
                    }
                    if (random > UserGuess )
                    {
                    textviewhint.setText("increase your guess");
                    }

                    if (remainingRight == 0)
                    {

                            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                            builder.setTitle("Number Guessing Game");
                            builder.setCancelable(false);
                            builder.setMessage("SORRY, Your right to guess is over"
                                    + "\n\n My guess was "+UserAttempts
                                    + "   \n\n Your guesses : " +guessesList
                                    + " \n\n Would you like to play again?");
                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            });
                            builder.create().show();
                    }
                    edittextguess.setText("");
                }
            }
        });



    }
}