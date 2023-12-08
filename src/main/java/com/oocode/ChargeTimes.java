package com.oocode;

import com.opencsv.CSVReader;

import java.io.StringReader;
import java.net.URL;
import java.time.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.LocalDateTime.parse;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class ChargeTimes {
    public static List<ZonedDateTime> goodTimes(String url, Duration duration) throws Exception {
        String result1;
        List<String[]> result;
        try (Scanner scanner = new Scanner(new URL(url).openStream(), UTF_8)) {
            scanner.useDelimiter("\\A");
            result1 = scanner.hasNext() ? scanner.next() : "";
        }
        try (CSVReader csvReader = new CSVReader(new StringReader(result1))) {
            result = csvReader.readAll();
        }
        return result.stream().skip(1)
                .sorted(comparingInt(row -> -parseInt(row[4])))
                .limit(3)
                .map(row -> ZonedDateTime.of(parse(row[0])
                                .withHour(parseInt(row[1].split(":")[0]))
                                .withMinute(parseInt(row[1].split(":")[1])),
                        ZoneId.of("GMT")))
                .sorted()
                .collect(toList());
    }

    public static void main(String[] args) throws Exception {
        List<ZonedDateTime> when = goodTimes(
                "https://api.nationalgrideso.com/dataset/" +
                        "91c0c70e-0ef5-4116-b6fa-7ad084b5e0e8" +
                        "/resource/" +
                        "db6c038f-98af-4570-ab60-24d71ebd0ae5" +
                        "/download/embedded-forecast.csv",
                Duration.ofDays(3));
        System.out.println("when = " + when);
    }
}
