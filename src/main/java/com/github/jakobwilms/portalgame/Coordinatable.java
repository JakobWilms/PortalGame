package com.github.jakobwilms.portalgame;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Coordinatable {

    protected final Texture texture;

    private int x;
    private int y;
    private int transitionX;
    private int transitionY;

    public Coordinatable(int x, int y, @NotNull String texture) {
        this.texture = Texture.get(texture);
        this.x = x;
        this.y = y;
        this.transitionX = 0;
        this.transitionY = 0;
    }

    @Contract(pure = true)
    public Coordinatable(@NotNull Coordinatable copy) {
        this.x = copy.x;
        this.y = copy.y;
        this.transitionX = copy.transitionX;
        this.transitionY = copy.transitionY;
        this.texture = copy.texture;
    }

    public ImmutableCoordinatable immutable() {
        return new ImmutableCoordinatable(this);
    }

    public Coordinatable mutable() {
        return new Coordinatable(this);
    }

    public void setPos(int x, int y) {
        setPos(x, y, 0, 0);
    }

    public void setPos(int x, int y, int transitionX, int transitionY) {
        setX(x);
        setY(y);
        setTransitionX(transitionX);
        setTransitionY(transitionY);
    }

    public boolean posEquals(Coordinatable other) {
        if (other == null)
            return false;

        return other.getX() == this.getX() && other.getY() == this.getY();
    }

    public Texture getTexture() {
        return texture;
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

    public int getTransitionX() {
        return transitionX;
    }

    public void setTransitionX(int transitionX) {
        this.transitionX = transitionX;
    }

    public void addTransitionX() {
        if (getTransitionX() + 1 >= Game.TRANSITION_MOVES) setTransitionX(0);
        else setTransitionX(getTransitionX() + 1);
    }

    public int getTransitionY() {
        return transitionY;
    }

    public void setTransitionY(int transitionY) {
        this.transitionY = transitionY;
    }

    public void addTransitionY() {
        if (getTransitionY() + 1 >= Game.TRANSITION_MOVES) setTransitionY(0);
        else setTransitionY(getTransitionY() + 1);
    }
}
