package com.example.tictactoe.model;

/**
 * Created by pedro on 19/08/2016.
 */
public class PlaySymbol {

    /**
     * String representing the play symbol.
     */
    private String playSymbol;

    /**
     * Creates an instance of a play symbol through a string representing it.
     *
     * @param playSymbol String representing the play symbol.
     */
    public PlaySymbol(String playSymbol) {
        this.playSymbol = playSymbol;
    }

    @Override
    public String toString() {
        return this.playSymbol;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (anotherObject == null || this.getClass() != anotherObject.getClass()) {
            return false;
        }

        PlaySymbol anotherPlaySymbol = (PlaySymbol) anotherObject;

        return this.playSymbol.equals(anotherPlaySymbol.playSymbol);
    }

}
