package com.library.management.system.service;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service("customSessionFactory")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class CustomSessionFactoryImpl implements CustomSessionFactory {

	private static SessionFactory SESSION_FACTORY;
	private final Logger logger = LoggerFactory.getLogger(CustomSessionFactoryImpl.class);


	@Autowired
	private Environment env;
	public CustomSessionFactoryImpl() {
		logger.debug("initialsing CustomSessionFactory service");
	}
	@Override
	@Bean
	public SessionFactory getSessionFactory() {
		if(SESSION_FACTORY != null) {
			return SESSION_FACTORY;
		}
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setPackagesToScan(new String[] { "com.library.management.system.model" });
		lsfb.setDataSource(restDataSource());
		lsfb.setHibernateProperties(hibernateProperties());
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			logger.error("Setting the properties of the local sessionFactory failed: " + e.toString());
		}
		SESSION_FACTORY= (SessionFactory) lsfb.getObject();
		return SESSION_FACTORY;
	}
	@Bean
	public DataSource restDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("mySQL.driverClassName"));
		dataSource.setUrl(env.getProperty("mySQL.url"));
		dataSource.setUsername(env.getProperty("mySQL.username"));
		dataSource.setPassword(env.getProperty("mySQL.password"));

		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(this.getSessionFactory());

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@SuppressWarnings("serial")
	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			}
		};
	}


}
