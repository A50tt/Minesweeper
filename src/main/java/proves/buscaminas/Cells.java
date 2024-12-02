
package proves.buscaminas;

public class Cells {
    
    int[][] cells;
    int bombs;
    
    public int[][] getCells() {
        return cells;
    }
    
    public Cells(int x, int y, int _bombs) {
        this.bombs = _bombs;
        this.cells = CellsUtils.assignBombs(x, y, bombs);
        this.cells = CellsUtils.assignNumbers(this.cells);
    }
    
    public Cells(int[][] _cells) {
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
    
    @Override
    public String toString() {
        String str = "";
        for (int[] row : cells) {
            for (int cell: row) {
                str += "[" + cell + "]\t";
            }
            str += "\n";
        }
        return str;
    }
}
