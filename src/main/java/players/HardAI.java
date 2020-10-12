package players;

import InputOutputData.OutputDataGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HardAI extends MediumAI {
    OutputDataGame outputDataGame;
    Map<String, Integer> allScore;

    public HardAI(char mark) {
        super(mark);
        outputDataGame = new OutputDataGame();
        allScore = new HashMap<>();
    }

    public void move(char[][] field) {
        if (checkEnemyUser(field)) {
            char[][] fieldCopy = copyField(field);
            for (int x = 0; x < fieldCopy.length; x++) {
                System.arraycopy(field[x], 0, fieldCopy[x], 0, fieldCopy[x].length);
            }
            for (int x = 0; x < fieldCopy.length; x++) {
                for (int y = 0; y < fieldCopy[x].length; y++) {
                    if (fieldCopy[x][y] == ' ') {
                        fieldCopy[x][y] = mark;
                        char[][] fieldCopyForMinimax = copyField(fieldCopy);
                        int score = minimax(fieldCopyForMinimax, false);
                        allScore.put(x + " " + y, score);
                        fieldCopy[x][y] = ' ';
                    }
                }
            }
            int max = -100;
            int x = -100;
            int y = -100;
            for (Map.Entry<String, Integer> entry : allScore.entrySet()) {
                if (entry.getValue() > max) {
                    String coordinates = entry.getKey();
                    max = entry.getValue();
                    x = Integer.parseInt(coordinates.split("\\s")[0]);
                    y = Integer.parseInt(coordinates.split("\\s")[1]);
                }
            }
            field[x][y] = mark;
            allScore.clear();
        }
    }

    private char[][] copyField(char[][] fieldOriginal) {
        char[][] fieldCopy = new char[3][3];
        for (int x = 0; x < fieldCopy.length; x++) {
            System.arraycopy(fieldOriginal[x], 0, fieldCopy[x], 0, fieldCopy[x].length);
        }
        return fieldCopy;
    }

    private int minimax(char[][] fieldCopy, boolean isMaximize) {
        int[] checkWin = outputDataGame.checkWin(fieldCopy);
        boolean draw = true;
        for (char[] fields : fieldCopy) {
            for (char chars : fields) {
                if (chars == ' ') {
                    draw = false;
                    break;
                }
            }
        }
        if (Arrays.stream(checkWin).anyMatch(e -> e == enemyMark + enemyMark + enemyMark)) {
            return -10;
        } else if (Arrays.stream(checkWin).anyMatch(e -> e == mark + mark + mark)) {
            return 10;
        } else if (draw) {
            return 0;
        } else {
            int bestScore;
            if (isMaximize) {
                Player userMax = new MediumAI(mark);
                userMax.move(fieldCopy);
                bestScore = minimax(fieldCopy, false);
            } else {
                Player userMin = new MediumAI(enemyMark);
                userMin.move(fieldCopy);
                bestScore = minimax(fieldCopy, true);
            }
            return bestScore;
        }
    }
}
