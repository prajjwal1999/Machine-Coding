package Uber;

import java.util.*;
public class RideBookingService {
    public static void bookRide(User user, String source, String destination, RideSelectionStrategy strategy){
      List<Ride> rides = RideRepository.findRide(source, destination);
      if(rides.isEmpty()){
          System.out.println("No Rides Available." +user.getName() +" Please try again later.");
          return;
      }
      Ride selectRide = null;
      if(strategy == RideSelectionStrategy.FASTEST_RIDE){
          selectRide = rides.stream()
                  .min(Comparator.comparingDouble(Ride::getRideDurationHours)
                  .thenComparing(Ride::calculateEndTime))
                  .orElse(null);

      }
      else if(strategy == RideSelectionStrategy.EARLIEST_END_TIME){
            selectRide = rides.stream()
                    .min(Comparator.comparing(Ride::calculateEndTime)
                            .thenComparingDouble(Ride::getRideDurationHours))
                    .orElse(null);
      }
      //
      else if (strategy == RideSelectionStrategy.FASTEST_RIDE) {
          
      }

        //
      if(selectRide!=null){
          user.bookedRide(selectRide);
          user.setTotalHoursTravel(user.getTotalHoursTravel()+selectRide.getRideDurationHours());
          System.out.println("Ride booked Successfully");
          System.out.println("Ride Details:"+" Car plate no :"+selectRide.getCarPlateNo()+" DriverID: "+selectRide.getDriverId()+" Ride Duration "+selectRide.getRideDurationHours() +" Booked for "+user
                  .getName());
      }
      else{
          System.out.println("No Rides Available you . "+user.getName() +" Please try again later.");
      }
    }
}
