package com.example.tictactoe.model.exceptions;

/**
 * Represents an impossible play in tic tac toe.
 *
 * @author pedro
 */
public class ImpossiblePlayException extends ArrayIndexOutOfBoundsException {

    /**
     * Creates an instance of an impossible play exception with a default
     * message.
     */
    public ImpossiblePlayException() {
        super("The play must be made within the bounds of the board.");
    }

}