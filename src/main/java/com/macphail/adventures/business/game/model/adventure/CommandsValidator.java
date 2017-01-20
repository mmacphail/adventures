package com.macphail.adventures.business.game.model.adventure;

import com.macphail.adventures.business.game.model.GameException;

import java.util.function.Supplier;

public class CommandsValidator {

    public static void assureThat(boolean condition, Supplier<GameException> supplier) {
        if(!condition) {
            throw supplier.get();
        }
    }

}
