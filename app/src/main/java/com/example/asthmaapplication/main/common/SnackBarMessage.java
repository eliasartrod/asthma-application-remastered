package com.example.asthmaapplication.main.common;

import java.util.ArrayList;

public class SnackBarMessage {

    private int resId;
    private String message;
    private ArrayList<String> formattedMessages = new ArrayList<>();

    public SnackBarMessage(int resId) {
        this.resId = resId;
    }

    public SnackBarMessage(String message) {
        this.message = message;
    }

    public int getResId() {
        return resId;
    }

    public ArrayList<String> getFormattedMessages() {
        return formattedMessages;
    }

    public void addFormattedMessage(String formattedMessage) {
        formattedMessages.add(formattedMessage);
    }

    public String getMessage() {
        return message;
    }
}