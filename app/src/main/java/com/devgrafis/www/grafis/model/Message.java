package com.devgrafis.www.grafis.model;

public class Message {
    private String transmitter, contenMessage;
    public Message(String transmitter, String contenMessage){
        this.transmitter = transmitter;
        this.contenMessage = contenMessage;
    }

    public String getTransmitter() {
        return transmitter;
    }
    public String getContenMessage() {
        return contenMessage;
    }
}
