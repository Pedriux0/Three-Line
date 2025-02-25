package game;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int numRows;
    private int numCols;
    private Piece[][] board;
    private List<Piece> pieces;
    private boolean greenTurn = true; //  turn

    public Grid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;//
        this.board = new Piece[numRows][numCols];
        this.pieces = new ArrayList<>();
    }

    public void placePiece(int row, int col) { //
        if (row < 0 || row >= numRows || col < 0 || col >= numCols || board[row][col] != null) {
            showAlert("You can't do that", "The cell already has a piece");
            return; // Invalid move
        }
        Piece piece;
        if (greenTurn) {
            piece = new GreenPiece(row, col);
        } else {
            piece = new BrownPiece(row, col);
        }
        greenTurn = !greenTurn; // Toggle turn
        board[row][col] = piece;
        pieces.add(piece);
    }
    public void showAlert(String title, String message) { // Alert Method
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public boolean checkWin() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] != null && checkLine(i, j, 1, 0) || // Horizontal
                        checkLine(i, j, 0, 1) || // Vertical
                        checkLine(i, j, 1, 1) || // Diagonal
                        checkLine(i, j, -1, 1)) { // Reverse Diagonal
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLine(int startRow, int startCol, int rowInc, int colInc) {
        Piece startPiece = board[startRow][startCol];
        if (startPiece == null) return false;
        for (int i = 1; i < 3; i++) {
            int row = startRow + i * rowInc;
            int col = startCol + i * colInc;
            if (row < 0 || row >= numRows || col < 0 || col >= numCols || board[row][col] == null ||
                    !board[row][col].getClass().equals(startPiece.getClass())) {
                return false;
            }
        }
        return true;
    }

    public void draw(GraphicsContext gc) {
        // Draw grid lines
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        for (int i = 1; i < numRows; i++) {
            gc.strokeLine(0, i * 100, numCols * 100, i * 100);
        }
        for (int i = 1; i < numCols; i++) {
            gc.strokeLine(i * 100, 0, i * 100, numRows * 100);
        }

        for (Piece piece : pieces) {
            piece.draw(gc);
        }
    }
}
