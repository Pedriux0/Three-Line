package game;
import javafx.scene.canvas.GraphicsContext;
//@author Juan NARANJO
public abstract class Piece {
    protected int row;
    protected int col;

    public Piece(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract void draw(GraphicsContext gc);

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }
}
