package com.github.jakobwilms.portalgame;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameScreenHandler {

    private final @NotNull Stage stage;
    private final @NotNull Scene scene;
    private final @NotNull VBox root;

    private final @NotNull Canvas canvas;
    private final @NotNull GraphicsContext context;

    private Dungeon activeDungeon;

    public GameScreenHandler(@NotNull Stage stage, @NotNull VBox root, @NotNull Canvas canvas, @NotNull GraphicsContext context, @NotNull Scene scene) {
        this.stage = stage;
        this.root = root;
        this.canvas = canvas;
        this.context = context;
        this.scene = scene;
        this.activeDungeon = null;
    }

    public void loadDungeon(@NotNull String name) {
        setActiveDungeon(ImmutableDungeon.get(name).mutable());
        display(getActiveDungeon().getTiles());
        display(getActiveDungeon().getRobots());
        display(getActiveDungeon().getPlayer());
    }

    public void display(@NotNull List<Coordinatable> tiles) {
        for (Coordinatable tile : tiles) display(tile);
    }

    public void display(@NotNull Coordinatable tile) {
        getContext().setFill(tile.getTexture().getImagePattern());
        getContext().fillRect(tile.getX() * Game.TILE_SIZE, tile.getY() * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
    }

    public void clear(@NotNull Coordinatable tile) {
        getContext().clearRect(tile.getX() * Game.TILE_SIZE, tile.getY() * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
    }

    public @NotNull GraphicsContext getContext() {
        return context;
    }

    public @NotNull Canvas getCanvas() {
        return canvas;
    }

    public @NotNull VBox getRoot() {
        return root;
    }

    public @NotNull Scene getScene() {
        return scene;
    }

    public @NotNull Stage getStage() {
        return stage;
    }

    public Dungeon getActiveDungeon() {
        return activeDungeon;
    }

    public void setActiveDungeon(Dungeon activeDungeon) {
        this.activeDungeon = activeDungeon;
    }
}
