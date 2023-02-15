package com.portal.react.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "appEntityManager",
        transactionManagerRef = "appTransactionManager",
        basePackages = "com.portal.react.persistence.repository.app"
)
public class AppDbConfig {

    @Bean(value = "appHibernateProperties")
    @ConfigurationProperties("projects.app.hibernate.property")
    public HashMap<String, Object> appHibernateProperties() { return new HashMap<String, Object>();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean appEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(appDataSource());
        em.setPackagesToScan("com.portal.react.persistence.entity.app");
        em.setPersistenceUnitName("appEntityManager");  //영속성 객체 이름 설정

        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        final HashMap<String, Object> properties = appHibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager appTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(appEntityManager().getObject());

        return transactionManager;
    }

    @Primary
    @Bean(value= "appDataSource")
    @ConfigurationProperties("projects.app.datasource.hikari")
    public DataSource appDataSource(){
        return DataSourceBuilder.create().build();
    }
}
