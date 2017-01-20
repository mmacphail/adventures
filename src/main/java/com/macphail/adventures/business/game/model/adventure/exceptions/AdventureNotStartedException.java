package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AdventureNotStartedException extends GameException {
    public AdventureNotStartedException() {
        super("This adventure has not been started yet");
    }

    public AdventureNotStartedException(String message) {
        super(message);
    }

    public AdventureNotStartedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdventureNotStartedException(Throwable cause) {
        super(cause);
    }

    public AdventureNotStartedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
