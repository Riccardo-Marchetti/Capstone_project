package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.BACKEND.entities.CinemaRoom;
import riccardo.BACKEND.entities.Seat;
import riccardo.BACKEND.exceptions.NotFoundException;
import riccardo.BACKEND.payloads.CinemaRoomDTO;
import riccardo.BACKEND.repositories.CinemaRoomDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaRoomService {

    @Autowired
    private CinemaRoomDAO cinemaRoomDAO;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ServiceLocator serviceLocator;


    public Page<CinemaRoom> getAllRoom(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
       return this.cinemaRoomDAO.findAll(pageable);
    }

    public CinemaRoom getCinemaRoomById (long id){
       return this.cinemaRoomDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public CinemaRoom saveCinemaRoom (CinemaRoomDTO payload){
        CinemaService cinemaService = serviceLocator.getService(CinemaService.class);
        CinemaRoom cinemaRoom = new CinemaRoom(payload.totalSeat(), cinemaService.getCinemaById(payload.idCinema()));
        return this.cinemaRoomDAO.save(cinemaRoom);
    }
    public CinemaRoom saveCinemaRoom (CinemaRoom cinemaRoom){
        CinemaService cinemaService = serviceLocator.getService(CinemaService.class);
        CinemaRoom newCinemaRoom = new CinemaRoom(cinemaRoom.getTotalSeat(), cinemaService.getCinemaById(cinemaRoom.getCinema().getId()));
        return this.cinemaRoomDAO.save(cinemaRoom);
    }
    public CinemaRoom updateCinemaRoom (long id, CinemaRoomDTO payload){
        CinemaService cinemaService = serviceLocator.getService(CinemaService.class);
        CinemaRoom cinemaRoom = this.cinemaRoomDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        cinemaRoom.setTotalSeat(payload.totalSeat());
        cinemaRoom.setCinema(cinemaService.getCinemaById(payload.idCinema()));
        cinemaRoom.setSeat(seatService.getSeatsByIds(payload.idSeat()));
        return this.cinemaRoomDAO.save(cinemaRoom);
    }
    public void deleteCinemaRoom (long id){
        CinemaRoom cinemaRoom = this.cinemaRoomDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.cinemaRoomDAO.delete(cinemaRoom);
    }
    public List<CinemaRoom> getCinemaRoomsByIds(List<Long> cinemaRoomsIds) {
        List<CinemaRoom> cinemaRoomList = new ArrayList<>();
        for (Long id : cinemaRoomsIds) {
            CinemaRoom cinemaRoom = cinemaRoomDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
            cinemaRoomList.add(cinemaRoom);
        }
        return cinemaRoomList;
    }
}
