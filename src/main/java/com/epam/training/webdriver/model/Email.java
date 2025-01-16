package com.epam.training.webdriver.model;

public class Email {
    private String sender;
    private String receiver;
    private String subject;
    private String content;

    // Constructor
    public Email(String sender, String receiver, String subject, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
    }

    // Getters and Setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "sender='" + maskSensitiveValue(sender) + '\'' +
                ", receiver='" + maskSensitiveValue(receiver) + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    private static String maskSensitiveValue(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.replaceAll(".", "*");
    }
}

