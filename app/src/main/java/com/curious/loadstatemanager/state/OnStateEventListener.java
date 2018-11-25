package com.curious.loadstatemanager.state;

public interface OnStateEventListener {
    void onTrigger(BaseState state, Object... args);
}
