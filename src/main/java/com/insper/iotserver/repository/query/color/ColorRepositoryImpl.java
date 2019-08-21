package com.insper.iotserver.repository.query.color;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.insper.iotserver.model.Color;

public class ColorRepositoryImpl implements ColorRepositoryQuery {

	@Autowired
	private EntityManager manager;

	public List<Color> getLastByDeviceId(Long deviceId) {
		StringBuffer stmt = new StringBuffer();
		stmt.append("Select s from Color s join s.device d "
				+ "where d.id like " + deviceId + " order by s.id desc");
		TypedQuery<Color> q = manager.createQuery(stmt.toString(), Color.class);
		List<Color> last = q.setMaxResults(1).getResultList();
		System.out.println(last);
		return last;
	}
}
