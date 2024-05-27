package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.entities.CinemaRoom;
import riccardo.BACKEND.exceptions.BadRequestException;
import riccardo.BACKEND.payloads.CinemaDTO;
import riccardo.BACKEND.payloads.CinemaRoomDTO;
import riccardo.BACKEND.services.CinemaRoomService;
import riccardo.BACKEND.services.CinemaService;

import java.util.List;

@RestController
@RequestMapping ("/room")
public class CinemaRoomController {

    @Autowired
    private CinemaRoomService cinemaRoomService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public Page<CinemaRoom> getAllCinemaRoom (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "number") String sortBy){
        return this.cinemaRoomService.getAllRoom(page, size, sortBy);
    }

    @GetMapping ("/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public CinemaRoom getCinemaRoomById (@PathVariable long roomId){
        return this.cinemaRoomService.getCinemaRoomById(roomId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public CinemaRoom saveCinemaRoom (@RequestBody @Validated CinemaRoomDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.cinemaRoomService.saveCinemaRoom(payload);
    }

    @PutMapping ("/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public CinemaRoom updateCinemaRoom (@PathVariable long roomId, @RequestBody @Validated CinemaRoomDTO payload, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.cinemaRoomService.updateCinemaRoom(roomId, payload);
    }

    @DeleteMapping ("/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCinemaRoom (@PathVariable long roomId){
        this.cinemaRoomService.deleteCinemaRoom(roomId);
    }
}
