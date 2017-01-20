package com.macphail.adventures.business.game.model.adventure.exceptions;

import com.macphail.adventures.business.game.model.GameException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NotThisPlayerTimeToSpeakException extends GameException {
    public NotThisPlayerTimeToSpeakException() {
        super("It's not your turn to react yet - wait for another player or the game master");
    }

    public NotThisPlayerTimeToSpeakException(String message) {
        super(message);
    }

    public NotThisPlayerTimeToSpeakException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotThisPlayerTimeToSpeakException(Throwable cause) {
        super(cause);
    }

    public NotThisPlayerTimeToSpeakException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
