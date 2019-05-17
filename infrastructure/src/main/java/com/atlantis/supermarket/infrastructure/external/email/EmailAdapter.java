package com.atlantis.supermarket.infrastructure.external.email;

import com.sendgrid.*;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailAdapter {

    @Value("${spring.sendgrid.api-key}")
    private String sendgrid_key;

    public void sendEmail(String f, String t, String subject, String body) {
	Email from = new Email(f);
	Email to = new Email(t);
	Content content = new Content("text/plain", body);
	Mail mail = new Mail(from, subject, to, content);

	SendGrid sg = new SendGrid(sendgrid_key);
	Request request = new Request();

	try {
	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	    request.setBody(mail.build());
	    Response response = sg.api(request);
	} catch (IOException ex) {
	    // TODO:
	} catch (Exception ex) {
	    // TODO:
	}

    }
}
