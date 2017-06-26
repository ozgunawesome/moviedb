package cc.ozgun.moviedb.controller;

import cc.ozgun.moviedb.model.Movie;
import cc.ozgun.moviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class MovieController {

    private MovieRepository movieRepository;

    @Autowired
    MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping(path = {"/", "movies"})
    public ModelAndView index(Pageable page) {
        return new ModelAndView("movies").addObject("movies", movieRepository.findAll(page));
    }

    @PostMapping(path = {"/", "movies"})
    public String save(Movie movie) {
        movieRepository.save(movie);
        return "redirect:/";
    }

    @GetMapping(path = "movies/new")
    public ModelAndView add() {
        return new ModelAndView("edit").addObject("movie", new Movie());
    }

    @GetMapping(path = "movies/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return new ModelAndView("edit").addObject("movie", movieRepository.findOne(id));
    }

    @GetMapping(path = "movies/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        movieRepository.delete(id);
        return "redirect:/";
    }

}
