package com.portal.react.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(unitName = "appEntityManager")
    private EntityManager appEntityManager;

/*    @PersistenceContext(unitName = "subEntitymanager")
    private EntityManager subEntityManager;*/

    @Bean
    public JPAQueryFactory appJpaQueryFactory() {
        return new JPAQueryFactory(appEntityManager);
    }

/*    @Bean
    public  JPAQueryFactory subJpaQueryFactory() {
        return new JPAQueryFactory(subEntityManager);
    }*/

}
