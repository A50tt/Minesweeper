package proves.buscaminas;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellsTest {

    // 3 x 3 x 0 bombs
    private static int[][] array3x3x0 = new int[3][3];
    private static String string3x3x0;
    
    // 9 x 9 x 0 bombs
    private static int[][] array9x9x0 = new int[9][9];
    private static String string9x9x0;

    public CellsTest() {
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

        string9x9x0 = "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n"
                + "[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t[0]\t\n";
    }
    
    public static void setUpArrays() {
        for (int[] row : array3x3x0) {
            for (int cell : row) {
                cell = 0;
            }
        }
     
        for (int[] row : array9x9x0) {
            for (int cell : row) {
                cell = 0;
            }
        }
    }

    @Test
    public void string3x3x0() {
        assertEquals(string3x3x0, new Cells(3, 3, 0).toString());
    }

    @Test
    public void object3x3x0() {
        assertArrayEquals(array3x3x0, new Cells(3, 3, 0).getCells());
    }

    @Test
    public void string9x9x0() {
        assertEquals(string9x9x0, new Cells(9, 9, 0).toString());
    }

    @Test
    public void object9x9x0() {
        assertArrayEquals(array9x9x0, new Cells(9, 9, 0).getCells());
    }
}
