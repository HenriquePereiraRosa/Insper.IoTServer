package com.insper.iotserver.repository.query.state;

import java.util.List;

import com.insper.iotserver.model.State;

public interface StateRepositoryQuery {

	public List<State> getLastByDeviceId(Long id);
}
