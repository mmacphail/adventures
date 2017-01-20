package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NotEnoughHeroicPointsLeftException extends GameException {
    public NotEnoughHeroicPointsLeftException() {
        super("You don't have any heroic points left to use");
    }

    public NotEnoughHeroicPointsLeftException(String message) {
        super(message);
    }

    public NotEnoughHeroicPointsLeftException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughHeroicPointsLeftException(Throwable cause) {
        super(cause);
    }

    public NotEnoughHeroicPointsLeftException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
