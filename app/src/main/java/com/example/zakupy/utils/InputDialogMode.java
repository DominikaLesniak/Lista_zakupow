package com.example.zakupy.utils;

public enum InputDialogMode {
    CREATE("Dodaj"),
    EDIT("Edytuj");

    public String actionName;

    InputDialogMode(String actionName) {
        this.actionName = actionName;
    }
}
