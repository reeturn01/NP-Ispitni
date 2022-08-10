package classes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SubtitleElement {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
    private final int number;
    private final String text;
    private LocalTime startTime;
    private LocalTime endTime;

    public SubtitleElement(String subtitle) {
        String[] lines = subtitle.split(System.lineSeparator());
        this.number = Integer.parseInt(lines[0]);
        String[] time = lines[1].split(" --> ");

        this.startTime = LocalTime.parse(time[0], TIME_FORMATTER);
        this.endTime = LocalTime.parse(time[1], TIME_FORMATTER);

        this.text = Arrays.stream(lines)
                .skip(2)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void shift(int ms){
        startTime = startTime.plus(ms, ChronoUnit.MILLIS);
        endTime = endTime.plus(ms, ChronoUnit.MILLIS);
    }

    private String getTimeInFormat(LocalTime time, DateTimeFormatter formatter){
        return time.format(formatter);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(number).append(System.lineSeparator());
        stringBuilder.append(getTimeInFormat(startTime, TIME_FORMATTER)).append(" --> ").append(getTimeInFormat(endTime, TIME_FORMATTER)).append(System.lineSeparator());
        stringBuilder.append(text).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
