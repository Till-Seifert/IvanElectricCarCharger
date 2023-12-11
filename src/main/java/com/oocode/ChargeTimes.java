package com.oocode;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.parse;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class ChargeTimes {
    public static void main(String[] args) throws Exception {
        new ChargeTimes().printReport();
    }

    private final NationalGridEsoDataProvider nationalGridEsoDataProvider;
    private final ReportGenerator reportGenerator = new ReportGenerator();

    public ChargeTimes() {
        this.nationalGridEsoDataProvider = () -> new SimpleHttpClient()
                .readUrl("https://api.nationalgrideso.com/dataset/91c0c70e-0ef5-4116-b6fa-7ad084b5e0e8/resource/db6c038f-98af-4570-ab60-24d71ebd0ae5/download/embedded-forecast.csv");
    }

    public ChargeTimes(String url) {
        this.nationalGridEsoDataProvider = () -> new SimpleHttpClient().readUrl(url);
    }

    public ChargeTimes(String url, HttpClient httpClient) {
        this.nationalGridEsoDataProvider = () -> httpClient.readUrl(url);
    }

    public ChargeTimes(NationalGridEsoDataProvider nationalGridEsoDataProvider) {
        this.nationalGridEsoDataProvider = nationalGridEsoDataProvider;
    }

    void printReport() throws IOException, CsvException {
        System.out.println(report());
    }

    String report() throws IOException, CsvException {
        return reportGenerator.reportFor(getZonedDateTimes());
    }

    /*
"DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
"2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1333,6488,2417,15595
     */

    List<ZonedDateTime> getZonedDateTimes() throws IOException, CsvException {
        List<String[]> forecastRows = new CsvReader().readRows(nationalGridEsoDataProvider.data());
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
}
