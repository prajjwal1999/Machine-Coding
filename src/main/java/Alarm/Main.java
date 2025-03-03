package Alarm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main() {
        System.out.println("Alarm App");
        System.out.println("Otions for alram..");
        AlarmService alarmService = new AlarmService();
        while (true) {
            System.out.println("Press 1 to set alarm , Press 2 for delete alarm, Press 3 to update alarm , Press 4 to snooze current alram, Press 5 to exit");
            Scanner scanner = new Scanner(System.in);
            int Case = scanner.nextInt();
            switch (Case) {
                case 1:
                    // Take date input
                    System.out.print("Enter year (YYYY): ");
                    int year = scanner.nextInt();
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter day (1-31): ");
                    int day = scanner.nextInt();
                    // Take time input
                    System.out.print("Enter hour (0-23): ");
                    int hour = scanner.nextInt();
                    System.out.print("Enter minutes (0-59): ");
                    int minute = scanner.nextInt();
                    LocalDateTime dateTimeObj;
                    // Create LocalDate and LocalTime, then combine them into LocalDateTime
                    LocalDate date = LocalDate.of(year, month, day);
                    LocalTime time = LocalTime.of(hour, minute);
                    dateTimeObj = LocalDateTime.of(date, time);
                    alarmService.addAlarm(dateTimeObj);
                    break;
                case 2:
                    alarmService.displayAlarms();
                    System.out.println("Enter the id for the alarm to delete ");
                    int id = scanner.nextInt();
                    alarmService.deleteAlarm(id);
                    break;
                case 3:
                    System.out.println("Enter id for alarm");
                    int idx = scanner.nextInt();
                        System.out.println(STR."Enter delay for alarm \{idx}");
                        int delay = scanner.nextInt();
                        alarmService.snoozeAlarm(idx, delay);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
}
