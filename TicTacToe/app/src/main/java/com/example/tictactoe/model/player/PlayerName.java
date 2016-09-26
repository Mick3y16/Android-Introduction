package com.example.tictactoe.model.player;

/**
 * Represents the name of a Tic Tac Toe player.
 *
 * @author pedro
 */
public class PlayerName {

    /**
     * Name of the player.
     */
    private String name;

    /**
     * Creates an instance of player name, through its name.
     *
     * @param name Name of the player.
     */
    public PlayerName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
