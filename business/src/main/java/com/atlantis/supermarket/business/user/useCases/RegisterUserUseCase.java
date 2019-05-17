package com.atlantis.supermarket.business.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.user.useCases.input.RegisterUser;
import com.atlantis.supermarket.business.user.useCases.output.UserCreated;
import com.atlantis.supermarket.common.parser.Encoder;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.exceptions.UserExistsException;
import com.atlantis.supermarket.core.user.generator.SaveUser;
import com.atlantis.supermarket.core.user.searcher.FindUserByUsername;
import com.atlantis.supermarket.core.user.validation.UserExists;

@Service
public class RegisterUserUseCase {
    
    @Autowired
    private FindUserByUsername searcher;
    
    @Autowired
    private SaveUser saver;
    
    public UserCreated handle(RegisterUser input) throws UserExistsException {
	User existent = searcher.findByUsername(input.getUsername());
	UserExists.validate(existent);
	User user = new User();
	user.setEmail(input.getEmail());
	user.setPassword(Encoder.passwordEncoder().encode(input.getPassword()));
	user.addRole(User.UserRole.USER);
	saver.save(user);
	return new UserCreated(user.getUsername());
    }
}
