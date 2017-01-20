package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class PlayerAlreadyInAdventureException extends GameException {
    public PlayerAlreadyInAdventureException() {
        super("You already are in this adventure");
    }

    public PlayerAlreadyInAdventureException(String message) {
        super(message);
    }

    public PlayerAlreadyInAdventureException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyInAdventureException(Throwable cause) {
        super(cause);
    }

    public PlayerAlreadyInAdventureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
