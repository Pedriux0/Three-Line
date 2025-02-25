package game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BrownPiece extends Piece {
    public BrownPiece(int row, int col) {
        super(row, col);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BROWN);
        gc.fillOval(col * 100 + 25, row * 100 + 25, 50, 50);
    }
}
