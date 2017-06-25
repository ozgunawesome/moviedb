package cc.ozgun.moviedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MoviedbApplication {

    public static void main(String... args) {
        SpringApplication.run(MoviedbApplication.class, args);
    }

}
