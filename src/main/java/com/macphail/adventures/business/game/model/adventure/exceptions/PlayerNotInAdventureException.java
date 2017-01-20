package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class PlayerNotInAdventureException extends GameException {
    public PlayerNotInAdventureException() {
        super("You are not in this adventure so you can't play in it");
    }

    public PlayerNotInAdventureException(String message) {
        super(message);
    }

    public PlayerNotInAdventureException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerNotInAdventureException(Throwable cause) {
        super(cause);
    }

    public PlayerNotInAdventureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
