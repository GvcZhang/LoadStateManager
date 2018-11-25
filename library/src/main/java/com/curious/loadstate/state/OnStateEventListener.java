package com.curious.loadstate.state;

public interface OnStateEventListener {
    void onTrigger(BaseState state, Object... args);
}
