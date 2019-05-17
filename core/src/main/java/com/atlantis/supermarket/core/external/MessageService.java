package com.atlantis.supermarket.core.external;

///https://www.twilio.com/whatsapp/api
public interface MessageService {
    void sendText(String to, String body);
}
