package com.atlantis.supermarket.external.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.external.EmailService;
import com.atlantis.supermarket.infrastructure.external.email.EmailAdapter;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailAdapter adapter;
    
    @Override
    public void sendText(String from, String to, String subject, String body) {
	this.adapter.sendEmail(from, to, subject, body);
	
    }

    @Override
    public void sendHTML(String from, String to, String subject, String body) {
	// TODO Auto-generated method stub
	
    }

}
