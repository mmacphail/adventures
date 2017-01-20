package com.macphail.adventures.business.game.repository;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AdventureNotFoundException extends GameException {
    public AdventureNotFoundException() {
        super("This adventure was not found");
    }

    public AdventureNotFoundException(String message) {
        super(message);
    }

    public AdventureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdventureNotFoundException(Throwable cause) {
        super(cause);
    }

    public AdventureNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
