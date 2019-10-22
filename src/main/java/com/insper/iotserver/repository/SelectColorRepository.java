package com.insper.iotserver.repository;

import com.insper.iotserver.model.SelectColor;
import com.insper.iotserver.repository.query.select_color.SelectColorRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectColorRepository extends JpaRepository<SelectColor, Long>, SelectColorRepositoryQuery {
	
}
