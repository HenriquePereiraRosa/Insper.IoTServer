package com.insper.iotserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insper.iotserver.model.Technician;
import com.insper.iotserver.repository.query.technician.TechnicianRepositoryQuery;

public interface TechnicianRepository extends JpaRepository<Technician, Long>, TechnicianRepositoryQuery {
	
}
