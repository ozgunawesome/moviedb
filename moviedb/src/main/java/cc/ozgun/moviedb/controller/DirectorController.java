package cc.ozgun.moviedb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * REST controller for getting the list of all directors (to populate the combo box)
 * <p>
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
@RestController
@RequestMapping(path = "/directors")
public class DirectorController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public List<String> getListOfDirectors() {
        return entityManager
                .createQuery("select distinct m.director from Movie m", String.class)
                .getResultList();
    }

}
