package com.github.jakobwilms.portalgame;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class GameScreen {

    private final @NotNull Game game;

    public GameScreen(@NotNull Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(Game.SCREEN_SIZE.getWidth(), Game.SCREEN_SIZE.getHeight());
        GraphicsContext context = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);
        root.setStyle("-fx-background-image: rgb(0, 0, 0)");

        Scene scene = new Scene(root, Game.SCREEN_SIZE.getWidth(), Game.SCREEN_SIZE.getHeight());

        stage.setScene(scene);

        GameScreenHandler screenHandler = new GameScreenHandler(stage, root, canvas, context, scene);
        this.game = new Game(screenHandler);
    }

    public @NotNull Game getGame() {
        return game;
    }
}
