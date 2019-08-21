package com.insper.iotserver.repository.query.color;

import java.util.List;

import com.insper.iotserver.model.Color;

public interface ColorRepositoryQuery {

	public List<Color> getLastByDeviceId(Long id);
}
