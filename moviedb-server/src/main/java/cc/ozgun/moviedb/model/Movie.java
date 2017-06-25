package cc.ozgun.moviedb.model;

import cc.ozgun.moviedb.model.mapper.DurationDeserializer;
import cc.ozgun.moviedb.model.mapper.DurationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Arrays.asList;

@Entity
@Validated
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "countMapping", columns = @ColumnResult(name = "numResults"))
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findByRegex",
                resultClass = Movie.class,
                query = "select * from Movie m where regexp_like(m.title, ?1) or regexp_like(m.short_title, ?1) or regexp_like(m.director, ?1)"),
        @NamedNativeQuery(
                name = "findByRegexCount",
                resultSetMapping = "countMapping",
                query = "select count(*) as numResults from Movie m where regexp_like(m.title, ?1) or regexp_like(m.short_title, ?1) or regexp_like(m.director, ?1)")
})
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    @NotBlank
    @Size(min = 1, max = 30)
    private String shortTitle;

    @Min(1900)
    @Max(2100)
    @NotNull
    private Integer productionYear;

    @NotBlank
    @Size(min = 1, max = 200)
    private String director;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Size(min = 1, max = 3)
    private Set<Genre> genres;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private AgeRating ageRating;

    @Min(0)
    @NotNull
    @JsonSerialize(using = DurationSerializer.class)
    @JsonDeserialize(using = DurationDeserializer.class)
    private Long duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(shortTitle, movie.shortTitle) &&
                Objects.equals(productionYear, movie.productionYear) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(genres, movie.genres) &&
                ageRating == movie.ageRating &&
                Objects.equals(duration, movie.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortTitle, productionYear, director, genres, ageRating, duration);
    }

    public Movie(String title, String shortTitle, Integer productionYear, String director, AgeRating ageRating, Long duration, Genre... genres) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.productionYear = productionYear;
        this.director = director;
        this.genres = new HashSet<>(asList(genres));
        this.ageRating = ageRating;
        this.duration = duration;
    }

    public Movie() {
    }
}
