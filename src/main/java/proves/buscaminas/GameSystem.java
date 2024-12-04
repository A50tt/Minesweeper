package proves.buscaminas;

import java.util.Scanner;

public class GameSystem {

    private UserMinesweeperBoard userBoard;
    private static Scanner scan;
    private boolean inGame;

    public static void main(String[] args) {
        GameSystem gameSystem = new GameSystem();
        scan = new Scanner(System.in);
        gameSystem.startGame();
    }

    public void startGame() {
        pickBoard();
        gameCycle();
    }

    public void pickBoard() {
        System.out.println("""
                           ---- Welcome to classic Minesweeper! ----
                           What dimensions do you want for the generated board?
                           a) 8 x 8 (10 bombs).
                           b) 9 x 9 (10 bombs).
                           c) 16 x 16 (40 bombs).
                           d) 30 x 16 (99 bombs).""");
        boolean optionPicked = false;
        while (!optionPicked) {
            String boardDimsOption = scan.nextLine();
            switch (boardDimsOption.toLowerCase()) {
                case "a":
                    userBoard = new UserMinesweeperBoard(8, 8, 10);
                    optionPicked = true;
                    System.out.println("8 x 8 (10 bombs) board selected.");
                    break;
                case "b":
                    userBoard = new UserMinesweeperBoard(9, 9, 10);
                    optionPicked = true;
                    System.out.println("9 x 9 (10 bombs) board selected.");
                    break;
                case "c":
                    userBoard = new UserMinesweeperBoard(16, 16, 40);
                    optionPicked = true;
                    System.out.println("16 x 16 (40 bombs) board selected.");
                    break;
                case "d":
                    userBoard = new UserMinesweeperBoard(30, 16, 99);
                    optionPicked = true;
                    System.out.println("30 x 16 (99 bombs) board selected.");
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
            //System.out.println(userBoard.getMinesweeperBoard().toString());
            System.out.println("What will you do?");
            String action = readAction();
        }
    }

    public String readAction() {
        boolean actionRead = false;
        while (!actionRead) {
            String[] actionCommands = scan.nextLine().split("\\s");
            int[] coord;
            switch (actionCommands[0].toLowerCase()) {
                //Reveal
                case ("x"):
                case ("reveal"):
                    coord = UserMinesweeperBoardUtils.coordinateConverter(userBoard, actionCommands[1]);
                    if (coord != null) {
                        if (userBoard.revealCell(coord) == -1) {
                            System.out.println("BOOOOOOOOOOOOOOOOOOM!!");
                            System.out.println(userBoard.toString());
                            System.out.println("YOU LOST!!");
                            System.out.println(userBoard.getMinesweeperBoard().toString());
                            inGame = false;
                        }
                        actionRead = true;
                    }
                    
                    break;
                //Flag
                case ("f"):
                case ("flag"):
                    coord = UserMinesweeperBoardUtils.coordinateConverter(userBoard, actionCommands[1]);
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
        return "";
    }
}
