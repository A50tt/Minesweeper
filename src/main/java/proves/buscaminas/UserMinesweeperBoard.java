package proves.buscaminas;

public class UserMinesweeperBoard {

    private String[][] cells;
    private boolean[][] isRevealedCell;
    private MinesweeperBoard msBoard;
    private String SYMBOL_FOR_BLANK_CELLS = " ";
    private String SYMBOL_FOR_FLAGGING_CELLS = "X";

    public UserMinesweeperBoard(int x, int y, int bombs) {
        msBoard = new MinesweeperBoard(x, y, bombs);
        this.cells = new String[y][x];
        this.isRevealedCell = new boolean[y][x];
        createBlankBoard();
    }

    public MinesweeperBoard getMinesweeperBoard() {
        return this.msBoard;
    }

    public String[][] getCells() {
        return this.cells;
    }

    @Override
    public String toString() {
        String str = "";
        int x = 0, y;
        for (int i = 0; i <= cells.length; i++) {
            if (i > 0) {
                System.out.print(i);
            }
            System.out.print("\t ");
        }
        System.out.println();
        for (String[] row : cells) {
            x++;
            y = 0;
            str += (char) (x + 64) + "\t";
            for (String cell : row) {
                str += "[" + cell + "]\t";
                y++;
            }
            str += "\n";
        }
        return str;
    }

    private void createBlankBoard() {
        int x = 0, y;
        for (String[] row : this.cells) {
            y = 0;
            for (String cell : row) {
                this.cells[x][y] = SYMBOL_FOR_BLANK_CELLS;
                y++;
            }
            x++;
        }
    }

    public int revealCell(int[] coord) {
        int x = coord[0];
        int y = coord[1];
        int cellValue = this.msBoard.getCells()[x][y];
        this.cells[x][y] = String.valueOf(cellValue);
        this.isRevealedCell[x][y] = true;
        revealZeroValueCells(coord);
        return cellValue;
    }
    
    public void flagCell(int[] coord) {
        int x = coord[0];
        int y = coord[1];
        if (this.cells[x][y] == SYMBOL_FOR_BLANK_CELLS) {
            this.cells[x][y] = SYMBOL_FOR_FLAGGING_CELLS;
        } else if (this.cells[x][y] == SYMBOL_FOR_FLAGGING_CELLS) {
            this.cells[x][y] = SYMBOL_FOR_BLANK_CELLS;
        }
    }
    
    public void revealZeroValueCells(int[] coord) {
        int x = coord[0];
        int y = coord[1];
        
        // <--  ^
        //      |
        if ((x - 1 != -1) && (y - 1 != -1)) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x-1, y-1};
                if (!isRevealedCell[x-1][y-1]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }

        // ^
        // |
        if (y - 1 != -1) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x, y-1};
                if (!isRevealedCell[x][y-1]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }

        // ^
        // | -->
        if ((x + 1 != cells.length) && (y - 1 != -1)) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x+1, y-1};
                if (!isRevealedCell[x+1][y-1]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }

        // <--
        if (x - 1 != -1) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x-1, y};
                if (!isRevealedCell[x-1][y]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }

        // -->
        if (x + 1 != cells.length) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x+1, y};
                if (!isRevealedCell[x+1][y]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }

        // |
        // V <--
        if ((x - 1 != -1) && (y + 1 != cells[x].length)) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x-1, y+1};
                if (!isRevealedCell[x-1][y+1]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }

        // |
        // V
        if (y + 1 != cells[x].length) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x, y+1};
                if (!isRevealedCell[x][y+1]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }

        // |
        // V -->
        if ((x + 1 != cells.length) && (y + 1 != cells[x].length)) {
            if (msBoard.getCells()[x][y] == 0) {
                int[] coordNew = {x+1, y+1};
                if (!isRevealedCell[x+1][y+1]) {
                    revealCell(coordNew);
                    revealZeroValueCells(coordNew);
                }
            }
        }
    }
}
