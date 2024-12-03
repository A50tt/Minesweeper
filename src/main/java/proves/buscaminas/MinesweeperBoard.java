package proves.buscaminas;

public class MinesweeperBoard {

    int[][] cells;
    int bombs;

    public int[][] getCells() {
        return cells;
    }

    public void setBombs(int _bombs) {
        this.bombs = _bombs;
    }

    public void addBomb() {
        this.bombs++;
    }

    public MinesweeperBoard(int x, int y, int _bombs) {
        this.bombs = _bombs;
        this.cells = MinesweeperBoardUtils.assignBombs(x, y, bombs);
        this.cells = MinesweeperBoardUtils.assignNumbers(this.cells);
    }

    public MinesweeperBoard(int[][] _cells) {
        this.cells = _cells;
        this.bombs = 0;
        for (int[] row : this.cells) {
            for (int cell : row) {
                if (cell == -1) {
                    this.bombs++;
                }
            }
        }
    }

    public MinesweeperBoard(String cellStr) {
        int cellOpenings = 0, indexCellOpening = 0;
        while (indexCellOpening != -1) {
            indexCellOpening = cellStr.indexOf("[", indexCellOpening);
            if (indexCellOpening != -1) {
                cellOpenings++;
                indexCellOpening++;
            }
        }

        int newLines = 0, indexNewLine = 0;
        while (indexNewLine != -1) {
            indexNewLine = cellStr.indexOf('\n', indexNewLine);
            if (indexNewLine != -1) {
                newLines++;
                indexNewLine++;
            }
        }

        cells = new int[cellOpenings / newLines][newLines];
        this.bombs = 0;
        int x = 0, y = 0;
        boolean bombFound = false;
        for (char ch : cellStr.toCharArray()) {
            switch (ch) {
                case '[':
                case ']':
                case '\t':
                    break;
                case '-':
                    addBomb();
                    cells[x][y] = -1;
                    bombFound = true;
                    break;
                case '\n':
                    x++;
                    y = 0;
                    break;
                case '1':
                    if (bombFound) {
                        bombFound = false;
                        y++;
                        break;
                    }
                default:
                    cells[x][y] = Integer.valueOf(Character.toString(ch));
                    y++;
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int[] row : cells) {
            for (int cell : row) {
                str += "[" + cell + "]\t";
            }
            str += "\n";
        }
        return str;
    }

    public void assignNumbers() {
        int x, y = 0;
        for (int[] row : this.cells) {
            x = 0;
            for (int cell : row) {
                if (cell != -1 && cell != 0) {
                    this.cells[y][x] = 0;
                }
                x++;
            }
            y++;
        }
        MinesweeperBoardUtils.assignNumbers(this.cells);
    }
}
