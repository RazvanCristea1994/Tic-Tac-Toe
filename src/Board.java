public class Board {

    public static final int ROWS = 3;
    public static final int COLS = 3;

    private Cell[][] cells;
    private int currentRow, currentCol;

    public Board() {

        this.cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void init() {

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col].clear();
            }
        }
    }

    public boolean isDraw() {

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (cells[row][col].getContent() == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWon(Seed theSeed) {

        return (cells[currentRow][0].getContent() == theSeed &&
                cells[currentRow][1].getContent() == theSeed &&
                cells[currentRow][2].getContent() == theSeed
                || cells[0][currentCol].getContent() == theSeed &&
                cells[1][currentCol].getContent() == theSeed &&
                cells[2][currentCol].getContent() == theSeed
                || currentCol == currentRow &&
                cells[0][0].getContent() == theSeed &&
                cells[1][1].getContent() == theSeed &&
                cells[2][2].getContent() == theSeed
                || currentCol + currentRow == 2 &&
                cells[0][2].getContent() == theSeed &&
                cells[1][1].getContent() == theSeed &&
                cells[2][0].getContent() == theSeed);
    }

    public void paint() {

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col].paint();
                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("------------");
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }
}
