
package proves.buscaminas;

import java.util.ArrayList;
import java.util.Random;

public class CellsUtils {
    
    public static int[][] assignBombs(int x, int y, int bombs) {      
        int[][] cells = new int[x][y];
        int row, column;
        Random rand = new Random();
        int bombsWithoutAssignment = bombs;
        ArrayList<Integer> cellsWithBombsAssigned = new ArrayList<Integer>();
        while (bombsWithoutAssignment != 0) {
            int assignBombToCell = rand.nextInt(1, (x * y)+1);
            if (!cellsWithBombsAssigned.contains(assignBombToCell)) {
                if (assignBombToCell%x != 0) {
                    row = assignBombToCell/x;
                } else {
                    row = (assignBombToCell/x)-1;
                }                
                if (assignBombToCell%y != 0) {
                    column = (assignBombToCell%y)-1;
                } else {
                    column = assignBombToCell/y;
                    if (assignBombToCell == (x * y)) {
                        column -= 1;
                    }
                }
                System.out.println(" / " + assignBombToCell);  
                cells[row][column] = -1;
                cellsWithBombsAssigned.add(assignBombToCell);
                bombsWithoutAssignment--;
            }
        }
        return cells;
    }
    
    public static int[][] assignNumbers (int[][] cells) {
        int x = 0;
        for (int[] row : cells) {
            int y = 0;
            for (int cell: row) {
                if (cell != -1) {
                    // <--  ^
                    //      |
                    if ((x - 1 != -1) && (y - 1 != -1)) {
                        if (cells[x-1][y-1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // ^
                    // |
                    if (y - 1 != -1) {
                        if (cells[x][y-1] == -1) {
                            cells[x][y] += 1;;
                        }
                    }

                    // ^
                    // | -->
                    if ((x + 1 != cells.length) && (y - 1 != -1)) {
                        if (cells[x+1][y-1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // <--
                    if (x - 1 != -1) {
                        if (cells[x-1][y] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // -->
                    if (x + 1 != cells.length) {
                        if (cells[x+1][y] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // |
                    // V <--
                    if ((x - 1 != -1) && (y + 1 != cells[x].length)) {
                        if (cells[x-1][y+1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // |
                    // V
                    if (y + 1 != cells[x].length) {
                        if (cells[x][y+1] == -1) {
                            cells[x][y] += 1;
                        }
                    }

                    // |
                    // V -->
                    if ((x + 1 != cells.length) && (y + 1 != cells[x].length)) {
                        if (cells[x+1][y+1] == -1) {
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
