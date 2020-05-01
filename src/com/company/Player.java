package com.company;
import java.util.ArrayList;

/**
 * this class is a player class that play the game
 * you can add ship and attack print the table of player
 * by proper methods
 *
 * @author mehrab
 * @version 1.0
 * @since 4/3/2018
 */
public class Player {
    //this field is a table of Table class
    private Table table;
    //this field is an array list from ship
    private ArrayList<Ship> ships;

    /**
     * @return the table of player
     */
    public Table getTable() {
        return table;
    }
    //this fields is the number of player's ships
    private int numberOfShips;
    //this fields is used for coloring the out put
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * this constructor ake a new table and array list
     * and set the number of ship is 0
     */
    public Player(){
        table = new Table();
        ships = new ArrayList<>();
        numberOfShips = 0;
    }

    /**
     * this method add a ship to the player's table
     * @param ship is a ship that add to the table of player
     * @param x is the length of point of starting point of ship
     * @param y is the wide of point of starting point of ship
     */
    public void addShip(Ship ship,int x,int y){
        if (numberOfShips < 5){
           try {
               ships.add(ship);
               numberOfShips++;
               //place the ship to the table
               table.placeShip(ship, x, y);
           }
           catch (Exception e){
           }
        }
        else
            System.out.println("your capacity is full !");

    }

    /**
     * this method attack to the ship
     * @param x is the length of point for attacking
     * @param y is the wide of point for attacking
     */
    public void attack(int x,int y){
        //atack to the table
        table.attackPoint(x,y);
    }

    /**
     * this method return the type of life of player
     * if player has ship return true
     * adn if hasn't ship return false
     * @return the type of life of player
     */
    public boolean isAllive(){
        for (Ship ship:ships) {
            if (ship.isAllive())
                return true;
        }
        return false;
    }

    /**
     * this method print the table for own player
     * shows the place of ships and points of ship is safe print @ and else print #
     */
    public void printOwnTable(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getTable(i,j) == 0 || table.getTable(i,j) == 2)
                    System.out.printf("_|");//print empty point
                if (table.getTable(i,j) == 1)
                    System.out.printf(ANSI_BLUE + "@|");//print safe point
                System.out.printf(ANSI_RESET);
                if (table.getTable(i,j) == 3)
                    System.out.printf(ANSI_RED + "#|");//print attacked point
                System.out.printf(ANSI_RESET);
            }
            System.out.println();
        }
    }

    /**
     * this method print the player table for enemy that
     * shows the enemy's attack and if attack is good print &
     * else print X for bad attack
     */
    public void printEnemyTable(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getTable(i,j) == 0 || table.getTable(i,j) == 1)
                    System.out.printf("_|");//print empty point
                if (table.getTable(i,j) == 2)
                    System.out.printf(ANSI_YELLOW + "X|");//print bad attack
                System.out.printf(ANSI_RESET);
                if (table.getTable(i,j) == 3)
                    System.out.printf(ANSI_GREEN + "&|");//print good attack
                System.out.printf(ANSI_RESET);
            }
            System.out.println();
        }
    }
}
