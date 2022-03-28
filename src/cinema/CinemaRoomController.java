package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaRoomController {

    private final CinemaRoom cinema;
    private final Statistic statistic;

    public CinemaRoomController() {
        this.cinema = new CinemaRoom();
        this.statistic = new Statistic();
    }

    @GetMapping("/seats")
    public CinemaRoom cinemaRoom() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) {
        if (seat.getRow() < 1 || seat.getColumn() < 1 ||
                seat.getRow() > cinema.getTotalRows() ||
                seat.getColumn() > cinema.getTotalColumns()) {

            return ResponseEntity.badRequest().body(Map.of(
                    "error",
                    "The number of a row or a column is out of bounds!"));
        }

        for (int i = 0; i < cinema.getAvailableSeats().size(); i++) {
            Seat currentSeat = cinema.getAvailableSeats().get(i);
            if (seat.getRow() == currentSeat.getRow() &&
                    seat.getColumn() == currentSeat.getColumn()) {

                statistic.updateStatisticPurchase(currentSeat.getPrice());

                ReservedSeat reservedSeat = new ReservedSeat(UUID.randomUUID(), currentSeat);
                cinema.getReservedSeats().add(reservedSeat);
                cinema.deleteFromAvailableSeats(currentSeat);
                return ResponseEntity.ok(reservedSeat);
            }
        }

        return ResponseEntity.badRequest().body(Map.of(
                "error",
                "The ticket has been already purchased!"));
    }


    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {
        for (int i = 0; i < cinema.getReservedSeats().size(); i++) {
            ReservedSeat currentSeat = cinema.getReservedSeats().get(i);
            if (currentSeat.getToken().equals(token.getToken())) {

                statistic.updateStatisticRefund(currentSeat.getTicket().getPrice());

                cinema.getReservedSeats().remove(currentSeat);
                cinema.getAvailableSeats().add(currentSeat.getTicket());
                return ResponseEntity.ok(Map.of("returned_ticket", currentSeat.getTicket()));
            }
        }
        return ResponseEntity.badRequest().body(Map.of("error","Wrong token!"));
    }

    @PostMapping ("/stats")
    public ResponseEntity<?> statistic(@RequestParam (required = false) String password) {
        if (password != null) {
            return ResponseEntity.ok(statistic);
        } else {
            return new ResponseEntity<>(Map.of("error","The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

    }


}
