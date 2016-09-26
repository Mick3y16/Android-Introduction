package com.example.tictactoe.model.player;

import com.example.tictactoe.model.GameBoard;
import com.example.tictactoe.model.PlaySymbol;

/**
 * Represents a Tic Tac Toe computer player.
 *
 * @author pedro
 */
public class ComputerPlayer implements Player {

    /**
     * Board where the computer is playing.
     */
    private GameBoard gameBoard;

    /**
     * Score of the computer.
     */
    private int score;

    /**
     * Play symbol of the computer.
     */
    private PlaySymbol playSymbol;

    /**
     * Level of the computer.
     */
    private String difficulty;

    /**
     * Creates an instance of an computer player through the board where it is playing, its score
     * and play symbol.
     *
     * @param score Score of the player.
     * @param playSymbol Play symbol of the player.
     * @param difficulty Difficulty of the player.
     */
    public ComputerPlayer(Integer score, PlaySymbol playSymbol, String difficulty) {
        this.gameBoard = gameBoard;
        this.score = score;
        this.playSymbol = playSymbol;
        this.difficulty = difficulty;
    }

    @Override
    public PlayerName name() {
        return new PlayerName("Computer");
    }

    @Override
    public Integer score() {
        return this.score;
    }

    @Override
    public PlaySymbol symbol() {
        return this.playSymbol;
    }

    @Override
    public void assignGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public PlaySymbol play(int xCoordinate, int yCoordinate) {
        // TODO: Add AI code for play here.. :/
        this.difficulty.toString();
        return null;
    }

    @Override
    public void increaseScore() {
        this.score++;
    }

    @Override
    public void resetScore() {
        this.score = 0;
    }
}
