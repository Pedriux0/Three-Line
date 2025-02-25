package game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    private static final int GRID_SIZE = 3; // Grid size (3x3)

    @Override
    public void start(Stage primaryStage) {
        Grid grid = new Grid(GRID_SIZE, GRID_SIZE);
        Canvas canvas = new Canvas(GRID_SIZE * 100, GRID_SIZE * 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        grid.draw(gc);

        Label instructionsLabel = new Label("PLease put a piece in a cell");
        instructionsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox root = new VBox();
        root.getChildren().addAll(canvas, instructionsLabel);

        canvas.setOnMouseClicked(event -> {
            int col = (int) (event.getX() / 100);
            int row = (int) (event.getY() / 100);
            grid.placePiece(row, col);
            grid.draw(gc);
            if (grid.checkWin()) {
                System.out.println("Awesome you won ( I don't lnow who");
                showAlert("Game Over", "You win!");
                System.out.println("Alert");
                primaryStage.close();
            }
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Three in a Line");
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}