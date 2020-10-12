package players;

import InputOutputData.OutputDataGame;

public class MediumAI extends EasyAI {
    final char enemyMark;

    public MediumAI(char mark) {
        super(mark);
        if (mark == 'X') {
            enemyMark = 'O';
        } else {
            enemyMark = 'X';
        }
    }

    @Override
    public void move(char[][] field) {
        if (checkEnemyUser(field)) {
            super.move(field);
        }
    }

    boolean checkEnemyUser(char[][] field) {
        OutputDataGame outputData = new OutputDataGame();
        int[] checkWin = outputData.checkWin(field);
        for (int i = 0; i < checkWin.length; i++) {
            if (checkWinOrBlock(checkWin[i])) {
                moveMediumAi(field, coordinatesMediumAi(i));
                return false;
            }
        }
        return true;
    }

    private boolean checkWinOrBlock(int gameSituation) {
        return gameSituation == (enemyMark + enemyMark + ' ') || gameSituation == (mark + mark + ' ');
    }

    private void moveMediumAi(char[][] field, int[] coordinates) {
        for (int i = 0; i < coordinates.length; i++) {
            int x = coordinates[i];
            int y = coordinates[++i];
            if (field[x][y] == ' ') {
                field[x][y] = mark;
            }
        }
    }

    private int[] coordinatesMediumAi(int i) {
        return switch (i) {
            case 0 -> new int[]{0, 0, 0, 1, 0, 2};
            case 1 -> new int[]{1, 0, 1, 1, 1, 2};
            case 2 -> new int[]{2, 0, 2, 1, 2, 2};
            case 3 -> new int[]{0, 0, 1, 0, 2, 0};
            case 4 -> new int[]{0, 1, 1, 1, 2, 1};
            case 5 -> new int[]{0, 2, 1, 2, 2, 2};
            case 6 -> new int[]{0, 0, 1, 1, 2, 2};
            default -> new int[]{0, 2, 1, 1, 2, 0};
        };
    }

}
