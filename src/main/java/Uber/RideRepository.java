package Uber;

import java.util.*;
import java.util.stream.Collectors;

public class RideRepository {
    private static List<Ride> availableRides = new ArrayList<>();
    public static void addRide(Ride ride){
        availableRides.add(ride);
        displayRides();
    }
    public static List<Ride>findRide(String source, String destination){
        return availableRides.stream().filter(ride->ride.isAvailabe() && ride.getSource().equalsIgnoreCase(source) && ride.getDestination().equalsIgnoreCase(destination)).collect(Collectors.toList());
    }
    public static void displayRides(){
        System.out.println("Rides Available...");
        availableRides.forEach(ride->System.out.println("Details: "+"Driver Id: "+ ride.getDriverId()+" Ride Duration :"+ride.getRideDurationHours()+ " Car No: "+ride.getCarPlateNo()));
    }

}
