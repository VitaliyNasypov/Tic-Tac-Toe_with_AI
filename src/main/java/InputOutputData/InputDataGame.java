package InputOutputData;

import java.util.Scanner;

public class InputDataGame {
    final static Scanner scanner = new Scanner(System.in);

    public String[] selectGameMode() {
        String selectGameMode;
        System.out.println("""
                The game is played by player one (X) and player two (O).
                Types of players: user (you), easy, medium, hard.
                To start the game, write: start TypePlayer TypePlayer
                At any time you can end the game by writing: exit
                """);
        while (true) {
            System.out.print("Enter game mode: ");
            selectGameMode = scanner.nextLine().toLowerCase();
            gameExit(selectGameMode);
            if (checkModeNewGame(selectGameMode)) {
                return selectGameMode.split("\\s");
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    private void gameExit(String command) {
        if (command.contains("exit")) {
            System.exit(0);
        }
    }

    private boolean checkModeNewGame(String modeGame) {
        boolean checkLength = modeGame.split("\\s").length == 3;
        boolean correctMode = modeGame.matches("start\\s(user|easy|medium|hard)\\s(user|easy|medium|hard)");
        return checkLength && correctMode;
    }

    public String checkCoordinates(char[][] field) {
        while (true) {
            System.out.print("Enter coordinates: ");
            String coordinates = scanner.nextLine().toLowerCase();
            gameExit(coordinates);
            if (coordinates.matches("[1-3]\\s[1-3]")) {
                String x = coordinates.charAt(0) + "";
                String y = coordinates.charAt(2) + "";
                if (!(field[3 - Integer.parseInt(y)][Integer.parseInt(x) - 1] == ' ')) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    return coordinates;
                }
            } else if (coordinates.matches("[^1-3]\\s[^1-3]")) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }
}