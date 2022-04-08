package com.github.jakobwilms.portalgame;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {

    private final @NotNull List<Coordinatable> tiles;
    private final @NotNull Coordinatable player;
    private final @NotNull List<Coordinatable> robots;
    private final @NotNull String name;

    public Dungeon(@NotNull Dungeon dungeon) {
        this.tiles = new ArrayList<>();
        for (Coordinatable tile : dungeon.getTiles())
            this.tiles.add(tile.mutable());
        this.player = dungeon.getPlayer().mutable();
        this.robots = new ArrayList<>();
        for (Coordinatable tile : dungeon.getRobots())
            this.robots.add(tile.mutable());
        this.name = dungeon.getName();
    }

    public Dungeon(@NotNull String name, @NotNull Coordinatable player, @NotNull List<Coordinatable> tiles, @NotNull List<Coordinatable> robots) {
        this.name = name;
        this.player = player;
        this.tiles = tiles;
        this.robots = robots;
    }

    public @NotNull ImmutableDungeon immutable() {
        return ImmutableDungeon.createImmutableDungeon(this);
    }

    public @NotNull Dungeon mutable() {
        return new Dungeon(this);
    }

    public @NotNull List<Coordinatable> getTiles() {
        return tiles;
    }

    public @NotNull Coordinatable getPlayer() {
        return player;
    }

    public @NotNull List<Coordinatable> getRobots() {
        return robots;
    }

    public @NotNull String getName() {
        return name;
    }
}
