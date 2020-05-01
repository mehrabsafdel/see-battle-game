package com.company;

/**
 * this class is a point class that has length and wide
 *
 * @author mehrab
 * @version 1.1
 * @since 4/3/2018
 */
public class Point {
    //the length of point
    private int X;
    //the wide of point
    private int Y;

    /**
     * @param x is the length of point
     * @param y is the wide of point
     */
    public Point(int x,int y){
        this.X = x;
        this.Y = y;
    }

    /**
     * this method is override from super class
     * that check if the length and wide of 2 point is equals returns true
     * @param obj is an object that type of that is point
     * @return true if 2 point are equals and flase if 2 points are not equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this.X == ((Point) obj).X && this.Y == ((Point) obj).Y)
            return true;
        else
            return false;
    }
}
