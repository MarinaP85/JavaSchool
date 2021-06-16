package com.sber.springjdbc.config;

import com.sber.springjdbc.dao.RecipeDao;
import com.sber.springjdbc.dao.RecipeDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Import(DataConfiguration.class)
@EnableTransactionManagement
public class TransactionalConfig {
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public RecipeDao recipeDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return new RecipeDaoImpl(dataSource, jdbcTemplate);
    }

}
