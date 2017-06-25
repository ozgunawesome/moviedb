package cc.ozgun.moviedb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * In-memory database to be used for testing
 * <p>
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
@Profile("test")
@Configuration
public class TestDataSource {

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl("jdbc:h2:mem:movie_db_test");
        dataSource.setDriverClass(org.h2.Driver.class);
        return dataSource;
    }
}
