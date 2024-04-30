package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Show;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.ShowDTO;
import riccardo.BACKEND.services.FilmService;
import riccardo.BACKEND.services.ShowService;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping
    public List<Show> getAllShows(){
        return this.showService.getAllShows();
    }

    @GetMapping ("/{showId}")
    public Show getShowById (@PathVariable long showId){
        return this.showService.getShowById(showId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Show saveShow (@RequestBody ShowDTO payload){
        return this.showService.saveShow(payload);
    }

    @PutMapping ("/{showId}")
    public Show updateShow (@PathVariable long showId, @RequestBody ShowDTO payload ){
        return this.showService.updateShow(showId, payload);
    }

    @DeleteMapping ("/{showId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteShow (@PathVariable long showId){
        this.showService.deleteShow(showId);
    }
}
