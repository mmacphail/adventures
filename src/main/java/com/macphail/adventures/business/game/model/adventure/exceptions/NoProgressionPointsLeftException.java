package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NoProgressionPointsLeftException extends GameException {
    public NoProgressionPointsLeftException() {
        super("You don't have enoguh progression points left to do that");
    }

    public NoProgressionPointsLeftException(String message) {
        super(message);
    }

    public NoProgressionPointsLeftException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoProgressionPointsLeftException(Throwable cause) {
        super(cause);
    }

    public NoProgressionPointsLeftException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
