package cc.ozgun.moviedb.configuration;

import cc.ozgun.moviedb.model.Movie;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;

/**
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
@Configuration
public class MovieRepositoryConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    public RepositoryRestConfiguration config() {
        return super.config()
                .exposeIdsFor(Movie.class) //expose the 'id' field
                .useHalAsDefaultJsonMediaType(false)
                .setDefaultMediaType(MediaType.APPLICATION_JSON_UTF8);
    }

}

