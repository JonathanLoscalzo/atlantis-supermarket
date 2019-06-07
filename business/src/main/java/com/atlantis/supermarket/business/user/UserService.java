package com.atlantis.supermarket.business.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.common.parser.Encoder;
import com.atlantis.supermarket.core.shared.business.UseCase;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.event.UserCreatedEvent;
import com.atlantis.supermarket.core.user.exceptions.UserExistsException;
import com.atlantis.supermarket.core.user.exceptions.UsernameNotFoundException;
import com.atlantis.supermarket.core.user.generator.SaveUser;
import com.atlantis.supermarket.core.user.searcher.FindAll;
import com.atlantis.supermarket.core.user.searcher.FindUserByUsername;
import com.atlantis.supermarket.core.user.validation.UserExists;
import com.atlantis.supermarket.core.user.validation.UserNotExists;
import com.atlantis.supermarket.infrastructure.user.UserRepository;

@Service
public class UserService {

    @Autowired
    private FindUserByUsername searcher;

    @Autowired
    private SaveUser saver;

    @Autowired
    private FindAll finder;
    
    @Autowired
    private UserRepository users;
    
    public User findUserByUsername(String username) throws UsernameNotFoundException {
	User user = searcher.findByUsername(username);
	UserNotExists.validate(user, username);
	return user;
    }
    
    private User createUser(User user, User.UserRole role) throws UserExistsException {
	User existent = searcher.findByUsername(user.getUsername());
	UserExists.validate(existent);
	user.setPassword(Encoder.passwordEncoder().encode(user.getPassword()));
	user.addRole(role);
	saver.save(user);
	return user;
    }

    public User createUser(User user) throws UserExistsException {
	return this.createUser(user, User.UserRole.CLIENT);
    }
    
    public User createAdminUser(User user) throws UserExistsException {
	return this.createUser(user, User.UserRole.ADMIN);
    }
    
    public User createCommonUser(User user) throws UserExistsException {
	return this.createUser(user, User.UserRole.USER);
    }
    
    public User createUser(String username, String password) throws UserExistsException {
	User existent = searcher.findByUsername(username);
	UserExists.validate(existent);
	User user = new User();
	user.setPassword(Encoder.passwordEncoder().encode(password));
	user.addRole(User.UserRole.CLIENT);
	saver.save(user);
	return user;
    }

    public List<User> findAll() {
	return finder.findAll();
    }
    
    public User findById(UUID id) throws UsernameNotFoundException {
	return users.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
    }

}
