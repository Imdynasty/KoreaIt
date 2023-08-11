package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DbConfig {

    @Value("${db.class.name}")
    private String driverClassName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String userName;

    @Value("${db.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(dbUrl);
        ds.setUsername(userName);
        ds.setPassword(password);

        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(1000 * 30);
        ds.setTimeBetweenEvictionRunsMillis(1000 * 3);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}