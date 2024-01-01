package com.oocode;

import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class NationalGridEsoBestTimesFinder {
    public List<ZonedDateTime> bestTimes(List<DataProvider.Row> rows) {
        return rows.stream()
                .sorted(comparingInt(row -> -row.embeddedWindForecast()))
                .limit(3)
                .map(row ->
                        row.dateGmt()
                                .withHour(row.timeGmt().getHour())
                                .withMinute(row.timeGmt().getMinute()))
                .sorted()
                .collect(toList());
    }
}
