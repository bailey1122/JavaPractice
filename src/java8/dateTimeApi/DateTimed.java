package java8.dateTimeApi;

import java.time.*;

public class DateTimed {
    public static void main(String[] args) {
//        LocalDate d = LocalDate.now();
        LocalDate d = LocalDate.of(1960, Month.AUGUST, 10);
        System.out.println(d);

        LocalDateTime dt = LocalDateTime.now(ZoneId.of("GMT"));
        System.out.println(dt);

//        LocalTime t = LocalTime.now();
//        LocalTime t = LocalTime.of(12,10,19,999);

//        Instant i = Instant.now();
//        System.out.println(i);

        LocalTime t = LocalTime.now(ZoneId.of("US/Pacific"));
        System.out.println(t);

//        for (String s : ZoneId.getAvailableZoneIds()) {
//            System.out.println(s);
//        }
    }
}
