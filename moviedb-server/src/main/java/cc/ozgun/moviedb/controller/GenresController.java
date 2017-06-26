package cc.ozgun.moviedb.controller;

import cc.ozgun.moviedb.model.Genre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Autofill for the edit view
 * <p>
 * Created by Özgün Ayaz on 6/26/2017. License: CC BY-SA 4.0
 */
@RestController
@RequestMapping(path = "/api/genres")
public class GenresController {

    @GetMapping(produces = "application/json")
    public List<Map<String, String>> getListOfGenres() {
        return Stream.of(Genre.values()).map(Genre::asTokenfieldDictionary).collect(toList());
    }
}
