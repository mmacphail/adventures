package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class PlayerAlreadyLeftAdventureException extends GameException {
    public PlayerAlreadyLeftAdventureException() {
        super("You left this adventure, you can't play in it anymore");
    }

    public PlayerAlreadyLeftAdventureException(String message) {
        super(message);
    }

    public PlayerAlreadyLeftAdventureException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyLeftAdventureException(Throwable cause) {
        super(cause);
    }

    public PlayerAlreadyLeftAdventureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
