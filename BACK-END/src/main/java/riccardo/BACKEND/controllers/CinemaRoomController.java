package riccardo.BACKEND.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.BACKEND.entities.Cinema;
import riccardo.BACKEND.entities.CinemaRoom;
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
    public Page<CinemaRoom> getAllCinemaRoom (@RequestParam int page, @RequestParam int size, @RequestParam String sortBy){
        return this.cinemaRoomService.getAllRoom(page, size, sortBy);
    }

    @GetMapping ("/{roomId}")
    public CinemaRoom getCinemaRoomById (@PathVariable long roomId){
        return this.cinemaRoomService.getCinemaRoomById(roomId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public CinemaRoom saveCinemaRoom (@RequestBody CinemaRoomDTO payload){
        return this.cinemaRoomService.saveCinemaRoom(payload);
    }

    @PutMapping ("/{roomId}")
    public CinemaRoom updateCinemaRoom (@PathVariable long roomId, @RequestBody CinemaRoomDTO payload ){
        return this.cinemaRoomService.updateCinemaRoom(roomId, payload);
    }

    @DeleteMapping ("/{roomId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCinemaRoom (@PathVariable long roomId){
        this.cinemaRoomService.deleteCinemaRoom(roomId);
    }
}
