package players;

import InputOutputData.InputDataGame;

public class User implements Player {
    char mark;

    public User(char mark) {
        this.mark = mark;
    }

    @Override
    public void move(char[][] field) {
        InputDataGame inputData = new InputDataGame();
        String coordinates = inputData.checkCoordinates(field);
        int x = 3 - Integer.parseInt(coordinates.charAt(2) + "");
        int y = Integer.parseInt(coordinates.charAt(0) + "") - 1;
        field[x][y] = mark;
    }
}