package com.insper.iotserver.repository.query.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.insper.iotserver.model.User;

public class UserRepositoryImpl implements UserRepositoryQuery {

	@Autowired
	private EntityManager manager;

	public List<User> getLastByDeviceId(Long deviceId) {
		StringBuffer stmt = new StringBuffer();
		stmt.append("Select s from Color s join s.device d "
				+ "where d.id like " + deviceId + " order by s.id desc");
		TypedQuery<User> q = manager.createQuery(stmt.toString(), User.class);
		List<User> last = q.setMaxResults(1).getResultList();
		System.out.println(last);
		return last;
	}
}
