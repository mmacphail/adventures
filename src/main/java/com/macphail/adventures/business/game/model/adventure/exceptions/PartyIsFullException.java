package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class PartyIsFullException extends GameException {
    public PartyIsFullException() {
        super("You can't join this adventure because the party is already full");
    }

    public PartyIsFullException(String message) {
        super(message);
    }

    public PartyIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartyIsFullException(Throwable cause) {
        super(cause);
    }

    public PartyIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
