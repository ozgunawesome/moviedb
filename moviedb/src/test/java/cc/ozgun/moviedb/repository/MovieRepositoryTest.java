package cc.ozgun.moviedb.repository;

import cc.ozgun.moviedb.MoviedbApplication;
import cc.ozgun.moviedb.model.AgeRating;
import cc.ozgun.moviedb.model.Genre;
import cc.ozgun.moviedb.model.Movie;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for Spring Data auto-implemented MovieRepository
 */
public class MovieRepositoryTest {

    private static ApplicationContext context;
    private static MovieRepository repository;

    private Movie movie;

    @BeforeClass
    public static void startApplication() {
        System.setProperty("spring.profiles.active", "test");
        context = SpringApplication.run(MoviedbApplication.class);
        repository = context.getBean(MovieRepository.class);
    }

    @Before
    public void setupMovie() {
        movie = new Movie();
        movie.setAgeRating(AgeRating.EIGHTEEN);
        movie.setDirector("director");
        movie.setDuration(100L);
        movie.setGenres(Collections.singleton(Genre.ACTION));
        movie.setProductionYear(2000);
        movie.setShortTitle("shorttitle");
        movie.setTitle("title");
    }

    @Test
    public void testSuccessfulAddToRepository() {
        repository.save(movie);
        Movie persistedMovie = repository.findOne(movie.getId());
        assertEquals(persistedMovie, movie);
        repository.delete(movie);
    }

    @Test(expected = ValidationException.class)
    public void testAddInvalidYear() {
        movie.setProductionYear(null);
        repository.save(movie);
        repository.delete(movie);
    }

    @Test(expected = ValidationException.class)
    public void testAddNullGenre() {
        movie.setGenres(null);
        repository.save(movie);
        repository.delete(movie);
    }

    @Test(expected = ValidationException.class)
    public void testAddTooManyGenres() {
        movie.setGenres(new HashSet<>(Arrays.asList(Genre.CARTOON, Genre.COMEDY, Genre.CRIME, Genre.ACTION)));
        repository.save(movie);
        repository.delete(movie);
    }
}
