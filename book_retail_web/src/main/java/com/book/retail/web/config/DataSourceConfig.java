package com.book.retail.web.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Data
public class DataSourceConfig {
//    @Value("${db.mysql.dmall_brand_activity.rw.host}")
//    private String host;
//    @Value("${db.mysql.dmall_brand_activity.rw.port}")
//    private String port;
//    @Value("${db.mysql.dmall_brand_activity.rw.user}")
//    private String username;
//    @Value("${db.mysql.dmall_brand_activity.rw.password}")
//    private String password;
    private int maximumPoolSize;

    @Bean
    @Primary
    public DataSource dataSource() {
        String url = "jdbc:h2:mem:test";
        HikariDataSource hikariDataSource = DataSourceBuilder.create().type(HikariDataSource.class)
                .driverClassName("org.h2.Driver")
                .url(url).username("root").password("123456").build();
        hikariDataSource.setMaximumPoolSize(maximumPoolSize);
        return hikariDataSource;
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
