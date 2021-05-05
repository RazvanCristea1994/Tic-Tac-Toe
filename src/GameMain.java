import java.util.Scanner;

public class GameMain {

    private Board board;
    private GameState currentState;
    private Seed currentPlayer;

    private static Scanner scan = new Scanner(System.in);

    public GameMain() {
        this.board = new Board();
        initGame();
        play();
    }

    public static void main(String[] args) {
        new GameMain();
    }

    private void initGame() {
        board.init();
        currentPlayer = Seed.CROSS;
        currentState = GameState.PLAYING;
    }

    private void play() {

        board.paint();
        do {
            playerMove(currentPlayer);
            board.paint();
            updateGame(currentPlayer);

            if(currentState == GameState.CROSS_WON) {
                System.out.println("'X' won!" );
            } else if (currentState == GameState.NOUGHT_WON) {
                System.out.println("'O' won!");
            } else if (currentState == GameState.DRAW) {
                System.out.println("It's a Draw!");
            }

            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING);

        System.out.println("Would you like to play again? [Y] [N]");
        String inputPlayAgain = scan.next().toLowerCase();
        if (inputPlayAgain.startsWith("y")){
            new GameMain();
        } else {
            System.out.println("Bye!");
        }
    }

    private void playerMove(Seed theSeed) {

        boolean validInput = false;
        do {
            if (theSeed == Seed.CROSS) {
                System.out.println("Player 'X', enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.println("Player 'O', enter your move (row[1-3] column[1-3]): ");
            }
            int row = scan.nextInt() - 1;
            int col = scan.nextInt() - 1;
            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                    && board.getCells()[row][col].getContent() == Seed.EMPTY) {
                board.getCells()[row][col].setContent(theSeed);
                board.setCurrentRow(row);
                board.setCurrentCol(col);
                validInput = true;
                board.setEmptyCellsNumber(board.getEmptyCellsNumber() - 1);
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
            }
        } while (!validInput);
    }

    private void updateGame(Seed theSeed) {

        if(board.hasWon(theSeed)) {
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {
            currentState = GameState.DRAW;
        }
    }
}
