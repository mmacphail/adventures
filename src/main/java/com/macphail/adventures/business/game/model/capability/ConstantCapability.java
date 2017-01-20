package com.macphail.adventures.business.game.model.capability;

public class ConstantCapability implements Capability {
    private final String reason;

    public ConstantCapability(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    @Override
    public String getReason() {
        return this.reason;
    }
}
