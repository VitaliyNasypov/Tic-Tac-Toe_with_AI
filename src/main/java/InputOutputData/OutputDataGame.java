package InputOutputData;

public class OutputDataGame {

    public void buildField(char[][] field) {
        System.out.println("---------");
        for (char[] chars : field) {
            for (int x = 0; x < chars.length; x++) {
                System.out.println("| " + chars[x] + " " + chars[++x] + " " + chars[++x] + " |");
            }
        }
        System.out.println("---------");
    }

    public boolean outputCheckGame(char[][] field) {
        int[] checkWin = checkWin(field);
        for (int check : checkWin) {
            if (check == ('X' + 'X' + 'X')) {
                System.out.println("X wins");
                return false;
            } else if (check == ('O' + 'O' + 'O')) {
                System.out.println("O wins");
                return false;
            }
        }

        for (char[] fields : field) {
            for (char chars : fields) {
                if (chars == ' ') {
                    return true;
                }
            }
        }
        System.out.println("Draw");
        return false;
    }

    public int[] checkWin(char[][] field) {
        int[] checkWin = new int[8];
        checkWin[0] = field[0][0] + field[0][1] + field[0][2];
        checkWin[1] = field[1][0] + field[1][1] + field[1][2];
        checkWin[2] = field[2][0] + field[2][1] + field[2][2];

        checkWin[3] = field[0][0] + field[1][0] + field[2][0];
        checkWin[4] = field[0][1] + field[1][1] + field[2][1];
        checkWin[5] = field[0][2] + field[1][2] + field[2][2];

        checkWin[6] = field[0][0] + field[1][1] + field[2][2];
        checkWin[7] = field[0][2] + field[1][1] + field[2][0];
        return checkWin;
    }
}