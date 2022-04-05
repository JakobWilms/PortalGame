package com.github.jakobwilms.portalgame;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

public enum Textures {
    PLAYER("player"),
    ROBOT("robot"),
    WALL("wall"),
    COAL("coal")
    ;
    public static final int TEXTURES = (int) Arrays.stream(Textures.values()).count();

    private final @NotNull String name;
    private final @NotNull BufferedImage image;

    Textures(@NotNull String name) {
        this.name = name;
        BufferedImage image = null;
        try {
            InputStream inputStream = Textures.class.getResourceAsStream("/com/github/jakobwilms/portalgame/textures/" + name + ".png");
            BufferedImage temp = ImageIO.read(Objects.requireNonNull(inputStream));
            BufferedImage resized = new BufferedImage(Game.TILE_SIZE, Game.TILE_SIZE, BufferedImage.TRANSLUCENT);
            Graphics2D graphics2D = resized.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(temp, 0, 0, Game.TILE_SIZE, Game.TILE_SIZE, null);
            graphics2D.dispose();
            image = resized;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        this.image = image;
    }

    public @NotNull BufferedImage getImage() {
        return image;
    }

    public @NotNull String getName() {
        return name;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return getName();
    }
}
