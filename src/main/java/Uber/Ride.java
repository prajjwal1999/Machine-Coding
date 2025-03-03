package Uber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Ride {
    private String rideId;
    private String driverId;
    private String source;
    private String destination;
    private int availableSeats;
    private double fare;
    private LocalDateTime startTime;
    private double rideDurationHours;
    private String carPlateNo;

    public LocalDateTime calculateEndTime(){
        return startTime.plusHours((long)rideDurationHours);
    }

    public boolean bookSeat(){
        if(availableSeats > 0){
            availableSeats--;
            return true;
        }
        return false;
    }
    public boolean isAvailabe(){
        return availableSeats > 0;
    }
}
