package main;


import logic.GameSystem;

public class Program {

    public static void main(String args[]) {
        GameSystem game = new GameSystem();
        game.askUserBoardDimensions();
        game.gameCycle();
    }
}
