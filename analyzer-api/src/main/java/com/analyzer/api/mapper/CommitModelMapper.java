package com.analyzer.api.mapper;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.analyzer.api.model.CommitStatisticsModel;
import com.analyzer.core.dto.CommitsStatistics;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.Type;

@Component("commitsModelMapper")
public class CommitModelMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(CommitsStatistics.class, CommitStatisticsModel.class)
						.byDefault()
						.register();

		factory.classMap(CommitStatisticsModel.class, CommitsStatistics.class)
						.byDefault()
						.register();
		
		factory.getConverterFactory().registerConverter(new DateConverter());
	}

	public class DateConverter extends BidirectionalConverter<Date, OffsetDateTime> {

		@Override
		public OffsetDateTime convertTo(Date source, Type<OffsetDateTime> destinationType,
				MappingContext mappingContext) {
			return OffsetDateTime.from(source.toInstant());
		}

		@Override
		public Date convertFrom(OffsetDateTime source, Type<Date> destinationType, MappingContext mappingContext) {
			return Date.from(source.toInstant());
		}

	}
}
