package com.insper.iotserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insper.iotserver.model.State;
import com.insper.iotserver.repository.query.state.StateRepositoryQuery;

public interface StateRepository extends JpaRepository<State, Long>, StateRepositoryQuery {
	
}
