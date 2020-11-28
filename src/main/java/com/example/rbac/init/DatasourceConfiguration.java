package com.example.rbac.init;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.example.rbac.repository" })
@Slf4j
public class DatasourceConfiguration {
	
	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() throws Exception {
		log.info("Creating database ");
		return DataSourceBuilder.create().url("jdbc:h2:mem:testdb")
				.driverClassName("org.h2.Driver").username("sa").password("").build();

	}
}
