package com.example.admin.servicezbroadcastreceiverem;

/**
 * Created by admin on 12/5/2017.
 */

public class IncomingMessage {

    private int id;
    private String message;

    public IncomingMessage(int id, String message) {

        this.setId(id);
        this.setMessage(message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
