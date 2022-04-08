package com.github.jakobwilms.portalgame;

import org.jetbrains.annotations.NotNull;

public class ImmutableCoordinatable extends Coordinatable {

    public ImmutableCoordinatable(int x, int y, @NotNull String texture) {
        super(x, y, texture);
    }

    public ImmutableCoordinatable(Coordinatable coordinatable) {
        super(coordinatable);
    }

    @Override
    public void setPos(int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPos(int x, int y, int transitionX, int transitionY) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setX(int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setY(int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTransitionX(int transitionX) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTransitionX() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTransitionY(int transitionY) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addTransitionY() {
        throw new UnsupportedOperationException();
    }
}
