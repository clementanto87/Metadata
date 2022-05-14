package com.school.management.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
	
	//=================================================
    // Static Variables
    //=================================================
	
	private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
	
	//=================================================
    // Private Variables
    //=================================================
	
	@Value("${data.driver.class.name}")
	private String driverClassName;
	
	@Value("${data.jdbc.url}")

	private String jdbcUrl;
	
	@Value("${db.user.name}")
	private String dbUserName;
	
	@Value("${db.user.password}")
	private String dbPassword;
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	@Value("${db.instance.name}")
	private String dbInstanceName;

	
	//=================================================
    // Beans
    //=================================================

	@Bean
	public DataSource getDataSource() {
		return getLocalDataSource();
	}
	
	

	private DataSource getLocalDataSource() {
		@SuppressWarnings("rawtypes")
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverClassName);
		dataSourceBuilder.url(jdbcUrl);
		dataSourceBuilder.username(dbUserName);
		dataSourceBuilder.password(dbPassword);
		DataSource dataSource = dataSourceBuilder.build();
		logger.info(
				"METHOD=getLocalDataSource" + "INFO_MSG=DataSource initialized successfuly for local configuration");
		return dataSource;
	}

}
