package com.macphail.adventures.business.game.model.capability;

import java.util.function.Supplier;

public class ConditionalCapability implements Capability {

    private boolean feasible = true;
    private String reason = "Unknown";
    private boolean done = false;

    public ConditionalCapability(String positiveReason, Supplier<Boolean> condition, String reasonIfFalse) {
        this.reason = positiveReason;

        checkIfFeasible(condition, reasonIfFalse);
    }

    public ConditionalCapability and(Supplier<Boolean> condition, String reasonIfFalse) {
        checkIfFeasible(condition, reasonIfFalse);
        return this;
    }

    private void checkIfFeasible(Supplier<Boolean> condition, String reasonIfFalse) {
        if(!done) {
            if(!conditionHoldsTrue(condition)) {
                markNotFeasible(reasonIfFalse);
            }
        }
    }

    private boolean conditionHoldsTrue(Supplier<Boolean> condition) {
        return condition.get();
    }

    private void markNotFeasible(String reason) {
        this.feasible = false;
        this.reason = reason;
        this.done = true;
    }


    @Override
    public boolean isPossible() {
        return feasible;
    }

    @Override
    public String getReason() {
        return reason;
    }
}
