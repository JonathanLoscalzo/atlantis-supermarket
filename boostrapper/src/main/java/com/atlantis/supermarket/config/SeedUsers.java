package com.atlantis.supermarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.business.user.UserService;
import com.atlantis.supermarket.common.parser.Encoder;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.exceptions.UserExistsException;
import com.atlantis.supermarket.core.user.exceptions.UsernameNotFoundException;

@Component
public class SeedUsers {
    // @Autowired
    // private
    @Autowired
    private UserService service;

    public void addAdmin() {
	try {
	    service.findUserByUsername("admin");
	} catch (UsernameNotFoundException e) {
	    User admin = new User();
	    admin.setUsername("admin");
	    admin.setPassword("admin");
	    try {
		service.createAdminUser(admin);
	    } catch (UserExistsException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}
    }
}
