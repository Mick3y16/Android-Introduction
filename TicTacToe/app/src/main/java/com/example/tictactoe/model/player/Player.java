package com.example.tictactoe.model.player;

import com.example.tictactoe.model.GameBoard;
import com.example.tictactoe.model.PlaySymbol;

/**
 * Represents a Tic Tac Toe player.
 *
 * @author pedro
 */
public interface Player {

    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public PlayerName name();

    /**
     * Returns the score of the player.
     *
     * @return The score of the player.
     */
    public Integer score();

    /**
     * Returns the play symbol used by the player.
     *
     * @return The play symbol used by the player.
     */
    public PlaySymbol symbol();

    /**
     * Assign the game board to the player, allowing him to play.
     *
     * @param gameBoard Game board.
     */
    public void assignGameBoard(GameBoard gameBoard);

    /**
     * Allows a player to make a play.
     *
     * @param xCoordinate Coordinate of X in the game board.
     * @param yCoordinate Coordinate of Y in the game board.
     * @return The play symbol used in the play.
     */
    public PlaySymbol play(int xCoordinate, int yCoordinate);

    /**
     * Increases the score of the player.
     */
    public void increaseScore();

    /**
     * Resets the score of the player.
     */
    public void resetScore();

}
