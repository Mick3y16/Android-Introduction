package com.example.tictactoe.model;

import com.example.tictactoe.model.exceptions.ImpossiblePlayException;

import java.util.Observable;

/**
 * Represents a board where tic tac toe plays are made.
 *
 * @author pedro
 */
public class GameBoard extends Observable {

    /**
     * Represents the board where plays are made.
     */
    private final Square[][] gameBoard;

    /**
     * Creates an instance of the board where the plays are made.
     */
    public GameBoard() {
        this.gameBoard = new Square[3][3];

        for (int line = 0; line < this.gameBoard.length; line++) {
            for (int column = 0; column < this.gameBoard[line].length; column++) {
                this.gameBoard[line][column] = new Square();
            }
        }
    }

    /**
     * Make a play on the game board, assigning a play symbol to a given square,
     * by its coordenates.
     *
     * @param play The symbol associated with the play.
     * @param xCoordinate The coordinate of X.
     * @param yCoordinate The coordinate of Y.
     */
    public void play(PlaySymbol play, int xCoordinate, int yCoordinate) {
        // If the play lands off the game board, it's impossible.
        if (xCoordinate < 0 || xCoordinate > 2 || yCoordinate < 0 || yCoordinate > 2) {
            throw new ImpossiblePlayException();
        }

        // Make the play
        Square square = gameBoard[xCoordinate][yCoordinate];
        square.assignPlaySymbol(play);

        // Notify game analyzer about the change
        setChanged();
        notifyObservers(this.gameBoard);
    }

}
