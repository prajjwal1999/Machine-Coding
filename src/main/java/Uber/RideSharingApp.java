package Uber;

import java.time.LocalDateTime;

public class RideSharingApp {
    public static void main(String[] args) {
        System.out.println("Welcome to Ride Sharing app...");
        User user1 = new User("U1","Ali");
        User user2 = new User("U2","Ahmed");
        Ride ride1 = new Ride("R12","U3","A","B",3,100.0, LocalDateTime.now(),2.0,"UK-1234");
        Ride ride2 = new Ride("R13","U2","B","C",2,200.0,LocalDateTime.now().plusHours(3),1.5,"UK079966");
        user1.offerRide(ride1);
        user1.offerRide(ride2);
        RideBookingService.bookRide(user2,"A","B",RideSelectionStrategy.FASTEST_RIDE);
        System.out.println(".....................................................................");
        RideBookingService.bookRide(user2,"C","B",RideSelectionStrategy.EARLIEST_END_TIME);
        System.out.println(".....................................................................");
        System.out.println(STR."ali Offered Rides: \{user1.getTotalRidesOffered()}");
        System.out.println(STR."Ahmed took rides: \{user2.getTotalRidesTaken()}");
        double fuelPerHour =6;
        System.out.println(STR."Total Fuel Saved: \{user2.getTotalFuelSaved(fuelPerHour,user2.getTotalHoursTravel())}");

    }
}
