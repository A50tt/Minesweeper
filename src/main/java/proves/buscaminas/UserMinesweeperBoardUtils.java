package proves.buscaminas;

public class UserMinesweeperBoardUtils {

    public static int[] coordinateConverter(UserMinesweeperBoard board, String coordinates) {
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

}
