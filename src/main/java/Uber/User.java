package Uber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String userId;
    private String name;
    private List<Ride> offeredRides;
    private List<Ride> bookedRides;
    private double totalHoursTravel;
    public User(String userId, String name){
        this.userId = userId;
        this.name = name;
        this.offeredRides = new ArrayList<>();
        this.bookedRides = new ArrayList<>();
    }

    public void offerRide(Ride ride) {
        offeredRides.add(ride);
        RideRepository.addRide(ride);
    }
    public void bookedRide(Ride ride) {
        if(ride.bookSeat()){
            bookedRides.add(ride);
        }
        else{
            System.out.println("No Seats Available");
        }
    }
    public int getTotalRidesOffered()  {
        return offeredRides.size();
    }
    public int getTotalRidesTaken()  {
        return bookedRides.size();
    }
    public double getTotalFuelSaved(double fuelPrice, double rideDurationHours)  {
        double totalFuelSaved = 0.0;
        totalFuelSaved =  bookedRides.size() * (fuelPrice) * (rideDurationHours);
        return totalFuelSaved;
    }
}
