package org.example;

class Slot {
    private int slotNumber;
    private VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public Slot(int slotNumber, VehicleType vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public Vehicle unpark() {
        Vehicle unparkedVehicle = this.vehicle;
        this.vehicle = null;
        this.isOccupied = false;
        return unparkedVehicle;
    }
}

