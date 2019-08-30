package com.insper.iotserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insper.iotserver.model.User;
import com.insper.iotserver.repository.query.user.UserRepositoryQuery;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQuery {
	
}
