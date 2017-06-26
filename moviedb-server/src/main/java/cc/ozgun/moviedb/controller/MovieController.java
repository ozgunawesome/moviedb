package cc.ozgun.moviedb.controller;

import cc.ozgun.moviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Özgün Ayaz on 6/26/2017. License: CC BY-SA 4.0
 */
@Controller
@RequestMapping(path = "/")
public class MovieController {

    private MovieRepository movieRepository;

    @Autowired
    MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public ModelAndView index(Pageable page) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("movies", movieRepository.findAll(page));
        return modelAndView;
    }
}
