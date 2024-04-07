package ru.ramanpan.minidocmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("ru.ramanpan.minidocmanagement")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class ApplicationContextConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.ramanpan.minidocmanagement.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520); // 20MB
        multipartResolver.setMaxInMemorySize(1048576);    // 1MB
        return multipartResolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    //    @Bean(name = "dataSource")
//    public DataSource getDataSource() {
//    	BasicDataSource dataSource = new BasicDataSource();
//    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//    	dataSource.setUrl("jdbc:postgresql://localhost:5432/learnpractic");
//    	dataSource.setUsername("postgres");
//    	dataSource.setPassword("postgres");
//
//    	return dataSource;
//    }
//
//
//    private Properties getHibernateProperties() {
//    	Properties properties = new Properties();
//    	properties.put("hibernate.show_sql", "true");
//    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//    	return properties;
//    }
//
//    @Autowired
//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//    	sessionBuilder.addProperties(getHibernateProperties());
//    	sessionBuilder.addAnnotatedClasses(UploadFile.class);
//    	return sessionBuilder.buildSessionFactory();
//    }
//
//	@Autowired
//	@Bean(name = "transactionManager")
//	public HibernateTransactionManager getTransactionManager(
//			SessionFactory sessionFactory) {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
//				sessionFactory);
//
//		return transactionManager;
//	}
//
//    @Autowired
//    @Bean(name = "fileUploadDao")
//    public FileUploadDAO getUserDao(SessionFactory sessionFactory) {
//    	return new FileUploadDAOImpl(sessionFactory);
//    }
//

}
