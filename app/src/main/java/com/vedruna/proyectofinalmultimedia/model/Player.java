package com.vedruna.proyectofinalmultimedia.model;

import androidx.annotation.NonNull;
/**
 * Player class represents a player entity with id, name, position, and clubName attributes.
 */
public class Player {
    public int idPlayer;
    public String name;
    public String position;
    public String clubName;
    /**
     * Default constructor for Player class.
     */
    public Player() {
    }
    /**
     * Constructs a Player object with the specified attributes.
     *
     * @param idPlayer  The unique identifier of the player.
     * @param name      The name of the player.
     * @param position  The position of the player.
     * @param clubName  The name of the club the player belongs to.
     */
    public Player(int idPlayer, String name, String position, String clubName) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.position = position;
        this.clubName = clubName;
    }
    /**
     * Getter method for retrieving the id of the player.
     *
     * @return The id of the player.
     */
    public int getIdPlayer() {
        return idPlayer;
    }
    /**
     * Setter method for setting the id of the player.
     *
     * @param idPlayer The id of the player to be set.
     */

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }
    /**
     * Getter method for retrieving the name of the player.
     *
     * @return The name of the player.
     */

    public String getName() {
        return name;
    }
    /**
     * Setter method for setting the name of the player.
     *
     * @param name The name of the player to be set.
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter method for retrieving the position of the player.
     *
     * @return The position of the player.
     */

    public String getPosition() {
        return position;
    }
    /**
     * Setter method for setting the position of the player.
     *
     * @param position The position of the player to be set.
     */

    public void setPosition(String position) {
        this.position = position;
    }
    /**
     * Getter method for retrieving the name of the club the player belongs to.
     *
     * @return The name of the club the player belongs to.
     */
    public String getClubName() {
        return clubName;
    }
    /**
     * Setter method for setting the name of the club the player belongs to.
     *
     * @param clubName The name of the club the player belongs to.
     */
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }


    /**
     * Overrides the toString method to provide a formatted string representation of the Player object.
     *
     * @return A formatted string representation of the Player object.
     */
    @NonNull
    @Override
    public String toString() {
        return "Id: " + getIdPlayer() + " / Nombre: " + getName() + " / Posicion: " + getPosition() + " / equipo: " + getClubName();
    }
}
