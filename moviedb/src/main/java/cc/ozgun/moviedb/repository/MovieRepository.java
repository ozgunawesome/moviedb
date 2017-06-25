package cc.ozgun.moviedb.repository;

import cc.ozgun.moviedb.model.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository interface for Movie entity. Spring Data REST automagically creates a paging and
 * sorting CRUD implementation and exposes its REST API at runtime
 */
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
}
