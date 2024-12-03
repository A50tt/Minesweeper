
package proves.buscaminas;

public class GameSystem {

    public static void main(String[] args) {
        /*
        El Principiante suele estar en un tablero de 8x8 o 9x9 que contiene 10 minas,
        el Intermedio suele estar en un tablero de 16x16 con 40 minas y
        el Experto suele estar en un tablero de 30x16 con 99 minas;
        */
        MinesweeperBoard cells = new MinesweeperBoard(25, 25, 40);
        System.out.println(cells);
    }
}
