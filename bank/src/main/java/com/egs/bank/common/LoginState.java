package com.egs.bank.common;

public enum LoginState {

    LOGGED_IN("LOGGED_IN"),
    FAILED("FAILED");

    private String state;

    LoginState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
