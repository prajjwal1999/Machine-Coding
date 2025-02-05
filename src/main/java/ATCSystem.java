import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Represents a flight request (landing or takeoff)
class Flight {
    private String flightNumber;
    private String requestType; // LAND or TAKEOFF
    private int duration; // Time in seconds

    public Flight(String flightNumber, String requestType, int duration) {
        this.flightNumber = flightNumber;
        this.requestType = requestType;
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getRequestType() {
        return requestType;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return requestType + " " + flightNumber;
    }
}

// Represents a Runway
class Runway {
    private int id;
    private boolean isOccupied;
    private Flight currentFlight;

    public Runway(int id) {
        this.id = id;
        this.isOccupied = false;
        this.currentFlight = null;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public synchronized void assignRunway(Flight flight) {
        isOccupied = true;
        currentFlight = flight;
        System.out.println("[INFO] Runway " + id + " assigned to " + flight + " for " + flight.getDuration() + " seconds.");

        // Simulate landing duration
        new Thread(() -> {
            try {
                Thread.sleep(flight.getDuration() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[INFO] " + flight.getFlightNumber() + " has completed its " + flight.getRequestType());
        }).start();
    }

    public synchronized void releaseRunway() {
        System.out.println("[INFO] Runway " + id + " is now available.");
        isOccupied = false;
        currentFlight = null;
    }

    public Flight getCurrentFlight() {
        return currentFlight;
    }
}

// Controls the assignment of runways to flights
class AirTrafficControl {
    private Queue<Flight> landingQueue;
    private Queue<Flight> takeoffQueue;
    private Runway[] runways;

    public AirTrafficControl(int numRunways) {
        landingQueue = new LinkedList<>();
        takeoffQueue = new LinkedList<>();
        runways = new Runway[numRunways];

        for (int i = 0; i < numRunways; i++) {
            runways[i] = new Runway(i + 1);
        }
    }

    public void requestLanding(Flight flight) {
        boolean assigned = false;
        for (Runway runway : runways) {
            if (runway.isAvailable()) {
                runway.assignRunway(flight);
                assigned = true;
                break;
            }
        }

        if (!assigned) {
            System.out.println("[LOG] No runway available for " + flight.getFlightNumber() + ". Added to landing queue.");
            landingQueue.offer(flight);
        }
    }

    public void requestTakeoff(Flight flight) {
        boolean assigned = false;
        for (Runway runway : runways) {
            if (runway.getCurrentFlight() != null && runway.getCurrentFlight().getFlightNumber().equals(flight.getFlightNumber())) {
                System.out.println("[INFO] " + flight.getFlightNumber() + " is taking off from Runway " + runway.getId() + "...");
                assigned = true;

                // Simulate takeoff duration
                new Thread(() -> {
                    try {
                        Thread.sleep(flight.getDuration() * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runway.releaseRunway();
                    processLandings(); // Check if new landings can happen
                }).start();
                break;
            }
        }

        if (!assigned) {
            System.out.println("[LOG] Takeoff request for " + flight.getFlightNumber() + " denied. Flight must land first or be assigned a runway.");
            takeoffQueue.offer(flight);
        }
    }

    private void processLandings() {
        for (Runway runway : runways) {
            if (runway.isAvailable() && !landingQueue.isEmpty()) {
                Flight flight = landingQueue.poll();
                runway.assignRunway(flight);
            }
        }
    }
}

// CLI application for user input
public class ATCSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of runways: ");
        int numRunways = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        AirTrafficControl atc = new AirTrafficControl(numRunways);

        while (true) {
            System.out.print("Enter flight number, operation (LAND/TAKEOFF), and duration in seconds, or 'EXIT' to quit: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("EXIT")) break;

            String[] parts = input.split(" ");
            if (parts.length != 3 || (!parts[1].equalsIgnoreCase("LAND") && !parts[1].equalsIgnoreCase("TAKEOFF")) || !parts[2].matches("\\d+")) {
                System.out.println("[ERROR] Invalid input. Use format: FL123 LAND 10 or FL123 TAKEOFF 5.");
                continue;
            }

            String flightNumber = parts[0];
            String operation = parts[1].toUpperCase();
            int duration = Integer.parseInt(parts[2]);

            Flight flight = new Flight(flightNumber, operation, duration);

            if (operation.equals("LAND")) {
                atc.requestLanding(flight);
            } else {
                atc.requestTakeoff(flight);
            }
        }

        scanner.close();
    }
}
