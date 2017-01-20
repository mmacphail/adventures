package com.macphail.adventures.business.game.service.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AdventureAlreadyStartedException extends GameException {

    public AdventureAlreadyStartedException() {
        super("This adventure has already started, you can't join it");
    }

    public AdventureAlreadyStartedException(String message) {
        super(message);
    }

    public AdventureAlreadyStartedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdventureAlreadyStartedException(Throwable cause) {
        super(cause);
    }

    public AdventureAlreadyStartedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
