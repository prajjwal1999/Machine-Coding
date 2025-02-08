package RateLimiting;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RateLimiting {
    private  int maxRequest;
    private  int windowSizeMillis;
    private Map<String, Deque<Long>> requestTimeStamps;

    public RateLimiting(int maxRequest, int windowSizeMillis) {
        this.maxRequest = maxRequest;
        this.windowSizeMillis = windowSizeMillis;
        this.requestTimeStamps = new HashMap<>();
    }
    public synchronized boolean allowRequest(String clientId){
        long currentTimeStamps = System.currentTimeMillis();
        requestTimeStamps.putIfAbsent(clientId,new LinkedList<>());
        Deque<Long> timestamps = requestTimeStamps.get(clientId);

        //remove timestamps outside the window
        while (!timestamps.isEmpty() && (currentTimeStamps-timestamps.peekFirst())>windowSizeMillis){
            timestamps.pollFirst();
        }
        if (timestamps.size() < maxRequest) {
            timestamps.addLast(currentTimeStamps);
            return true; // Request allowed
        }

        return false; // Request denied


    }


    public static void main(String[] args) throws InterruptedException {
        RateLimiting rateLimiter = new RateLimiting(3, 5000); // 3 requests per 5 seconds
        String clientId = "user123";

        // Simulating requests
        for (int i = 0; i < 130; i++) {
            boolean allowed = rateLimiter.allowRequest(clientId+i);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(1); // Simulate time between requests
        }
    }
}
