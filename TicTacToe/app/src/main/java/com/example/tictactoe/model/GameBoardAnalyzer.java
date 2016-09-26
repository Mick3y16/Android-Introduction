package com.example.tictactoe.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by pedro on 19/08/2016.
 */
public class GameBoardAnalyzer implements Observer {

    /**
     * Game being analyzed.
     */
    private Game game;

    /**
     * Creates a new instance of a game board analyzer responsible for analyzing
     * a game.
     *
     * @param game Game notified by the analyzer when the win condition is met.
     */
    public GameBoardAnalyzer(Game game) {
        this.game = game;
    }

    @Override
    public void update(Observable observable, Object changedObject) {
        /* GameBoard is Observable and the received object is a matrix representing the said board */
        GameBoard gameBoard = (GameBoard) observable;
        Square[][] gameBoardMatrix = (Square[][]) changedObject;

        // Check lines
        for (int line = 0; line < gameBoardMatrix.length; line++) {
            if (gameBoardMatrix[line][0].playSymbol() != null
                    && gameBoardMatrix[line][0].equals(gameBoardMatrix[line][1])
                    && gameBoardMatrix[line][0].equals(gameBoardMatrix[line][2])) {
                // Found a winner, publish results...
                game.declareWinner(gameBoardMatrix[line][0].playSymbol());

                return;
            }
        }

        // Check column
        for (int column = 0; column < gameBoardMatrix[0].length; column++) {
            if (gameBoardMatrix[0][column].playSymbol() != null
                    && gameBoardMatrix[0][column].equals(gameBoardMatrix[1][column])
                    && gameBoardMatrix[0][column].equals(gameBoardMatrix[2][column])) {
                // Found a winner, publish results...
                game.declareWinner(gameBoardMatrix[0][column].playSymbol());

                return;
            }
        }

        // Check diagonal
        if (gameBoardMatrix[0][0].equals(gameBoardMatrix[1][1])
                && gameBoardMatrix[0][0].equals(gameBoardMatrix[2][2])) {
            // Found a winner, publish results...
            game.declareWinner(gameBoardMatrix[0][0].playSymbol());
        }
    }
}
