package logic;

import java.util.ArrayList;
import java.util.Random;

public class BoardUtils {

    public static int[] coordinateConverter(Board board, String coordinates) {
        int[] xyArr = new int[2];
        char[] chArray = coordinates.toCharArray();
        if (chArray.length >= 2) {
            boolean letterPhase = true;
            for (char ch : chArray) {
                if (letterPhase) {
                    if (Character.isLetter(ch)) {
                        xyArr[0] += Character.getNumericValue(ch) - 10;
                        letterPhase = false;
                    } else {
                        System.out.println("Error, expected only a letter as the first character in the coordinates.");
                        return null;
                    }
                } else if (Character.isLetter(ch)) {
                    System.out.println("Error, expected only one letter and the number of column in the coordinates.");
                        return null;
                } else if (Character.isDigit(ch)) {
                    xyArr[1] = Integer.valueOf(xyArr[1] + String.valueOf(ch));
                    letterPhase = false;
                } else {
                    System.out.println("The input " + ch + " is not a letter, nor a number.");
                    return null;
                }
            }
            xyArr[1] -= 1;
            return xyArr;
        } else {
            System.out.println("Coordinates input not recognised.\n"
                + "The format should be: action LN (action = f/flag/x/reveal, L = 'Letter of cell', N = 'Number of cell').");
            return null;
        }
    }
    
    public static int[][] assignBombs(int x, int y, int bombs) {
        int[][] cells = new int[y][x];
        int row, column;
        Random rand = new Random();
        int bombsWithoutAssignment = bombs;
        ArrayList<Integer> cellsWithBombsAssigned = new ArrayList<Integer>();
        while (bombsWithoutAssignment != 0) {
            int assignBombToCell = rand.nextInt(1, (x * y) + 1);
            if (!cellsWithBombsAssigned.contains(assignBombToCell)) {
                if (assignBombToCell < x) {
                    row = 0;
                } else if (assignBombToCell % x == 0) {
                    row = (assignBombToCell / x) - 1;
                } else {
                    row = (assignBombToCell / x);
                }
                
                if (assignBombToCell < x) {
                    column = assignBombToCell - 1;
                } else if (assignBombToCell % x != 0) {
                    column = (assignBombToCell % x) - 1;
                } else {
                    column = (y - 1);
                }
                cells[row][column] = -1;
                cellsWithBombsAssigned.add(assignBombToCell);
                bombsWithoutAssignment--;
            }
        }
        return cells;
    }
    
    public static int[][] assignNumbers(int[][] cells) {
        /*
        int[][] cells = new int[stringedCells.length][stringedCells[0].length];
        for (int i = 0; i < stringedCells.length; i++) {
            for (int y = 0; y < stringedCells[i].length; y++) {
                cells[i][y] = Integer.valueOf(stringedCells[i][y]);
            }
        }
        */
        int x = 0;
        for (int[] row : cells) {
            int y = 0;
            for (int cell : row) {
                if (cell != -1) {
                    // <--  ^
                    //      |
                    if ((x - 1 != -1) && (y - 1 != -1)) {
                        if (cells[x - 1][y - 1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // ^
                    // |
                    if (y - 1 != -1) {
                        if (cells[x][y - 1] == -1) {
                            cells[x][y] += 1;;
                        }
                    }

                    // ^
                    // | -->
                    if ((x + 1 != cells.length) && (y - 1 != -1)) {
                        if (cells[x + 1][y - 1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // <--
                    if (x - 1 != -1) {
                        if (cells[x - 1][y] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // -->
                    if (x + 1 != cells.length) {
                        if (cells[x + 1][y] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // |
                    // V <--
                    if ((x - 1 != -1) && (y + 1 != cells[x].length)) {
                        if (cells[x - 1][y + 1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // |
                    // V
                    if (y + 1 != cells[x].length) {
                        if (cells[x][y + 1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // |
                    // V -->
                    if ((x + 1 != cells.length) && (y + 1 != cells[x].length)) {
                        if (cells[x + 1][y + 1] == -1) {
                            cells[x][y] += 1;
                        }
                    }
                }
                y++;
            }
            x++;
        }
        return cells;
    }

}
