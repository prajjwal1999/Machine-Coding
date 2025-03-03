package Alarm;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class AlarmService {
    private final PriorityBlockingQueue<Alarm> alarmQueue = new PriorityBlockingQueue<>();

    public AlarmService() {
        startAlarmChecker();
    }

    // Add an alarm
    public void addAlarm(LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            System.out.println("Cannot set an alarm in the past.");
            return;
        }

        Alarm alarm = new Alarm(dateTime,0);
        if (alarmQueue.contains(alarm)) {
            System.out.println(STR."Alarm already exists at \{dateTime}");
            return;
        }

        alarmQueue.offer(alarm);
        System.out.println("Alarm added successfully.");
        displayAlarms();
    }

    // Display all alarms
    public void displayAlarms() {
        if (alarmQueue.isEmpty()) {
            System.out.println("No alarms set for now.");
            return;
        }
        alarmQueue.forEach(System.out::println);
    }

    // Delete an alarm
    public boolean deleteAlarm(int id) {
        return alarmQueue.removeIf(alarm -> alarm.getId()==id);

    }
    public boolean snoozeAlarm(int id, int delayMinutes) {
        Optional<Alarm> alarmToUpdate = alarmQueue.stream()
                .filter(alarm -> alarm.getId() == id)
                .findFirst();

        if (alarmToUpdate.isPresent()) {
            Alarm oldAlarm = alarmToUpdate.get();
            alarmQueue.remove(oldAlarm); // Remove the old alarm

            LocalDateTime newTime = LocalDateTime.now().plusMinutes(delayMinutes);
            Alarm newAlarm = new Alarm(newTime, id); // Reuse same ID

            alarmQueue.offer(newAlarm); // Add updated alarm
            System.out.println("Alarm updated: " + newAlarm);
            return true;
        } else {
            System.out.println("Alarm with ID " + id + " not found.");
            return false;
        }
    }

    // Background thread to execute alarms
    private void startAlarmChecker() {
        Thread alarmThread = new Thread(() -> {
            while (true) {
                try {
                    if (!alarmQueue.isEmpty()) {
                        Alarm nextAlarm = alarmQueue.peek();
                        if (nextAlarm != null && (LocalDateTime.now().isAfter(nextAlarm.getTime()) || LocalDateTime.now().isEqual(nextAlarm.getTime()))) {
                            System.out.println(STR."Wake up! Alarm at \{nextAlarm.getTime()}");
                            alarmQueue.poll(); // Remove the executed alarm
                        }
                    }
                    Thread.sleep(1000); // Check every second
                } catch (InterruptedException e) {
                    System.err.println("Alarm checker interrupted.");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        alarmThread.setDaemon(true);
        alarmThread.start();
    }
}
