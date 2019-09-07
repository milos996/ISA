package com.example.ISAums.email_service;

public interface EmailService {

    void send(String from, String to, String title, String body);
}
