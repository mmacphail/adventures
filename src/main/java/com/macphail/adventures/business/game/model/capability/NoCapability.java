package com.macphail.adventures.business.game.model.capability;

public class NoCapability implements Capability {
    private final String reason;

    public NoCapability(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean isPossible() {
        return false;
    }

    @Override
    public String getReason() {
        return reason;
    }
}
