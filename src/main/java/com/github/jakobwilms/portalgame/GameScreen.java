package com.github.jakobwilms.portalgame;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class GameScreen {

    public GameScreen(@NotNull Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas();
        GraphicsContext context = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        root.setStyle("-fx-background-image: rgb(0, 0, 0)");

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);

    }
}
