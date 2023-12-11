package com.oocode;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

public class ReportGenerator {
    public String reportFor(List<ZonedDateTime> zonedDateTimes) {
        return "Best times to plug in:\n" +
                zonedDateTimes.stream()
                        .map((zonedDateTime) -> zonedDateTime.format(RFC_1123_DATE_TIME))
                        .collect(Collectors.joining("\n"));
    }
}
