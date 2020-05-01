package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * this class is a game class that has a mood field that decide
 * to what game play
 *
 * @author mehrab
 * @version 1.0
 * @since 4/3/2018
 */
public class Game {
    //field that decide the play
    private int mood;
    //field that scan
    Scanner input;
    //field that build random number
    private Random random;

    /**
     * this constructor build objects from classes for scan and random
     *
     * @param mood is the mood of game
     */
    public Game(int mood){
        this.mood = mood;
        input = new Scanner(System.in);
        random = new Random();
        //mood = 1 cpu playing
        //mood = 2 2 players
    }

    /**
     * this method check the points of player's table and if there is ship on that return true
     * else return false
     * @param player is the player that need its table
     * @param x is the length of starting point that ship start building
     * @param y is the wide of starting point that ship start building
     * @param ship is a ship that need its side and its length
     * @return the type of exist ship on the place of new ship
     */
    public boolean existShip(Player player,int x,int y,Ship ship){
            if (ship.getSide() == 1){//if side=1 it's mean ship is vertical
                for (int i = 0; i < ship.getLength(); i++) {
                    if (player.getTable().getTable(x + i,y) != 0)//check the type of point on the player's table
                        return true;//if that point of player's table is not 0 it's mean that point is full
                }
                return false;//otherwise that points all is empty
            }
            else {//the ship is horizontal
                for (int i = 0; i < ship.getLength(); i++) {
                    if (player.getTable().getTable(x ,y + i) != 0)//check the type of point on the player's table
                        return true;//if that point of player's table is not 0 it's mean that point is full
                }
                return false;//otherwise that points all is empty
            }
    }

    /**
     * this method check the mood of game
     * if mood is 1 it's means the game is for one player
     * and if mood is 2 it's means the game is for 2 players
     *
     */
    public void mood(){
        //this field is for attacking's players and if it is 1 it's means attack is correct and if it's 2 it means attack is wrong attack
        int attackMood;
        //one player mood
        if (mood == 1){
            //build new players from proper class
            Player player = new Player();
            Player cpu = new Player();
            //this field is for fixthe length and side of the ship of player
            int len ,sid ;
            //this field is for placing ships and attack of player and cpu
            int x,y,cpuX,cpuY;
            //this 2 array is for saving each attack of player and cpu
            ArrayList<Point> cpuPoint = new ArrayList<>();
            ArrayList<Point> playerPoint = new ArrayList<>();
            //place 5 ships  for player
            for (int i = 0; i < 5; i++) {
                System.out.println("length and side(0-horizontal  1-vertical)");
                //scan length of ship
                len = input.nextInt();
                //scan side of ship
                sid = input.nextInt();
                //check that the length and side is valid and if they are invalid scan a new length and side
                while ((len < 2 || len > 5) || (sid < 0 || sid > 1)) {
                    System.out.println("BAD INPUT .TRy AGAIN \n length and side");
                    len = input.nextInt();
                    sid = input.nextInt();
                }
                //build a new ship from valid length and side
                Ship ship = new Ship(len,sid);
                System.out.println("staring point");
                //the details of point of starting point of ship's place
                x = input.nextInt();
                y = input.nextInt();
                //check the details of point and check that place is empty and if it isn't scan point
                while (((x < 0 || x > 9) || (y < 0 || y > 9)) || ((sid == 0 && x + len > 10) || (sid == 1 && y + len > 10)) || existShip(player,y,x,ship)) {
                    System.out.println("BAD INPUT .TRy AGAIN \n starting point");
                    x = input.nextInt();
                    y = input.nextInt();
                }
                //add a ship to the player class
                player.addShip(ship,y,x);
            }
            //this fields is for the length and side of the ship of cpu
            int length,side;
            //place 5 ship for cpu
            for (int i = 0; i < 5; i++) {
                System.out.println("cpu's turn");
                //length of ship is a random number between 2 and 5
                length = random.nextInt(4) + 2;
                //side of ship is a random number between 0 and 1
                side = random.nextInt(2);
                //build a new ship from proper length and side
                Ship ship = new Ship(length,side);
                //check the side of ship
                if (side == 0) {
                    //this is for entering to below while
                    cpuX = -1;
                    cpuY = -1;
                    //this loop is make a random point for starting point of ship and check the place of ship is empty
                    while (cpuX < 0 || cpuY < 0 || existShip(cpu,cpuY,cpuX,ship)){
                        //this random number has a condition is that the ship must be in the table
                        cpuX = random.nextInt(10) - length + 1;
                        //random number between 0 and 10
                        cpuY = random.nextInt(10) ;
                    }
                    //add ship to the cpu class by proper details
                    cpu.addShip(ship, cpuY, cpuX);
                }
                //check the side of ship
                else if (side == 1){
                    //this is for entering to below while
                    cpuX = -1;
                    cpuY = -1;
                    //this loop is make a random point for starting point of ship and check the place of ship is empty
                    while (cpuX < 0 || cpuY < 0 || existShip(cpu,cpuY,cpuX,ship)) {
                        //this random number has a condition is that the ship must be in the table
                        cpuY = random.nextInt(10) - length + 1;
                        //random number between 0 and 10
                        cpuX = random.nextInt(10);
                    }
                    //add ship to the cpu class by proper details
                    cpu.addShip(ship,cpuY,cpuX);
                }
            }
            System.out.println("which type of attack do you want?\n1-usual attack  2-wrong attack");
            //scan the type of the attack of player
            attackMood = input.nextInt();
            //check the attackMood field has proper number and if it hasn't scan a new attackMood
            while (attackMood < 1 || attackMood > 2){
                System.out.println("wrong choice ! TRY again");
                attackMood = input.nextInt();
            }
            //this loop is for start attacking between players and until one of the is alive continious
            while (player.isAllive() && cpu.isAllive()){
                //print the table of player that show the place of itself ships and type of them
                player.printOwnTable();
                System.out.println();
                //print the table of player that show the attacking places and its type
                cpu.printEnemyTable();
                System.out.println("player turn / attack");
                //scan the point that player want to attack
                x = input.nextInt();
                y = input.nextInt();
                //check that the point is proper and if it isn't scan a new point
                while ((x < 0 || x > 9) || (y < 0 || y > 9) || playerPoint.contains(new Point(y,x))) {
                    System.out.println("BAD INPUT .TRy AGAIN \n attack");
                    x = input.nextInt();
                    y = input.nextInt();
                }
                //this fields is use for saving las number of point
                int properyX = x,propertyY = y;
                //check the mood of player attack
                if (attackMood == 2){
                    //x is a random place beside the last x
                    x -= random.nextInt(3) - 1;
                    //x is a random place beside the last x
                    y -= random.nextInt(3) - 1;
                    //check that the x and y is proper or not and it must be a new point
                    while (x < 0 || y < 0 || playerPoint.contains(new Point(y,x)) || (x == properyX && y == propertyY)) {
                        x -= random.nextInt(3) - 1;
                        y -= random.nextInt(3) - 1;
                    }
                }
                //save this point to the array
                playerPoint.add(new Point(y,x));
                //attack enemy
                cpu.attack(y,x);
                //check the type of player life
                if (!cpu.isAllive()){
                    System.out.println("player win!");
                    break;
                }
                //make a random point for cpu's attack
                cpuX = random.nextInt(10);
                cpuY = random.nextInt(10);
                //check the recurrent point
                while (cpuPoint.contains(new Point(cpuX,cpuY))){
                    cpuX = random.nextInt(10);
                    cpuY = random.nextInt(10);
                }
                //save this point to the array
                cpuPoint.add(new Point(cpuX,cpuY));
                //attack player
                player.attack(cpuY,cpuX);
                //check the type of cpu life
                if (!player.isAllive()){
                    System.out.println("cpu win!");
                    break;
                }
            }
        }

        else {
            //this fields is use for the length and side of ships
            int length ,side ;
            //this fields is use for the starting pint of ship and attacking point
            int x,y;
            //make 2 new array for saving each attack details
            ArrayList<Point> p1point = new ArrayList<>();
            ArrayList<Point> p2point = new ArrayList<>();
            //make 2 player
            Player player1 = new Player();
            Player player2 = new Player();
            System.out.println("player1");
            player1.printOwnTable();
            //place 5 ships for player 1
            for (int i = 0; i < 5; i++) {
                System.out.println("length and side");
                //scan the length and the side of ship
                length = input.nextInt();
                side = input.nextInt();
                //check the length and side
                while ((length < 2 || length > 5) || (side < 0 || side > 1)) {
                    System.out.println("BAD INPUT .TRy AGAIN \n length and side(0-horizontal  1-vertical)");
                    length = input.nextInt();
                    side = input.nextInt();
                }
                //make a new ship from length and side
                Ship ship = new Ship(length,side);
                System.out.println("staring point");
                //fixing the starting point of ship
                x = input.nextInt();
                y = input.nextInt();
                //check the place of ship
                while (((x < 0 || x > 9) || (y < 0 || y > 9)) || ((side == 0 && x + length > 10) || (side == 1 && y + length > 10)) || existShip(player1,y,x,ship)) {
                    System.out.println("BAD INPUT .TRy AGAIN \n starting point (x , y)");
                    x = input.nextInt();
                    y = input.nextInt();
                }
                //add ship tp player 1 class
                player1.addShip(ship,y,x);
            }
            //player 2
            System.out.println("player2");
            player2.printOwnTable();
            //place 5 ships for player 2
            for (int i = 0; i < 5; i++) {
                System.out.println("length and side");
                //scan the length and side of ship
                length = input.nextInt();
                side = input.nextInt();
                //check the length and side of ship
                while ((length < 2 || length > 5) || (side < 0 || side > 1)) {
                    System.out.println("BAD INPUT .TRy AGAIN \n length and side(0-horizontal  1-vertical)");
                    length = input.nextInt();
                    side = input.nextInt();
                }
                //make a new ship from length adn side
                Ship ship = new Ship(length,side);
                System.out.println("staring point");
                //fixing the staring point of ship
                x = input.nextInt();
                y = input.nextInt();
                //check the ship's place
                while (((x < 0 || x > 9) || (y < 0 || y > 9)) || ((side == 0 && x + length > 10) || (side == 1 && y + length > 10)) || existShip(player2,y,x,ship)) {
                    System.out.println("BAD INPUT .TRy AGAIN \n starting point (x,y)");
                    x = input.nextInt();
                    y = input.nextInt();
                }
                //add a ship to player 2 class
                player2.addShip(ship,y,x);
            }
            //this field is for check the type of attack mood of player 2
            int attackMood2;
            System.out.println("PLAYER1 : which type of attack do you want?\n1-usual attack  2-wrong attack");
            //fix the type of attack for player 1
            attackMood = input.nextInt();
            //check the attackMood of player 1
            while (attackMood < 1 || attackMood > 2){
                System.out.println("wrong choice ! TRY again");
                attackMood = input.nextInt();
            }
            System.out.println("PLAYER2 : which type of attack do you want?\n1-usual attack  2-wrong attack");
            //fixing the attackMood of player 2
            attackMood2 = input.nextInt();
            //check the attackMood2
            while (attackMood2 < 1 || attackMood2 > 2){
                System.out.println("wrong choice ! TRY again");
                attackMood2 = input.nextInt();
            }
            //this loop is for attacking player
            while (player1.isAllive() && player2.isAllive()){
                //print the table of player 1 that shows the ships and them shape
                player1.printOwnTable();
                System.out.println();
                //print the table of player 1 that shows the attacking point
                player2.printEnemyTable();
                System.out.println("player 1 turn / attack");
                //scan the attacking point from player 1
                x = input.nextInt();
                y = input.nextInt();
                //check the point for attacking
                while ((x < 0 || x > 9) || (y < 0 || y > 9) || p1point.contains(new Point(y,x))) {
                    System.out.println("BAD INPUT .TRy AGAIN \n attack (x,y)");
                    x = input.nextInt();
                    y = input.nextInt();
                }
                //this fields is for saving the last number of x and y of point for attacking
                int propertyX = x,propertyY = y;
                //checl the mood of attacking
                if (attackMood == 2){
                    x -= random.nextInt(3) - 1;
                    y -= random.nextInt(3) - 1;
                    //check the shape of x and y for them proper number
                    while (x < 0 || y < 0 || p1point.contains(new Point(y,x)) ||(x == propertyX && y == propertyY)) {
                        x -= random.nextInt(3) - 1;
                        y -= random.nextInt(3) - 1;
                    }
                }
                //add point tho array to save that details
                p1point.add(new Point(y,x));
                //attack player 2
                player2.attack(y,x);
                //check the player 2 life
                if (!player2.isAllive()){
                    System.out.println("player 1 won!");
                    break;
                }
                //print the table of player 2 that shows the ships and them places
                player2.printOwnTable();
                System.out.println();
                //print the table of player 2 that shows the attacking points
                player1.printEnemyTable();
                System.out.println("player 2 turn / attack");
                //scan the point for attacking from player 2
                x = input.nextInt();
                y = input.nextInt();
                //check the property of point
                while ((x < 0 || x > 9) || (y < 0 || y > 9) ||  p2point.contains(new Point(y,x))) {
                    System.out.println("BAD INPUT .TRy AGAIN \n attack (x,y)");
                    x = input.nextInt();
                    y = input.nextInt();
                }
                //save the last number of x and y for wrong attack
                propertyX = x;
                propertyY = y;
                //check the type of attack
                if (attackMood2 == 2){
                    //make a random place for attacking
                    x -= random.nextInt(3) - 1;
                    y -= random.nextInt(3) - 1;
                    //check that random place
                    while (x < 0 || y < 0 ||p2point.contains(new Point(y,x)) ||(x == propertyX && y == propertyY)) {
                        x -= random.nextInt(3) - 1;
                        y -= random.nextInt(3) - 1;
                    }
                }
                //add this point to the array for save that attacking point details
                p2point.add(new Point(y,x));
                //attack player 1
                player1.attack(y,x);
                //check the player 1 life
                if (!player1.isAllive()){
                    System.out.println("player 2 won!");
                    break;
                }
            }
        }
    }
}

