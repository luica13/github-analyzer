package com.analyzer.api.mapper;

import org.springframework.stereotype.Component;

import com.analyzer.api.model.ContributorsStatisticsModel;
import com.analyzer.core.dto.ContributorsStatistics;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component("statisticsModelMapper")
public class StatisticsModelMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(ContributorsStatistics.class, ContributorsStatisticsModel.class)
				.byDefault()
				.register();

		factory.classMap(ContributorsStatisticsModel.class, ContributorsStatistics.class)
				.byDefault()
				.register();
	}
}
