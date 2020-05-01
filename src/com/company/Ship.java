package com.company;

import java.util.ArrayList;

/**
 * this class is a ship that has a length and a side
 * you can get the length and side of ship and attack to the ship
 * by proper methods
 * you can check the ship is safe or not
 * and can check this ship has a point or not
 *
 * @author mehrab
 * @version 1.0
 * @since 4/3/2018
 */
public class Ship {
    //this field is the length of ship
    private int length;
    //this field is the side of ship
    //side is 1 means ship is vertical and side is 2 means ship is horizontal
    private int side;
    //a new array list from POint
    private ArrayList<Point> points;

    /**
     * this constructor set the length and side of shipd
     * @param length is the length od ship
     * @param side is te side of ship
     */
    public Ship(int length,int side){
        this.length = length;
        this.side = side;
        points = new ArrayList<>();
    }

    /**
     * @return the length field
     */
    public int getLength() {
        return length;
    }

    /**
     * @return the side fields
     */
    public int getSide() {
        return side;
    }

    /**
     * add a point to the array
     * @param point is the point that add to the array
     */
    public void addPoint(Point point){
        points.add(point);
    }

    /**
     * check the safe of ship
     * if all of points of ship was attacked  return false
     * else return true
     * @return the safe of ship
     */
    public boolean isAllive(){
       if (points.isEmpty())
           return false;
       else
           return true;
    }

    /**
     * @param point is the point of ship that attacked
     */
    public void attacked(Point point){
        points.remove(point);
    }

    /**
     * this method check that this ship has a special point or not
     * @param point is the point that checked this ship has or not
     * @return if ship has this point return true else return false
     */
    public boolean hasPoint(Point point){
        if (points.contains(point))
            return true;
        else
            return false;
    }

}
