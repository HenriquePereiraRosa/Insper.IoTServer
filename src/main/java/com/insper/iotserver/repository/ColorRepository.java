package com.insper.iotserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insper.iotserver.model.Color;
import com.insper.iotserver.repository.query.color.ColorRepositoryQuery;

public interface ColorRepository extends JpaRepository<Color, Long>, ColorRepositoryQuery {
	
}
