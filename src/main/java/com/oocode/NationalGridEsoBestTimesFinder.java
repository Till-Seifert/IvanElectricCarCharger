package com.oocode;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.StringReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.parse;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class NationalGridEsoBestTimesFinder {
    /*
"DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
"2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1333,6488,2417,15595
     */
    public List<ZonedDateTime> bestTimes(String input) throws IOException, CsvException {
        List<String[]> forecastRows = readRows(input);
        return forecastRows.stream().skip(1)
                .sorted(comparingInt(row -> -parseInt(row[4])))
                .limit(3)
                .map(row -> ZonedDateTime.of(parse(row[0])
                                .withHour(parseInt(row[1].split(":")[0]))
                                .withMinute(parseInt(row[1].split(":")[1])),
                        ZoneId.of("GMT")))
                .sorted()
                .collect(toList());
    }

    private List<String[]> readRows(String input) throws IOException, CsvException {
        List<String[]> result;
        try (CSVReader csvReader = new CSVReader(new StringReader(input))) {
            result = csvReader.readAll();
        }
        return result;
    }
}
