package players;

import java.util.Random;

public class EasyAI implements Player{
    char mark;

    public EasyAI(char mark) {
        this.mark = mark;
    }

    @Override
    public void move(char[][] field) {
        Random randomMove = new Random();
        int x;
        int y;
        do {
            x = randomMove.nextInt(3 - 1 + 1) + 1;
            y = randomMove.nextInt(3 - 1 + 1) + 1;
        } while (!(field[3 - y][x - 1] == ' '));
        field[3 - y][x - 1] = mark;
    }
}
