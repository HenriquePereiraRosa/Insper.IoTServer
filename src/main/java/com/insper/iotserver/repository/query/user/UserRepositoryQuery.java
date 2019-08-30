package com.insper.iotserver.repository.query.user;

import java.util.List;

import com.insper.iotserver.model.User;

public interface UserRepositoryQuery {

	public List<User> getLastByDeviceId(Long id);
}
