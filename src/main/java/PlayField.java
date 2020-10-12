import InputOutputData.InputDataGame;
import InputOutputData.OutputDataGame;
import players.*;

import java.util.Arrays;

class PlayField {
    private final char[][] field;
    private Player playerOne;
    private Player playerTwo;
    private int count;

    PlayField() {
        field = new char[3][3];
        for (char[] chars : field) {
            Arrays.fill(chars, ' ');
        }
    }

    void startNewGame() {
        InputDataGame inputDataGame = new InputDataGame();
        String [] selectGameMode = inputDataGame.selectGameMode();
        playerOne = playerInitialization(selectGameMode[1], 'X');
        playerTwo = playerInitialization(selectGameMode[2], 'O');
        count = 0;
        System.out.println("\nThe game field is 3x3 in size.");
        System.out.println("""
                         Coordinates (x,y)
                        (1, 3) (2, 3) (3, 3)
                        (1, 2) (2, 2) (3, 2)
                        (1, 1) (2, 1) (3, 1)""");
        System.out.println("Coordinates are specified in the form: X Y");
        outputField();
    }

    private Player playerInitialization(String mode, char mark) {
        return switch (mode) {
            case "user" -> new User(mark);
            case "easy" -> new EasyAI(mark);
            case "medium" -> new MediumAI(mark);
            default -> new HardAI(mark);
        };
    }

    void outputField() {
        OutputDataGame outputData = new OutputDataGame();
        outputData.buildField(field);
    }

    boolean checkGame() {
        OutputDataGame outputData = new OutputDataGame();
        return outputData.outputCheckGame(field);
    }

    void makeMove() {
        if (count % 2 == 0) {
            playerOne.move(field);
            System.out.println("\nPlayer One made a move.");
        } else {
            playerTwo.move(field);
            System.out.println("\nPlayer Two made a move.");
        }
        outputField();
        count++;
    }
}
