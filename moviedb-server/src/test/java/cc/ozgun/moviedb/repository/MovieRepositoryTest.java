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
    private Movie mockMovie;

    @BeforeClass
    public static void startApplication() {
        System.setProperty("spring.profiles.active", "test");
        context = SpringApplication.run(MoviedbApplication.class);
        repository = context.getBean(MovieRepository.class);

        // Setup the movies used for testing repository search
        // These should be setup once (as opposed to the mock movie which is used across several tests)
        repository.save(new Movie("Office Space", "officespace", 1999,
                "Mike Judge", AgeRating.EIGHTEEN, 5345L, Genre.COMEDY, Genre.DRAMA));
        repository.save(new Movie("The Dark Knight", "darkknight", 2008,
                "Christopher Nolan", AgeRating.TWELVE, 9140L, Genre.CRIME, Genre.DRAMA));
        repository.save(new Movie("Inception", "weneedtogodeeper", 2010,
                "Christopher Nolan", AgeRating.SIXTEEN, 8880L, Genre.SCIFI, Genre.ACTION));
        repository.save(new Movie("The Lion King", "lionking", 1994,
                "Roger Allers", AgeRating.SIX, 4400L, Genre.CARTOON, Genre.ACTION));
        repository.save(new Movie("Harold and Kumar Go to White Castle", "haroldkumar1", 2004,
                "Danny Leiner", AgeRating.EIGHTEEN, 5280L, Genre.COMEDY));
    }

    @Test
    public void testFindMoviesFromRepository() {
        assertEquals(1, repository.findByTitle("Inception", null).getNumberOfElements());
        assertEquals(2, repository.findByTitleContainingIgnoreCase("io", null).getNumberOfElements());
        assertEquals(1, repository.findByShortTitle("darkknight", null).getNumberOfElements());
        assertEquals(3, repository.findByShortTitleContainingIgnoreCase("K", null).getNumberOfElements());
        assertEquals(2, repository.findByDirector("Christopher Nolan", null).getNumberOfElements());
        assertEquals(2, repository.findByDirectorContainingIgnoreCase("nolan", null).getNumberOfElements());
        assertEquals(2, repository.findByDurationGreaterThanEqual(8880L, null).getNumberOfElements());
        assertEquals(3, repository.findByDurationLessThanEqual(5345L, null).getNumberOfElements());
        assertEquals(2, repository.findByDurationBetween(5200L, 6600L, null).getNumberOfElements());
        assertEquals(1, repository.findByGenresContaining(Genre.CARTOON, null).getNumberOfElements());
        assertEquals(3, repository.findByGenresNotContaining(Genre.ACTION, null).getNumberOfElements());
        assertEquals(2, repository.findByProductionYearOnOrAfter(2008, null).getNumberOfElements());
        assertEquals(1, repository.findByProductionYearOnOrBefore(1994, null).getNumberOfElements());
        assertEquals(3, repository.findByProductionYearBetween(1998, 2009, null).getNumberOfElements());
        assertEquals(2, repository.findByAgeRating(AgeRating.EIGHTEEN, null).getNumberOfElements());
        assertEquals(3, repository.findByAgeRatingGreaterThanEqual(AgeRating.SIXTEEN, null).getNumberOfElements());
        assertEquals(3, repository.findByRegularExpression("mar|lan", null).getNumberOfElements());
    }

    @Before
    public void setupMovie() {
        mockMovie = new Movie();
        mockMovie.setAgeRating(AgeRating.EIGHTEEN);
        mockMovie.setDirector("director");
        mockMovie.setDuration(100L);
        mockMovie.setGenres(Collections.singleton(Genre.ACTION));
        mockMovie.setProductionYear(2000);
        mockMovie.setShortTitle("shorttitle");
        mockMovie.setTitle("title");
    }

    @Test
    public void testSuccessfulAddToRepository() {
        repository.save(mockMovie);
        Movie persistedMovie = repository.findOne(mockMovie.getId());
        assertEquals(persistedMovie, mockMovie);
        repository.delete(mockMovie);
    }

    @Test(expected = ValidationException.class)
    public void testAddInvalidYear() {
        mockMovie.setProductionYear(null);
        repository.save(mockMovie);
    }

    @Test(expected = ValidationException.class)
    public void testAddNullGenre() {
        mockMovie.setGenres(null);
        repository.save(mockMovie);
    }

    @Test(expected = ValidationException.class)
    public void testAddTooManyGenres() {
        mockMovie.setGenres(new HashSet<>(Arrays.asList(Genre.CARTOON, Genre.COMEDY, Genre.CRIME, Genre.ACTION)));
        repository.save(mockMovie);
    }

    @Test(expected = ValidationException.class)
    public void testAddNullDirector() {
        mockMovie.setDirector(null);
        repository.save(mockMovie);
    }

    @Test(expected = ValidationException.class)
    public void testAddTooLongShortTitle() {
        mockMovie.setShortTitle("123456789 123456789 123456789 1");
        repository.save(mockMovie);
    }

}
