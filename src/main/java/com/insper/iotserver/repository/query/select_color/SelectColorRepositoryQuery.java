package com.insper.iotserver.repository.query.select_color;

import com.insper.iotserver.model.SelectColor;

import java.util.List;

public interface SelectColorRepositoryQuery {

	public List<SelectColor> getLastByDeviceId(Long id);
}
