package cc.ozgun.moviedb.repository;

import cc.ozgun.moviedb.model.AgeRating;
import cc.ozgun.moviedb.model.Genre;
import cc.ozgun.moviedb.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Repository interface for Movie entity. Spring Data REST automagically creates a paging and
 * sorting CRUD implementation and exposes its REST API at runtime
 */
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    Page<Movie> findByTitle(@Param("title") String title, Pageable pageable);

    Page<Movie> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);

    Page<Movie> findByShortTitle(@Param("shortTitle") String shortTitle, Pageable pageable);

    Page<Movie> findByShortTitleContainingIgnoreCase(@Param("shortTitle") String shortTitle, Pageable pageable);

    Page<Movie> findByDirector(@Param("director") String director, Pageable pageable);

    Page<Movie> findByDirectorContainingIgnoreCase(@Param("director") String director, Pageable pageable);

    Page<Movie> findByDurationGreaterThanEqual(@Param("min") Long duration, Pageable pageable);

    Page<Movie> findByDurationLessThanEqual(@Param("max") Long duration, Pageable pageable);

    Page<Movie> findByDurationBetween(@Param("min") Long lowerBound, @Param("max") Long upperBound, Pageable pageable);

    Page<Movie> findByGenresContaining(@Param("genre") Genre genre, Pageable pageable);

    Page<Movie> findByGenresNotContaining(@Param("genre") Genre genre, Pageable pageable);

    @Query("select m from Movie m where m.productionYear >= ?1")
    Page<Movie> findByProductionYearOnOrAfter(@Param("year") Integer productionYear, Pageable pageable);

    @Query("select m from Movie m where m.productionYear <= ?1")
    Page<Movie> findByProductionYearOnOrBefore(@Param("year") Integer productionYear, Pageable pageable);

    Page<Movie> findByProductionYearBetween(@Param("from") Integer lowerBound, @Param("to") Integer upperBound, Pageable pageable);

    Page<Movie> findByAgeRating(@Param("ageRating") AgeRating ageRating, Pageable pageable);

    @RestResource(path = "findSuitableForAgeGroup", rel = "findSuitableForAgeGroup")
    Page<Movie> findByAgeRatingGreaterThanEqual(@Param("ageRating") AgeRating ageRating, Pageable pageable);

    @Query(name = "findByRegex", countName = "findByRegexCount")
    Page<Movie> findByRegularExpression(@Param("expression") String expression, Pageable pageable);
}
