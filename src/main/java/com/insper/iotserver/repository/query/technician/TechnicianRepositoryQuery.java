package com.insper.iotserver.repository.query.technician;

import java.util.List;

import com.insper.iotserver.model.Technician;

public interface TechnicianRepositoryQuery {

	public List<Technician> getLastByDeviceId(Long id);
}
