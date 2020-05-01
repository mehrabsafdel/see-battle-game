package com.company;
import java.util.ArrayList;

/**
 * this is a table class
 * this class is like a map that length and side is 10
 *
 * @author mehrab
 * @version 1.0
 * @since 4/3/2018
 */
public class Table {
    //this fields is a 2 array that like a table
    private int [][] table;
    //this method is a array list from SHIP class
    private ArrayList<Ship> ships;

    /**
     * @param i the length of point
     * @param j the wide of point
     * @return the point
     */
    public int getTable(int i,int j){
        return table[i][j];
    }

    /**
     * this constructor make a 10*10 table
     */
    public Table(){
        table = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table[i][j] = 0;
            }
        }
        ships = new ArrayList<>();
    }

    /**
     * this method attack a point in the table and check that point
     * if there is a ship on that point attack the ship
     * else attack an empty point
     * @param x is the length of point
     * @param y is the wide of point
     */
    public void attackPoint(int x,int y){
        if (table[x][y] == 0)//empty point
            table[x][y] = 2;
        //this mean this point was empty and attacked
        else if (table[x][y] == 1){//ship point
            table[x][y] = 3;
            //this means this point has a ship and attacked
            getShipOfPoint(new Point(x, y)).attacked(new Point(x,y));
            //attack to the ship
        }
    }

    /**
     * this method place a ship from starting point
     * @param ship is the ship that want to place in to the table
     * @param X is the length of starting point
     * @param Y is the wide of starting point
     */
    public void placeShip(Ship ship,int X,int Y){
        //check the side of ship
        if (ship.getSide() == 0){
            //add ship to the array
            ships.add(ship);
            //add points of ships to the table
            for (int i = 0; i < ship.getLength(); i++) {
                if (table[X][Y + i] == 0){//check the point is empty
                    table[X][Y + i] = 1;
                    //add each point to the ship class
                    ship.addPoint(new Point(X,Y + i));
                }
            }
        }
        else if (ship.getSide() == 1){
            //add ship to the array
            ships.add(ship);
            for (int i = 0; i < ship.getLength(); i++) {
                if (table[X + i][Y] == 0){//check the point is empty
                    table[X + i][Y] = 1;
                    //add each points to the ship class
                    ship.addPoint(new Point(X+i,Y));
                }
            }
        }
    }

    /**
     * this method  gave the ship that has point that input to this method
     * @param point is one of the ships points
     * @return the ship that has the point
     */
    public Ship getShipOfPoint(Point point){
        for (Ship ship:ships) {
            if (ship.hasPoint(point))
            return ship;
        }
            //if there isn't ant ship that has that point return nothing
        return null;
    }

}
