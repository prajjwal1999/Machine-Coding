package org.example;

import java.util.HashMap;
import java.util.Map;

class ParkingLot {
    private String id;
    private int noOfFloors;
    private int noOfSlotsPerFloor;
    private Map<Integer, Map<Integer, Slot>> floors;

    public ParkingLot(String id, int noOfFloors, int noOfSlotsPerFloor) {
        this.id = id;
        this.noOfFloors = noOfFloors;
        this.noOfSlotsPerFloor = noOfSlotsPerFloor;
        this.floors = new HashMap<>();
        for (int i = 1; i <= noOfFloors; i++) {
            Map<Integer, Slot> slots = new HashMap<>();
            for (int j = 1; j <= noOfSlotsPerFloor; j++) {
                Slot slot = new Slot(j, getVehicleType(i, j));
                slots.put(j, slot);
            }
            this.floors.put(i, slots);
        }
    }

    private VehicleType getVehicleType(int floor, int slot) {
        if (slot == 1) return VehicleType.TRUCK;
        if (slot <= 3) return VehicleType.BIKE;
        return VehicleType.CAR;
    }

    public String parkVehicle(Vehicle vehicle) {
        for (int i = 1; i <= noOfFloors; i++) {
            Map<Integer, Slot> floorSlots = floors.get(i);
            for (Map.Entry<Integer, Slot> entry : floorSlots.entrySet()) {
                Slot slot = entry.getValue();
                if (slot.isAvailable() && slot.getVehicleType() == vehicle.getType()) {
                    slot.occupy(vehicle);
                    return id + "_" + i + "_" + slot.getSlotNumber();
                }
            }
        }
        return "Parking Lot Full";
    }

    public String unparkVehicle(String ticketId) {
        String[] ticketDetails = ticketId.split("_");
        int floorNo = Integer.parseInt(ticketDetails[1]);
        int slotNo = Integer.parseInt(ticketDetails[2]);
        Slot slot = floors.get(floorNo).get(slotNo);
        if (slot != null && !slot.isAvailable()) {
            Vehicle vehicle = slot.unpark();
            return "Unparked vehicle with Registration Number: " + vehicle.getRegistrationNumber() + " and Color: " + vehicle.getColor();
        }
        return "Invalid Ticket";
    }

    public void display(String displayType, VehicleType vehicleType) {
        for (int i = 1; i <= noOfFloors; i++) {
            Map<Integer, Slot> floorSlots = floors.get(i);
            StringBuilder result = new StringBuilder();
            for (Slot slot : floorSlots.values()) {
                if (slot.getVehicleType() == vehicleType) {
                    result.append(slot.getSlotNumber()).append(",");
                }
            }
            if (result.length() > 0) {
                result.deleteCharAt(result.length() - 1); // Remove last comma
            }
            System.out.println(displayType + " for " + vehicleType + " on Floor " + i + ": " + result.toString());
        }
    }

    public void displayFreeCount(VehicleType vehicleType) {
        for (int i = 1; i <= noOfFloors; i++) {
            Map<Integer, Slot> floorSlots = floors.get(i);
            long freeSlots = floorSlots.values().stream().filter(slot -> slot.getVehicleType() == vehicleType && slot.isAvailable()).count();
            System.out.println("No. of free slots for " + vehicleType + " on Floor " + i + ": " + freeSlots);
        }
    }
}

