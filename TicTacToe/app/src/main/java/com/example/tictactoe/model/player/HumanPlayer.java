package com.example.tictactoe.model.player;

import com.example.tictactoe.model.GameBoard;
import com.example.tictactoe.model.PlaySymbol;

/**
 * Represents a Tic Tac Toe human player.
 *
 * @author pedro
 */
public class HumanPlayer implements Player {

    /**
     * Board where the player is playing.
     */
    private GameBoard gameBoard;

    /**
     * Name of the player.
     */
    private PlayerName playerName;

    /**
     * Score of the player.
     */
    private int score;

    /**
     * Play symbol of the player.
     */
    private PlaySymbol playSymbol;

    /**
     * Creates an instance of an human player through the board where the player is playing, its
     * name, score and play symbol.
     *
     * @param playerName Name of the player.
     * @param score Score of the player.
     * @param playSymbol Play symbol of the player.
     */
    public HumanPlayer(PlayerName playerName, Integer score, PlaySymbol playSymbol) {
        this.playerName = playerName;
        this.score = score;
        this.playSymbol = playSymbol;
    }

    @Override
    public PlayerName name() {
        return this.playerName;
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
        this.gameBoard.play(this.playSymbol, xCoordinate, yCoordinate);

        return this.playSymbol;
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
