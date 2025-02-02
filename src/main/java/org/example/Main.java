package org.example;

import java.util.*;

public class Main {
    private static ParkingLot parkingLot;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] commandArgs = command.split(" ");
            switch (commandArgs[0]) {
                case "create_parking_lot":
                    createParkingLot(commandArgs);
                    break;
                case "park_vehicle":
                    parkVehicle(commandArgs);
                    break;
                case "unpark_vehicle":
                    unparkVehicle(commandArgs);
                    break;
                case "display":
                    display(commandArgs);
                    break;
                case "exit":
                    return;
            }
        }
    }

    private static void createParkingLot(String[] commandArgs) {
        String parkingLotId = commandArgs[1];
        int noOfFloors = Integer.parseInt(commandArgs[2]);
        int noOfSlotsPerFloor = Integer.parseInt(commandArgs[3]);
        parkingLot = new ParkingLot(parkingLotId, noOfFloors, noOfSlotsPerFloor);
        System.out.println("Created parking lot with " + noOfFloors + " floors and " + noOfSlotsPerFloor + " slots per floor");
    }

    private static void parkVehicle(String[] commandArgs) {
        VehicleType type = VehicleType.valueOf(commandArgs[1]);
        String regNo = commandArgs[2];
        String color = commandArgs[3];
        Vehicle vehicle = new Vehicle(type, regNo, color);
        String ticketId = parkingLot.parkVehicle(vehicle);
        if (ticketId.equals("Parking Lot Full")) {
            System.out.println(ticketId);
        } else {
            System.out.println("Parked vehicle. Ticket ID: " + ticketId);
        }
    }

    private static void unparkVehicle(String[] commandArgs) {
        String ticketId = commandArgs[1];
        String result = parkingLot.unparkVehicle(ticketId);
        System.out.println(result);
    }

    private static void display(String[] commandArgs) {
        String displayType = commandArgs[1];
        VehicleType vehicleType = VehicleType.valueOf(commandArgs[2]);
        if ("free_count".equals(displayType)) {
            parkingLot.displayFreeCount(vehicleType);
        } else {
            parkingLot.display(displayType, vehicleType);
        }
    }
}
