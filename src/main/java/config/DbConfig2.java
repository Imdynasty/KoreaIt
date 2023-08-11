package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DbConfig2 {
    //@Profile("dev,dev2")// dev, dev2프로필
    @Profile("!real")//real아닌 프로필
    @Configuration
    static class DbConfigDev {
        @Bean(destroyMethod = "close")
        public DataSource dataSource() {
            System.out.println("dev!");
            DataSource ds = new DataSource();
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
            ds.setUsername("BOARD_B1");
            ds.setPassword("_aA123456");

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

    @Profile("real")
    @Configuration
    static class DbConfigReal {
        @Bean(destroyMethod = "close")
        public DataSource dataSource() {
            System.out.println("real!");
            DataSource ds = new DataSource();
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
            ds.setUsername("BOARD_B1");
            ds.setPassword("_aA123456");

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
}