package proves.buscaminas;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MinesweeperBoardTest {

    // 3 x 3
    private static String string3x3x0;
    private static int[][] array3x3x0 = new int[3][3];
    private static String string3x3x5;
    private static int[][] array3x3x5 = new int[3][3];
    
    // 9 x 9
    private static String string9x9x0;
    private static int[][] array9x9x0 = new int[9][9];
    private static String string9x9x15;
    private static int[][] array9x9x15 = new int[9][9];
    
    // 3 x 6
    private static String string6x3x3;
    private static int[][] array6x3x6 = new int[3][6];

    public MinesweeperBoardTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        setUpStrings();
        setUpArrays();
    }
    
    public static void setUpStrings() {
        string3x3x0 = "[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t\n";
        
        string3x3x5 = "[1]\t[1]\t[1]\t\n"
                + "[1]\t[-1]\t[1]\t\n"
                + "[1]\t[1]\t[1]\t\n";

        string6x3x3 = "[1]\t[1]\t[1]\t[0]\t[1]\t[-1]\t\n"
                + "[1]\t[-1]\t[1]\t[1]\t[2]\t[2]\t\n"
                + "[1]\t[1]\t[1]\t[1]\t[-1]\t[1]\t\n";

        string9x9x0 = "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n";

        string9x9x15 = "[1]\t[1]\t[2]\t[1]\t[1]\t[1]\t[1]\t[1]\t[0]\t\n"
                    + "[1]\t[-1]\t[2]\t[-1]\t[1]\t[1]\t[-1]\t[1]\t[0]\t\n"
                    + "[1]\t[2]\t[3]\t[3]\t[2]\t[2]\t[2]\t[2]\t[1]\t\n"
                    + "[1]\t[2]\t[-1]\t[2]\t[-1]\t[1]\t[1]\t[-1]\t[1]\t\n"
                    + "[1]\t[-1]\t[3]\t[3]\t[2]\t[2]\t[3]\t[2]\t[2]\t\n"
                    + "[1]\t[2]\t[-1]\t[1]\t[1]\t[-1]\t[2]\t[-1]\t[1]\t\n"
                    + "[0]\t[1]\t[2]\t[2]\t[3]\t[2]\t[4]\t[2]\t[2]\t\n"
                    + "[1]\t[1]\t[2]\t[-1]\t[2]\t[-1]\t[2]\t[-1]\t[2]\t\n"
                    + "[1]\t[-1]\t[2]\t[1]\t[2]\t[1]\t[2]\t[2]\t[-1]\t\n";
    }
    
    public static void setUpArrays() {
        // 3 x 3 x 0 bombs
        for (int[] row : array3x3x0) {
            for (int cell : row) {
                cell = 0;
            }
        }
        
        // 3 x 3 x 5 bombs
        array3x3x5[1][1] = -1;
        MinesweeperBoard cell3x3x5 = new MinesweeperBoard(array3x3x5);
        cell3x3x5.assignNumbers();
        array3x3x5 = cell3x3x5.getCells();
        
        // 9 x 9 x 0 bombs
        for (int[] row : array9x9x0) {
            for (int cell : row) {
                cell = 0;
            }
        }
        
        // 9 x 9 x 15 bombs
        int[][] array9x9x15Bombs = { {1,1}, {1,3}, {1,6}, {3,2}, {3,7}, {4,1}, {5,2}, {5,5}, {5,7}, {7,3}, {7,5}, {7,7}, {8,1}, {8,8} };
        for (int[] bombPos : array9x9x15Bombs) {
            array9x9x15[bombPos[0]][bombPos[1]] = -1;
        }
        MinesweeperBoard cell9x9x15 = new MinesweeperBoard(array9x9x15);
        cell9x9x15.assignNumbers();
        array9x9x15 = cell9x9x15.getCells();
        
        // 6 x 3 x 3 bombs
        array6x3x6[1][1] = -1;
        array6x3x6[0][5] = -1;
        array6x3x6[2][4] = -1;
        MinesweeperBoard cell3x6x3 = new MinesweeperBoard(array6x3x6);
        cell3x6x3.assignNumbers();
        array6x3x6 = cell3x6x3.getCells();
        
        // 9 x 9 x 0 bombs
        for (int[] row : array9x9x0) {
            for (int cell : row) {
                cell = 0;
            }
        }
    }
    
    @Test
    @Order(0)
    public void assertNumberAssignments() {
        assertEquals(string3x3x0, new MinesweeperBoard(3, 3, 0).toString());
    }

    @Test
    @Order(1)
    public void assertEqualsString3x3x0() {
        assertEquals(string3x3x0, new MinesweeperBoard(3, 3, 0).toString());
    }

    @Test
    @Order(2)
    public void assertEqualsArray3x3x0() {
        assertArrayEquals(array3x3x0, new MinesweeperBoard(3, 3, 0).getCells());
    }

    @Test
    @Order(3)
    public void assertEqualsString3x3x5() {
        assertEquals(string3x3x5, new MinesweeperBoard(string3x3x5).toString());
    }
    
    @Test
    @Order(4)
    public void assertEqualsArray3x3x5() {
        assertEquals(array3x3x5, new MinesweeperBoard(array3x3x5).getCells());
    }
    
    @Test
    @Order(5)
    public void assertEqualsString9x9x0() {
        assertEquals(string9x9x0, new MinesweeperBoard(9, 9, 0).toString());
    }

    @Test
    @Order(6)
    public void assertEqualsArray9x9x0() {
        assertArrayEquals(array9x9x0, new MinesweeperBoard(9, 9, 0).getCells());
    }
    
    @Test
    @Order(7)
    public void assertEqualsString9x9x15() {
        MinesweeperBoard cell = new MinesweeperBoard(string9x9x15);
        cell.assignNumbers();
        assertEquals(string9x9x15, cell.toString());
    }
    
    @Test
    @Order(8)
    public void assertEqualsArray9x9x15() {
        MinesweeperBoard cell = new MinesweeperBoard(array9x9x15);
        cell.assignNumbers();
        assertEquals(array9x9x15, cell.getCells());
    }
    
    @Test
    @Order(9)
    public void assertEqualsString6x3x3() {
        MinesweeperBoard cell = new MinesweeperBoard(string6x3x3);
        cell.assignNumbers();
        assertEquals(string6x3x3, cell.toString());
    }

    @Test
    @Order(10)
    public void assertEqualsArray6x3x3() {
        assertArrayEquals(array6x3x6, new MinesweeperBoard(array6x3x6).getCells());
    }
}
