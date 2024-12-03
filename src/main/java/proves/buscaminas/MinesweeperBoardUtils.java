package proves.buscaminas;

import java.util.ArrayList;
import java.util.Random;

public class MinesweeperBoardUtils {

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
                
                /*
                row = (assignBombToCell / x) - 1;
                if (assignBombToCell % x == 0 || assignBombToCell < x) {
                    if (assignBombToCell != (x * y)) {
                        row += 1;
                    }
                }
                
                column = (assignBombToCell % x) - 1;
                if (assignBombToCell % x == 0 || assignBombToCell < x) {
                    column += 1;
                }
                */
                
                System.out.println(" / " + assignBombToCell);
                cells[row][column] = -1;
                cellsWithBombsAssigned.add(assignBombToCell);
                bombsWithoutAssignment--;
            }
        }
        return cells;
    }

    public static int[][] assignNumbers(int[][] cells) {
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

    public static int[] coordinateConverter(MinesweeperBoard board, String coordinates) {
        int[] xyArr = new int[2];
        char[] chArray = coordinates.toCharArray();
        boolean letterPhase = false;
        for (char ch : chArray) {
            if (Character.isLetter(ch)) {
                if (letterPhase) {
                    xyArr[1] = Character.getNumericValue(ch) - 10;
                } else {
                    return null;
                }
            } else if (Character.isDigit(ch)) {
                xyArr[0] = Integer.valueOf(String.valueOf(xyArr[0]) + String.valueOf(ch));
                letterPhase = true;
            } else {
                System.out.println("The input " + ch + " is not a letter, nor a number.");
                return null;
            }
        }
        return xyArr;
    }
}
