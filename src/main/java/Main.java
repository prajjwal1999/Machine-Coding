import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args){

        System.out.println("Enter no of runways");
        Scanner scanner = new Scanner(System.in);
        int runwayCount = scanner.nextInt();
        List<Runway> runwayArrayList = new ArrayList<>();
        for(int i=0;i<runwayCount;i++){
            Runway runway= new Runway(i+1,false,"IN653");
            runwayArrayList.add(runway);
        }
        System.out.println("No of runways created: "+runwayArrayList.size());
        while(true){
            System.out.println("Enter Type of Request <land> or <Takeoff>");
            String type = scanner.next();
            System.out.println("Creating runways....");
            if(type.equalsIgnoreCase("land")){
                System.out.println("Enter Flight No..");
                String flightNo = scanner.next();
                System.out.println("Enter duration");
                int duration = scanner.nextInt();
                PlanRequest planRequest = new PlanRequest(flightNo,Type.LANDING,duration);
                handleLanding(runwayArrayList,planRequest);
            }
            else if(type.equalsIgnoreCase("takeoff")) {
                System.out.println("Enter Flight No..");
                String flightNo = scanner.next();
                handleTakeoff(runwayArrayList, flightNo);
            }
            else{
                break;
            }

        }


    }
    public static void handleLanding(List<Runway> runwaysList,PlanRequest planRequest){
        AtomicReference<Boolean> check= new AtomicReference<>(false);
        runwaysList.forEach(runway -> {
            if(!runway.isOccupied){
                runway.setOccupied(true);
                runway.setCurrentFlightNo(planRequest.getFlightNo());
                check.set(true);
            }
        });
        if(!check.get()){
            System.out.println("Sorry no space available");
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runwaysList.forEach(runway -> {
                    if(runway.getCurrentFlightNo()==planRequest.getFlightNo()){
                        runway.setOccupied(false);
                        runway.setCurrentFlightNo(null);
                    }
                });
            }
        }, planRequest.getDuration());
        printRunwayInfo(runwaysList);
    }
    public static void handleTakeoff(List<Runway> runwaysList,String flightNo){
        runwaysList.forEach(runway -> {
            if(runway.currentFlightNo.equalsIgnoreCase(flightNo)){
                System.out.println("Flight found on runway, permission to take off...");
                runway.setCurrentFlightNo(null);
                runway.setOccupied(false);
            }
        });
        printRunwayInfo(runwaysList);
    }
    public static void autoTakeOff(List<Runway> runwayList){

    }
    public static void printRunwayInfo(List<Runway> runwayList){
    runwayList.forEach(runway -> {
        System.out.println("<"+runway.getId()+"-->"+runway.currentFlightNo+">");
    });

    }
}
