package com.macphail.adventures.business.game.service.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AdventureAlreadyEndedException extends GameException {

    public AdventureAlreadyEndedException() {
        super("This adventure has already ended, so you can't play in it");
    }

    public AdventureAlreadyEndedException(String message) {
        super(message);
    }

    public AdventureAlreadyEndedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdventureAlreadyEndedException(Throwable cause) {
        super(cause);
    }

    protected AdventureAlreadyEndedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
