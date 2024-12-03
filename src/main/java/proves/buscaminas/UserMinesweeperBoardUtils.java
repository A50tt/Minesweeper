package proves.buscaminas;

public class UserMinesweeperBoardUtils {
    
    public static int[] coordinateConverter(UserMinesweeperBoard board, String coordinates) {
        int[] xyArr = new int[2];
        char[] chArray = coordinates.toCharArray();
        boolean letterPhase = true;
        for (char ch : chArray) {
            if (Character.isLetter(ch)) {
                if (letterPhase) {
                    xyArr[0] = Character.getNumericValue(ch) - 10;
                } else {
                    return null;
                }
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
    }
    

}
