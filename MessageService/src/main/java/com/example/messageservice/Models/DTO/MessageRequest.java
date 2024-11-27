package com.example.messageservice.Models.DTO;
public class MessageRequest {
    private String message;
    private long sender_id;
    private long receiver_id;
    public MessageRequest(String message, long sender_id, long receiver_id) {
        this.message = message;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getSender_id() {
        return sender_id;
    }
    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }
    public long getReceiver_id() {
        return receiver_id;
    }
    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }
}
