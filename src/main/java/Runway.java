public class Runway {
    public  int id;

    public Runway(int id, boolean isOccupied, String currentFlightNo) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.currentFlightNo = currentFlightNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getCurrentFlightNo() {
        return currentFlightNo;
    }

    public void setCurrentFlightNo(String currentFlightNo) {
        this.currentFlightNo = currentFlightNo;
    }

    public boolean isOccupied;
    public String currentFlightNo;
}
