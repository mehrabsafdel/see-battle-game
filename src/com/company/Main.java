package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //this is an object from scanner class for scan the mood of game
       Scanner input = new Scanner(System.in);
       //mood of the game . if mood=1 game is cpu mood and if mood=2 game is for 2 players
        int mood;
        System.out.printf("1-cpu \n2-2 players\n");
        //a loop for check the mood number that must be 1 or 2 and if it isn't 1 or 2 give a new mood from user
        while (true){
            mood = input.nextInt();
            if(mood == 1 || mood == 2)
                break;
            System.out.println("bad input! try again :");
        }
        //a new object from game class
        Game game = new Game(mood);
        //this method run the base of the program
        game.mood();

    }
}
