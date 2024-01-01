package com.oocode;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.parse;
import static java.util.stream.Collectors.toList;

public class NationalGridEsoDataProvider implements DataProvider {
    private final HttpClient httpClient;
    private final String url;

    public NationalGridEsoDataProvider(HttpClient httpClient, String url) {
        this.httpClient = httpClient;
        this.url = url;
    }

    /*
"DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
"2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1333,6488,2417,15595
 */
    @Override
    public List<Row> data() throws IOException {
        return readRows(httpClient.readUrl(url)).stream()
                .skip(1)
                .map(row -> new Row(
                        ZonedDateTime.of(parse(row[0]), ZoneId.of("GMT")),
                        LocalTime.parse(row[1]),
                        parseInt(row[4])))
                .collect(toList());
    }

    private List<String[]> readRows(String input) throws IOException {
        try {
            try (CSVReader csvReader = new CSVReader(new StringReader(input))) {
                return csvReader.readAll();
            }
        } catch (CsvException e) {
            throw new IOException(e);
        }
    }
}
