package com.github.jakobwilms.portalgame;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public abstract class Coordinatable {

    protected final BufferedImage image;

    protected int x;
    protected int y;

    public Coordinatable(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public Coordinatable(int x, int y, @NotNull Textures textures) {
        this(x, y, textures.getImage());
    }

    public void setPos(int x, int y) {
        setX(x);
        setY(y);
    }

    public boolean posEquals(Coordinatable other) {
        if (other == null)
            return false;

        return other.getX() == this.getX() && other.getY() == this.getY();
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
