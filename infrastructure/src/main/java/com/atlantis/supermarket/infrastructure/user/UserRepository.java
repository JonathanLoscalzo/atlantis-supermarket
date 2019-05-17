package com.atlantis.supermarket.infrastructure.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.generator.SaveUser;
import com.atlantis.supermarket.core.user.searcher.FindAll;
import com.atlantis.supermarket.core.user.searcher.FindUserByUsername;


public interface UserRepository extends JpaRepository<User, UUID>, SaveUser, FindAll, FindUserByUsername{
}
