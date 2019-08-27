package com.insper.iotserver.repository.query.technician;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.insper.iotserver.model.Technician;

public class TechnicianRepositoryImpl implements TechnicianRepositoryQuery {

	@Autowired
	private EntityManager manager;

	public List<Technician> getLastByDeviceId(Long deviceId) {
		StringBuffer stmt = new StringBuffer();
		stmt.append("Select s from Color s join s.device d "
				+ "where d.id like " + deviceId + " order by s.id desc");
		TypedQuery<Technician> q = manager.createQuery(stmt.toString(), Technician.class);
		List<Technician> last = q.setMaxResults(1).getResultList();
		System.out.println(last);
		return last;
	}
}
