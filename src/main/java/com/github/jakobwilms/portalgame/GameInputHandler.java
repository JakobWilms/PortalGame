package com.github.jakobwilms.portalgame;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class GameInputHandler implements EventHandler<KeyEvent> {

    private final Set<KeyCode> activeKeys = new HashSet<>();

    public GameInputHandler() {}

    @Override
    public void handle(@NotNull KeyEvent keyEvent) {
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED))
            activeKeys.add(keyEvent.getCode());
        else if (keyEvent.getEventType().equals(KeyEvent.KEY_RELEASED))
            activeKeys.remove(keyEvent.getCode());
    }

    public Set<KeyCode> getActiveKeys() {
        return activeKeys;
    }

    public boolean contains(KeyCode keyCode) {
        return getActiveKeys().contains(keyCode);
    }
}
