package com.insper.iotserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insper.iotserver.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
	
	Device getByCode(String code);
	Device getByLabel(String label);
}
