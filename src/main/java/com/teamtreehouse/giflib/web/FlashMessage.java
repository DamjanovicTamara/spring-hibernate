package com.teamtreehouse.giflib.web;

public class FlashMessage {
    private String message;
    private Status status;
    public static enum Status{
        SUCCESS,FAILURE
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
    public FlashMessage(String message,Status status){
        this.message=message;
        this.status=status;
    }
}
