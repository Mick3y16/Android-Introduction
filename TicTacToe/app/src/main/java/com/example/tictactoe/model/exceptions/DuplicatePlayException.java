package com.example.tictactoe.model.exceptions;

/**
 * Represents a duplicate play in tic tac toe.
 *
 * @author pedro
 */
public class DuplicatePlayException extends IllegalArgumentException {

    /**
     * Creates an instance of a duplicate play exception with a default message.
     */
    public DuplicatePlayException() {
        super("You can't make a play in an already filled square");
    }

}