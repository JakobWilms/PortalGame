package com.github.jakobwilms.portalgame;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Texture {

    private static final @NotNull HashMap<String, Texture> TEXTURES = new HashMap<>();
    private static final @NotNull List<String> TEXTURE_NAMES;
    private static final @NotNull List<Texture> TEXTURE_OBJECTS;

    public static @NotNull Texture get(@NotNull String name) {
        return TEXTURES.get(name);
    }

    static {
        loadAll();
        TEXTURE_NAMES = new ArrayList<>(TEXTURES.keySet());
        TEXTURE_OBJECTS = new ArrayList<>(TEXTURES.values());
    }

    private final @NotNull String name;
    private final @NotNull BufferedImage image;
    private final int argb;

    private final @NotNull Image fxImage;

    private final @NotNull ImagePattern imagePattern;

    private Texture(@NotNull String name, @NotNull BufferedImage image, int argb) {
        this.name = name;
        this.argb = argb;
        this.image = image;
        this.fxImage = SwingFXUtils.toFXImage(image, null);
        this.imagePattern = new ImagePattern(fxImage);
    }

    private static void loadAll() {
        InputStream inputStream = ImmutableDungeon.class.getResourceAsStream("/com/github/jakobwilms/portalgame/textures.txt");
        assert inputStream != null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            for (String line; (line = reader.readLine()) != null; ) {
                TEXTURES.put(line, load(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static @NotNull Texture load(@NotNull String line) {
        String[] split = line.split(":");
        try (InputStream inputStream = Texture.class.getResourceAsStream("/com/github/jakobwilms/portalgame/textures/" + split[0] + ".png")) {
            assert inputStream != null;
            BufferedImage temp = ImageIO.read(inputStream);
            BufferedImage resized = new BufferedImage(Game.TILE_SIZE, Game.TILE_SIZE, BufferedImage.TRANSLUCENT);
            Graphics2D graphics2D = resized.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(temp, 0, 0, Game.TILE_SIZE, Game.TILE_SIZE, null);
            graphics2D.dispose();

            return new Texture(split[0], resized, Integer.decode("0x" + split[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Could not find file " + split[0] + ".png");
    }

    public static @NotNull String fromArgb(int argb) {
        for (int i = 0; i < TEXTURES.size(); i++) {
            if (TEXTURE_OBJECTS.get(i).getArgb() == argb) return TEXTURE_NAMES.get(i);
        }
        return "empty";
    }

    public @NotNull BufferedImage getImage() {
        return image;
    }

    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull String toString() {
        return getName();
    }

    public @NotNull ImagePattern getImagePattern() {
        return imagePattern;
    }

    public @NotNull Image getFxImage() {
        return fxImage;
    }

    public int getArgb() {
        return argb;
    }
}
