package com.toread.repository;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author 探路者
 */
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@PropertySource({"classpath:jdbc.properties"})
@ComponentScan(basePackages = "com.toread")
public class SpringConfig {
    @Autowired
    private Environment env;

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.ORACLE);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource);
        lemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lemfb.setPackagesToScan("com.toread.repository.entity");
        return lemfb;
    }

    @Bean
    public SharedEntityManagerBean sharedEntityManagerBean(EntityManagerFactory entityManagerFactory){
        SharedEntityManagerBean sharedEntityManagerBean  =  new SharedEntityManagerBean();
        sharedEntityManagerBean.setEntityManagerFactory(entityManagerFactory);
        return sharedEntityManagerBean;
    }
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.maxActive")));
        dataSource.setValidationQuery(env.getProperty("jdbc.validationQuery"));
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("jdbc.testWhileIdle")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
        dataSource.setMaxWait(Long.parseLong(env.getProperty("jdbc.maxWait")));
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("jdbc.testOnBorrow")));
        //添加日志记录器
        return dataSource;
    }
}
