package com.insper.iotserver.repository.query.state;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.insper.iotserver.model.State;

public class StateRepositoryImpl implements StateRepositoryQuery {

	@Autowired
	private EntityManager manager;

	public List<State> getLastByDeviceId(Long deviceId) {
		StringBuffer stmt = new StringBuffer();
		stmt.append("Select s from State s join s.device d "
				+ "where d.id like " + deviceId + " order by s.id desc");
		TypedQuery<State> q = manager.createQuery(stmt.toString(), State.class);
		List<State> last = q.setMaxResults(1).getResultList();
		System.out.println(last);
		return last;
	}
}
