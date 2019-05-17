package com.atlantis.supermarket.external.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.external.MessageService;
import com.atlantis.supermarket.infrastructure.external.message.WhatsappAdapter;

@Service
public class WhatsappServiceImpl implements MessageService {

    @Autowired
    private WhatsappAdapter adapter;
    
    @Override
    public void sendText(String to, String body) {
	// TODO Auto-generated method stub
	
    }

}
