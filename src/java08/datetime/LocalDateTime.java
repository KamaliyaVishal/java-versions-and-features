package java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime is a class in the java.time package that represents a date-time with a date and time without a timezone.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using {@code LocalDate} to represent a date without a time.</li>
 *   <li>Using {@code LocalTime} to represent a time without a date.</li>
 *   <li>Using {@code LocalDateTime} to represent a date-time with a date and time without a timezone.</li>
 *   <li>Using {@code ZonedDateTime} to represent a date-time with a date and time with a timezone.</li>
 *   <li>Using {@code Instant} to represent a point in time in the UTC timezone.</li>
 *   <li>Using {@code Duration} to represent a time-based amount of time.</li>
 *   <li>Using {@code Period} to represent a date-based amount of time.</li>
 *   <li>Using {@code DateTimeFormatter} to format and parse date-time objects.</li>
 * </ul>
 */
public class LocalDateTime {
    public static void main(String[] args) {
        // LocalDate basic usage
        LocalDate today = LocalDate.now();
        LocalDate customDate = LocalDate.of(1999, 2, 18);
        System.out.println("today: " + today);
        System.out.println("customDate: " + customDate);
        System.out.println("yesterday: " + today.minusDays(1));
        System.out.println("tomorrow: " + today.plusDays(1));
        System.out.println("firstDayOfMonth: " + today.withDayOfMonth(1));
        System.out.println("lastDayOfMonth: " + today.withDayOfMonth(today.lengthOfMonth()));
        // compare
        System.out.println("date compare: " + !today.equals(today.minusDays(1)));

        // LocalTime basic usage
        LocalTime localTime = LocalTime.now();
        LocalTime customTime = LocalTime.of(12, 30, 45);
        System.out.println("\nlocalTime: " + localTime);
        System.out.println("customTime: " + customTime);
        System.out.println("nextHour: " + localTime.plusHours(1));
        System.out.println("nextSecond: " + localTime.plusSeconds(1));
        System.out.println("time compare: " + localTime.isAfter(customTime));

        // LocalDateTime
        java.time.LocalDateTime localDateTime = java.time.LocalDateTime.now();
        java.time.LocalDateTime customDateTime = java.time.LocalDateTime.of(1999, 6, 18, 12, 30, 45);
        System.out.println("\nlocalDateTime: " + localDateTime);
        System.out.println("customDateTime: " + customDateTime);

        // ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime utcZoned = ZonedDateTime.of(1999, 6, 18, 12, 30, 45, 0, ZoneId.of("UTC"));
        System.out.println("\nzonedDateTime: " + zonedDateTime);
        System.out.println("UTC zoned: " + utcZoned);

        // Instant
        Instant instant = Instant.now();
        Instant customInstant = Instant.ofEpochSecond(1719859200);
        System.out.println("\ninstant: " + instant);
        System.out.println("customInstant: " + customInstant);

        // Duration (time-based)
        Duration duration = Duration.between(LocalTime.MIN, LocalTime.NOON);
        System.out.println("\nduration: " + duration);
        System.out.println("duration 1 second: " + Duration.ofSeconds(1));

        // Period (date-based)
        Period period = Period.between(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1));
        System.out.println("\nperiod: " + period);
        System.out.println("period 1 year: " + Period.ofYears(1));

        // DateTimeFormatter
        System.out.println();
        System.out.println("ISO_LOCAL_DATE: " + today.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_TIME: " + localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("ISO_LOCAL_DATE_TIME: " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("RFC_1123_DATE_TIME: " + zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        System.out.println("Custom: " + localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        // Parsing with custom pattern
        String input = "27/05/2024 15:30:45";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        java.time.LocalDateTime parsed = java.time.LocalDateTime.parse(input, fmt);
        System.out.println("Parsed: " + parsed);
    }
}
