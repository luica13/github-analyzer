package com.analyzer.api.mapper;

import org.springframework.stereotype.Component;

import com.analyzer.api.model.RepositoryModel;
import com.analyzer.core.dto.Repository;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component("repositoryModelMapper")
public class RepositoryModelMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Repository.class, RepositoryModel.class)
				.byDefault()
				.register();

		factory.classMap(RepositoryModel.class, Repository.class)
				.byDefault()
				.register();
	}
}
