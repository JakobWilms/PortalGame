package com.github.jakobwilms.portalgame;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
    }

    @Override
    public void start(@NotNull Stage stage) {
        Utils.print(this.getClass(), Thread.currentThread().getStackTrace(), "Starting");
        stage.setTitle("Portal Game");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.setFullScreenExitHint("");
        stage.show();

        GameScreen gameScreen = new GameScreen(stage);
    }

    @Override
    public void stop() {
    }
}
