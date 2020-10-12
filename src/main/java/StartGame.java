
/*
        Coordinates (x,y)
        (1, 3) (2, 3) (3, 3)
        (1, 2) (2, 2) (3, 2)
        (1, 1) (2, 1) (3, 1)
*/

public class StartGame {
    public static void main(String[] args) {
        PlayField playField = new PlayField();
        playField.startNewGame();
        while (playField.checkGame()) {
            playField.makeMove();
        }
    }
}
