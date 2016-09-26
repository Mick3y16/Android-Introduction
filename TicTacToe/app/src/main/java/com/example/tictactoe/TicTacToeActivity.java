package com.example.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.model.Game;
import com.example.tictactoe.model.PlaySymbol;
import com.example.tictactoe.model.exceptions.DuplicatePlayException;
import com.example.tictactoe.model.exceptions.ImpossiblePlayException;

public class TicTacToeActivity extends AppCompatActivity {

    private Game game;

    private Button[][] boardButtons;

    private TextView scoreBoard;

    private TextView message;

    private SharedPreferences sharedPreferences;

    private MediaPlayer play;

    private MediaPlayer victory;

    private boolean sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        // Get all the buttons used to draw the game game.
        this.boardButtons = new Button[3][3];
        this.boardButtons[0][0] = (Button) findViewById(R.id.button1);
        this.boardButtons[0][1] = (Button) findViewById(R.id.button2);
        this.boardButtons[0][2] = (Button) findViewById(R.id.button3);
        this.boardButtons[1][0] = (Button) findViewById(R.id.button4);
        this.boardButtons[1][1] = (Button) findViewById(R.id.button5);
        this.boardButtons[1][2] = (Button) findViewById(R.id.button6);
        this.boardButtons[2][0] = (Button) findViewById(R.id.button7);
        this.boardButtons[2][1] = (Button) findViewById(R.id.button8);
        this.boardButtons[2][2] = (Button) findViewById(R.id.button9);

        // Get all text views.
        this.scoreBoard = (TextView) findViewById(R.id.scoreBoard);
        this.message = (TextView) findViewById(R.id.message);

        // Load sounds
        this.play = MediaPlayer.create(getApplicationContext(), R.raw.play);
        this.victory = MediaPlayer.create(getApplicationContext(), R.raw.victory);

        // Loads the game preferences from a file.
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sound = this.sharedPreferences.getBoolean("sound", true);

        String playerName = this.sharedPreferences.getString("player_name", getResources().getString(R.string.player_name));
        Integer playerOneScore = this.sharedPreferences.getInt("human_score", 0);
        Integer playerTwoScore = this.sharedPreferences.getInt("computer_score", 0);
        String difficulty = this.sharedPreferences.getString("difficulty_level", getResources().getString(R.string.difficulty_easy));

        // Instantiate the game game.
        this.game = new Game(playerName, playerOneScore, playerName, playerTwoScore);
        startGame();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt("human_score", this.game.playerOneScore());
        editor.putInt("computer_score", this.game.playerTwoScore());
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemMenuNewGame:
                startGame();
                break;
            case R.id.itemMenuConfig:
                startActivityForResult(new Intent(this, Settings.class), 0);
                break;
            case R.id.itemMenuReset:
                this.game.resetScores();
                updateScore();
                Toast.makeText(getApplicationContext(), "Scores were reset!", Toast.LENGTH_LONG);
                break;
            case R.id.itemMenuExit:
                new AlertDialog.Builder(TicTacToeActivity.this)
                        .setMessage("Do you wish to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TicTacToeActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create().show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CANCELED) {
            this.sound = this.sharedPreferences.getBoolean("sound", true);
        }
    }

    /**
     * Starts the game both internally and externally.
     */
    private void startGame() {
        // Start the game (internally)
        this.game.initialize();

        // Start the game (externally)
        for (int line = 0; line < this.boardButtons.length; line++) {
            for (int column = 0; column < this.boardButtons[0].length; column++) {
                this.boardButtons[line][column].setText("");
                this.boardButtons[line][column].setOnClickListener(new ButtonClickListener(line, column));
            }
        }

        updateScore();
        this.message.setText("Turn: " + game.playerOneName());
    }


    /**
     * Represents a button click listener responsible for listening to actions performed to a
     * specific button.
     */
    public class ButtonClickListener implements View.OnClickListener {

        /**
         * X position of the button.
         */
        private int xPosition;

        /**
         * Y position of the button.
         */
        private int yPosition;

        /**
         * Creates an instance of a button click listener through the button's position.
         *
         * @param xPosition X position of the button.
         * @param yPosition Y position of the button.
         */
        public ButtonClickListener(int xPosition, int yPosition) {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }

        @Override
        public void onClick(View view) {
            try {
                PlaySymbol playSymbol = TicTacToeActivity.this.game.play(this.xPosition, this.yPosition);
                if (TicTacToeActivity.this.sound) TicTacToeActivity.this.play.start(); // Start play sound if the sound is enabled.
                TicTacToeActivity.this.boardButtons[this.xPosition][this.yPosition].setText(playSymbol.toString());
                TicTacToeActivity.this.boardButtons[this.xPosition][this.yPosition].setTextColor(Color.rgb(0, 200, 0));

                if (!TicTacToeActivity.this.game.isOver()) {
                    TicTacToeActivity.this.message.setText("Turn: " + TicTacToeActivity.this.game.nextPlayerName());
                } else {
                    String message;

                    if (TicTacToeActivity.this.game.winner() == null) {
                        message = "It's a draw.";
                    } else {
                        // If there is a winner play the winning sound.
                        if (TicTacToeActivity.this.sound) TicTacToeActivity.this.victory.start();
                        message = "Winner: " + TicTacToeActivity.this.game.winner().name();
                    }

                    updateScore();
                    playAgain(message);
                }
            } catch (DuplicatePlayException | ImpossiblePlayException ex) {
                TicTacToeActivity.this.message.setText(ex.getMessage());
            }
        }
    }

    /**
     * Updates the score board after the game is over.
     */
    private void updateScore() {
        String scoreText = ""
                + this.game.playerOneName() + ": " + this.game.playerOneScore()
                + " VS "
                + this.game.playerTwoName() + ": " + this.game.playerTwoScore();
        this.scoreBoard.setText(scoreText);
    }

    /**
     * Shows an alert asking the player if he wishes to play again.
     */
    private void playAgain(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TicTacToeActivity.this);
        builder.setMessage(message + "\nPlay again?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        startGame();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TicTacToeActivity.this.finish();
                    }
                })
                .create()
                .show();
    }

}
