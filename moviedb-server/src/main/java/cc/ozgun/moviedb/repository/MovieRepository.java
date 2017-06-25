package cc.ozgun.moviedb.repository;

import cc.ozgun.moviedb.model.AgeRating;
import cc.ozgun.moviedb.model.Genre;
import cc.ozgun.moviedb.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;

/**
 * Repository interface for Movie entity. Spring Data REST automagically creates a paging and
 * sorting CRUD implementation and exposes its REST API at runtime
 */
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    Page<Movie> findByTitle(@Param("title") String title, Pageable pageable);

    Page<Movie> findByTitleContaining(@Param("title") String title, Pageable pageable);

    Page<Movie> findByShortTitle(@Param("shortTitle") String shortTitle, Pageable pageable);

    Page<Movie> findByShortTitleContaining(@Param("shortTitle") String shortTitle, Pageable pageable);

    Page<Movie> findByDirector(@Param("director") String director, Pageable pageable);

    Page<Movie> findByDirectorContaining(@Param("director") String director, Pageable pageable);

    Page<Movie> findByDurationGreaterThanEqual(@Param("min") Long duration, Pageable pageable);

    Page<Movie> findByDurationLessThanEqual(@Param("max") Long duration, Pageable pageable);

    Page<Movie> findByDurationBetween(@Param("min") Long duration, @Param("max") Long duration2, Pageable pageable);

    Page<Movie> findByGenresContaining(@Param("genres") Set<Genre> genres, Pageable pageable);

    Page<Movie> findByGenresNotContaining(@Param("genres") Set<Genre> genres, Pageable pageable);

    Page<Movie> findByAgeRating(@Param("ageRating") AgeRating ageRating, Pageable pageable);

    @RestResource(path = "findByAgeRatingSuitableFor")
    Page<Movie> findByAgeRatingGreaterThanEqual(@Param("ageRating") AgeRating ageRating, Pageable pageable);
}
