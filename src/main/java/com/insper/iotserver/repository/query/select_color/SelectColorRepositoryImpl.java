package com.insper.iotserver.repository.query.select_color;

import com.insper.iotserver.model.SelectColor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SelectColorRepositoryImpl implements SelectColorRepositoryQuery {

	@Autowired
	private EntityManager manager;

	public List<SelectColor> getLastByDeviceId(Long deviceId) {
		StringBuffer stmt = new StringBuffer();
		stmt.append("Select s from Color s join s.device d "
				+ "where d.id like " + deviceId + " order by s.id desc");
		TypedQuery<SelectColor> q = manager.createQuery(stmt.toString(), SelectColor.class);
		List<SelectColor> last = q.setMaxResults(1).getResultList();
		System.out.println(last);
		return last;
	}
}
