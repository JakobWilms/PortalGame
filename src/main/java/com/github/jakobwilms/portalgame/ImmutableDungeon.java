package com.github.jakobwilms.portalgame;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ImmutableDungeon extends Dungeon {

    private static final @NotNull HashMap<String, ImmutableDungeon> DUNGEONS = new HashMap<>();

    public static ImmutableDungeon get(@NotNull String name) {
        return DUNGEONS.get(name);
    }

    static {
        loadAll();
    }

    public ImmutableDungeon(@NotNull String name, @NotNull Coordinatable player, @NotNull List<Coordinatable> tiles, @NotNull List<Coordinatable> robots) {
        super(name, player.immutable(), Collections.unmodifiableList(tiles), Collections.unmodifiableList(robots));
    }

    @Contract("_ -> new")
    public static @NotNull ImmutableDungeon createImmutableDungeon(@NotNull Dungeon dungeon) {
        return createImmutableDungeon(dungeon.getName(), dungeon.getPlayer(), dungeon.getTiles(), dungeon.getRobots());
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull ImmutableDungeon createImmutableDungeon(@NotNull String name, @NotNull Coordinatable player, @NotNull List<Coordinatable> tiles, @NotNull List<Coordinatable> robots) {
        List<Coordinatable> immutableTiles = new ArrayList<>();
        for (Coordinatable tile : tiles) immutableTiles.add(tile.immutable());
        List<Coordinatable> immutableRobots = new ArrayList<>();
        for (Coordinatable tile : robots) immutableRobots.add(tile.immutable());

        return new ImmutableDungeon(name, player, immutableTiles, immutableRobots);
    }

    private static void loadAll() {
        InputStream inputStream = ImmutableDungeon.class.getResourceAsStream("/com/github/jakobwilms/portalgame/dungeons.txt");
        assert inputStream != null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            for (String line; (line = reader.readLine()) != null; ) {
                DUNGEONS.put(line, load(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Contract("_ -> new")
    private static @NotNull ImmutableDungeon load(@NotNull String name) {
        List<Coordinatable> robots = new ArrayList<>();
        List<Coordinatable> tiles = new ArrayList<>();
        Coordinatable player = new Coordinatable(0, 0, "player");
        try (InputStream inputStream = ImmutableDungeon.class.getResourceAsStream("/com/github/jakobwilms/portalgame/maps/dungeon_" + name + ".png")) {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(inputStream));
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    String texture = Texture.fromArgb(image.getRGB(x, y));
                    if (texture.equals("player")) {
                        player = new Coordinatable(x, y, "player");
                        tiles.add(new Coordinatable(x, y, "empty"));
                    } else if (texture.equals("robot")) {
                        robots.add(new Coordinatable(x, y, "robot"));
                        tiles.add(new Coordinatable(x, y, "empty"));
                    } else {
                        tiles.add(new Coordinatable(x, y, texture));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createImmutableDungeon(name, player, tiles, robots);
    }

}
