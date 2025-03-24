package logic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameSystem {

    private Board userBoard;
    private static Scanner scan;
    private boolean inGame;
    
    public GameSystem() {
        scan = new Scanner(System.in);
        this.startGame();
    }

    public void startGame() {
        pickBoard();
        gameCycle();
    }
    
    public void endGame() {
        this.inGame = false;
    }

    public void pickBoard() {
        System.out.println("""
                           ---- Welcome to classic Minesweeper! ----
                           What dimensions do you want for the generated board?
                           a) 8 x 8 (10 bombs).
                           b) 9 x 9 (10 bombs).
                           c) 16 x 16 (40 bombs).
                           d) 30 x 16 (99 bombs).
                           f) Custom board.""");
        boolean optionPicked = false;
        while (!optionPicked) {
            String boardDimsOption = scan.nextLine();
            switch (boardDimsOption.toLowerCase()) {
                case "0":
                    userBoard = new Board(3, 3, 1);
                    optionPicked = true;
                    System.out.println("3 x 3 (1 bomb) board selected.");
                    break;
                case "a":
                    userBoard = new Board(8, 8, 10);
                    optionPicked = true;
                    System.out.println("8 x 8 (10 bombs) board selected.");
                    break;
                case "b":
                    userBoard = new Board(9, 9, 10);
                    optionPicked = true;
                    System.out.println("9 x 9 (10 bombs) board selected.");
                    break;
                case "c":
                    userBoard = new Board(16, 16, 40);
                    optionPicked = true;
                    System.out.println("16 x 16 (40 bombs) board selected.");
                    break;
                case "d":
                    userBoard = new Board(30, 16, 99);
                    optionPicked = true;
                    System.out.println("30 x 16 (99 bombs) board selected.");
                    break;
                 case "f":
                    int customX, customY, customBombs;
                    while(true) {
                        try {
                            System.out.println("Width of board: ");
                            customX = scan.nextInt();
                            System.out.println("Height of board: ");
                            customY = scan.nextInt();
                            System.out.println("Number of bombs: ");
                            customBombs = scan.nextInt();
                            if (customBombs > (customX * customY) || customX < 1 || customY < 1 || customBombs < 1 || customX > 30 || customY > 30) {
                                throw new InputMismatchException();
                            }
                            break;
                        } catch (InputMismatchException ex) {
                            System.out.println("The numbers have to be between 1 and 30");
                            System.out.println("There can't be more or equal bombs than cells.");
                        }
                    }
                    userBoard = new Board(customX, customY, customBombs);
                    optionPicked = true;
                    System.out.println(customX + " x " + customY + " (" + customBombs + " bombs) board selected.");
                    break;
                default:
                    System.out.println("The input is not a valid option, please try again.");
            }
            System.out.println("Enter the input 'help' for more information about the commands.");
        }
    }

    public void gameCycle() {
        System.out.println("");
        inGame = true;
        while (inGame) {
            System.out.println(userBoard.toString());
            //System.out.println(userBoard.getRevealedBoard().toString());
            System.out.println("What will you do?");
            readActions();
        }
    }

    public void readActions() {
        boolean actionRead = false;
        while (!actionRead) {
            String[] actionCommands = scan.nextLine().split("\\s");
            int[] coord;
            switch (actionCommands[0].toLowerCase()) {
                //Reveal
                case ("x"):
                case ("reveal"):
                    coord = BoardUtils.coordinateConverter(userBoard, actionCommands[1]);
                    if (coord != null) {
                        if (userBoard.revealCell(coord).equals("-1")) {
                            System.out.println("BOOOOOOOOOOOOOOOOOOM!!");
                            System.out.println(userBoard.toString());
                            System.out.println("YOU LOST!!");
                            System.out.println(userBoard.getRevealedBoard().toString());
                            inGame = false;
                        } else if (userBoard.checkIfGameIsWon()) {
                            System.out.println(this.userBoard.getRevealedBoard());
                            System.out.println("GAME WON!");
                            inGame = false;
                        }
                        actionRead = true;
                    }
                    break;
                //Flag
                case ("f"):
                case ("flag"):
                    coord = BoardUtils.coordinateConverter(userBoard, actionCommands[1]);
                    if (coord != null) {
                        userBoard.flagCell(coord);
                        actionRead = true;
                    }
                    break;
                //Cry for help commands
                case ("help"):
                    System.out.println("Commands:\n"
                            + "The format should be: action LN (L = 'Letter of cell', N = 'Number of cell').\n"
                            + "Actions:\n"
                            + "x/reveal - to reveal a cell.\n"
                            + "f/flag - to flag/unflag a cell.\n\n"
                            + "Examples:\n"
                            + "x A1 - reveal cell A1.\n"
                            + "reveal A1 - reveal cell A1.\n"
                            + "f B11 - flag/unflag cell C11.\n"
                            + "flag B11 - flag/unflag cell C11.");
                    break;
                default:
                    System.out.println("Command not recognised, please try again.");
            }
        }
    }
}
