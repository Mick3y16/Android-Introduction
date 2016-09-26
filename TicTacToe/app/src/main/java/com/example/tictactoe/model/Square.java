package com.example.tictactoe.model;


import com.example.tictactoe.model.exceptions.DuplicatePlayException;

import java.util.Objects;

/**
 * Represents a square where a play can be registered.
 *
 * @author pedro
 */
public class Square {

    /**
     * Symbol
     */
    private PlaySymbol playSymbol;

    /**
     * Creates an instance of a square, where a play can be registered.
     */
    public Square() {
    }

    /**
     * Returns the play symbol registered to this square.
     *
     * @return The play symbol registered to this square.
     */
    public PlaySymbol playSymbol() {
        return this.playSymbol;
    }

    /**
     * Assigns a play to a square if it has not been filled already.
     *
     * @param playSymbol Symbol registered to the square.
     */
    public void assignPlaySymbol(PlaySymbol playSymbol) {
        if (this.playSymbol != null) {
            throw new DuplicatePlayException();
        }

        this.playSymbol = playSymbol;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (anotherObject == null || this.getClass() != anotherObject.getClass()) {
            return false;
        }

        Square anotherSquare = (Square) anotherObject;

        return Objects.equals(this.playSymbol, anotherSquare.playSymbol);
    }

}
