package com.github.jakobwilms.portalgame;

import javafx.animation.AnimationTimer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Game {

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int TILES_X = 64;
    public static final int TILES_Y = 36;
    public static final int TILE_SIZE = Math.min(SCREEN_SIZE.width / TILES_X, SCREEN_SIZE.height / TILES_Y);

    public static final int TRANSITION_MOVES = 8;
    public static final int TRANSITION_PIXELS = TILE_SIZE / TRANSITION_MOVES;

    private final @NotNull AnimationTimer animationTimer;
    private final long startMillis;
    private int breakTime;

    private final @NotNull GameScreenHandler screenHandler;

    public Game(@NotNull GameScreenHandler screenHandler) {
        this.startMillis = System.currentTimeMillis();
        this.breakTime = 0;
        this.screenHandler = screenHandler;

        getScreenHandler().loadDungeon("introduction");

        this.animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (breakTime > 0) breakTime--;

            }
        };
    }

    public long getStartMillis() {
        return startMillis;
    }

    public @NotNull AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public @NotNull GameScreenHandler getScreenHandler() {
        return screenHandler;
    }
}
