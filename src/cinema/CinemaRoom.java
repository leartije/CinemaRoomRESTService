package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {

    private final int totalRows = 9;
    private final int totalColumns = 9;
    private List<Seat> availableSeats;
    @JsonIgnore
    private List<ReservedSeat> reservedSeats;

    public CinemaRoom() {
        this.availableSeats = createRoom();
        this.reservedSeats = new ArrayList<>();

    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return this.availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void deleteFromAvailableSeats(Seat seat) {
        this.availableSeats.remove(seat);
    }

    public List<ReservedSeat> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<ReservedSeat> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    private List<Seat> createRoom() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                Seat seat = new Seat(i + 1, j + 1);
                seats.add(seat);
            }
        }
        return seats;
    }
}
