package com.example.tictactoe.model;

import com.example.tictactoe.model.player.ComputerPlayer;
import com.example.tictactoe.model.player.HumanPlayer;
import com.example.tictactoe.model.player.Player;
import com.example.tictactoe.model.player.PlayerName;

import java.util.Random;

/**
 * Created by pedro on 17/08/2016.
 */
public class Game {

    /**
     * Board where the game is played.
     */
    private GameBoard gameBoard;

    /**
     * Analyzer that runs on each play and check for the winning condition.
     */
    private GameBoardAnalyzer gameBoardAnalyzer;

    /**
     * Players playing the game.
     */
    private Player[] players;

    /**
     * Plays made during the game.
     */
    private Integer plays;

    /**
     * Winner of the game.
     */
    private Player winner;

    /**
     * Creates an instance of a game where two players play each other.
     *
     * @param playerOneName Name of the first player.
     * @param playerOneScore Score of the first player.
     * @param playerTwoName Name of the second player.
     * @param playerTwoScore Score of the second player.
     */
    public Game(String playerOneName, Integer playerOneScore, String playerTwoName, Integer playerTwoScore) {
        // Tic tac toe requires two players.
        this.players = new Player[2];
        this.players[0] = new HumanPlayer(new PlayerName(playerOneName), playerOneScore, new PlaySymbol("X"));
        this.players[1] = new HumanPlayer(new PlayerName(playerTwoName), playerTwoScore, new PlaySymbol("O"));

        // Initialize the board.
        this.initialize();
    }

    /**
     * Creates an instance of a game where a player plays against the computer.
     *
     * @param playerOneName Name of the player.
     * @param playerOneScore Score of the player.
     * @param playerTwoScore Score of the computer.
     * @param playerTwoDifficulty Difficulty of the computer.
     */
    public Game(String playerOneName, Integer playerOneScore, Integer playerTwoScore, String playerTwoDifficulty) {
        // Tic tac toe requires two players.
        this.players = new Player[2];
        this.players[0] = new HumanPlayer(new PlayerName(playerOneName), playerOneScore, new PlaySymbol("X"));
        this.players[1] = new ComputerPlayer(playerTwoScore, new PlaySymbol("O"), playerTwoDifficulty);

        // Initialize the board.
        this.initialize();
    }

    /**
     * Returns the name of player one.
     *
     * @return The name of the first player.
     */
    public String playerOneName() {
        return this.players[0].name().toString();
    }

    /**
     * Returns the score of player one.
     *
     * @return The score of the first player.
     */
    public int playerOneScore() {
        return this.players[0].score();
    }

    /**
     * Returns the name of player two.
     *
     * @return The name of the second player.
     */
    public String playerTwoName() {
        return this.players[1].name().toString();
    }

    /**
     * Returns the score of player two.
     *
     * @return The score of the second player.
     */
    public int playerTwoScore() {
        return this.players[1].score();
    }

    /**
     * Returns the name of the next player.
     *
     * @return Name of the next player.
     */
    public PlayerName nextPlayerName() {
        return this.players[this.plays % 2].name();
    }

    /**
     * Returns the winner of the tic tac toe game, or null if the game is still
     * going.
     *
     * @return The winner of the tic tac toe game.
     */
    public Player winner() {
        return this.winner;
    }

    /**
     * Make a play on the given coordinates, this method is able to determine whose
     * turn it is, based on the number of plays made.
     *
     * @param xCoordinate Coordinate of X.
     * @param yCoordinate Coordinate of Y.
     */
    public PlaySymbol play(int xCoordinate, int yCoordinate) {
        int player = this.plays % 2;

        PlaySymbol playSymbol = this.players[player].play(xCoordinate, yCoordinate);
        this.plays++;

        return playSymbol;
    }

    /**
     * Declares the winner of a tic tac toe game based on the play symbol used.
     *
     * @param playSymbol Play symbol used in the winning play, used to identify
     * the player.
     */
    public void declareWinner(PlaySymbol playSymbol) {
        for (int player = 0; player < this.players.length; player++) {
            if (this.players[player].symbol().equals(playSymbol)) {
                this.winner = this.players[player];
                this.winner.increaseScore();
            }
        }
    }

    /**
     * Resets the score of both players.
     */
    public void resetScores() {
        this.players[0].resetScore();
        this.players[1].resetScore();
    }

    /**
     * Checks if a tic tac toe game has reached the end.
     *
     * @return True if the game of tic tac toe is over, false otherwise.
     */
    public boolean isOver() {
        // There is no winner or 9 plays have not been made.
        return this.winner != null || this.plays == 9;
    }

    /**
     * Creates the game board for a tic tac toe game.
     */
    public void initialize() {
        // The game board analyzer analyzes the game board and notifies the game.
        this.gameBoard = new GameBoard();
        this.gameBoardAnalyzer = new GameBoardAnalyzer(this);
        this.gameBoard.addObserver(this.gameBoardAnalyzer);

        // Assign the game board to the players and setup the game.
        this.players[0].assignGameBoard(this.gameBoard);
        this.players[1].assignGameBoard(this.gameBoard);
        this.winner = null;
        this.plays = 0;
    }

}
