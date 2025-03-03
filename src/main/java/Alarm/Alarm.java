package Alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Alarm implements Comparable<Alarm> {
    private int id;
    private LocalDateTime time;
    private int delay;//minute delay
    private static int counter = 0; // Static counter for auto-incremented IDs

    public Alarm(LocalDateTime time, int delay) {
        this.id =counter++;
        this.time = time;
        this.delay = delay;
    }
    @Override
    public int compareTo(Alarm other) {
        return this.time.compareTo(other.time); // Sort by time (earliest first)
    }
    @Override
    public String toString() {
        return "Alarm{" + "time=" + time + '}';
    }

}
