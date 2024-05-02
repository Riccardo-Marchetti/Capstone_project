package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Film;
import riccardo.BACKEND.entities.Show;
import riccardo.BACKEND.exceptions.BadRequestException;
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
    public Page<Show> getAllShows(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "20") int size, @RequestParam (defaultValue = "film") String sortBy){
        return this.showService.getAllShows(page, size, sortBy);
    }

    @GetMapping ("/{showId}")
    public Show getShowById (@PathVariable long showId){
        return this.showService.getShowById(showId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Show saveShow (@RequestBody @Validated ShowDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.showService.saveShow(payload);
    }

    @PutMapping ("/{showId}")
    public Show updateShow (@PathVariable long showId, @RequestBody @Validated ShowDTO payload, BindingResult validation ){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.showService.updateShow(showId, payload);
    }

    @DeleteMapping ("/{showId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteShow (@PathVariable long showId){
        this.showService.deleteShow(showId);
    }
}
