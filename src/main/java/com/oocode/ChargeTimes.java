package com.oocode;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.StringReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import static java.util.Comparator.comparingInt;

public class ChargeTimes {
    private final String url;

    public static void main(String[] args) throws Exception {
        ChargeTimes c = new ChargeTimes();
        c.generateReport();
    }

    public void generateReport() throws IOException, CsvException {
        String input = this.read_url();
        String output = this.generateOutput(input);
        System.out.println(output);
    }

    public ChargeTimes() {
        this("https://api.nationalgrideso.com/dataset/91c0c70e-0ef5-4116-b6fa-7ad084b5e0e8/resource/db6c038f-98af-4570-ab60-24d71ebd0ae5/download/embedded-forecast.csv");
    }

    public ChargeTimes(String url) {
        this.url = url;
    }

    private String read_url() throws IOException {
        return new SimpleHttpClient().readUrl(this.url);
    }

    String generateOutput(String input) throws IOException, CsvException {
    /*
"DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
"2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1333,6488,2417,15595
     */
        List<String[]> forecastRows;
        try (CSVReader csvReader = new CSVReader(new StringReader(input))) {
            forecastRows = csvReader.readAll();
        }
        Stream<String[]> output = forecastRows.stream();
        output = deleteHeader(output);
        output = sortByHighestCapacity(output);
        output = topX(output, 3);
        String strOutput = dateTimeFormatting(output);

        return ("Best times to plug in:\n" + strOutput);
    }

    private static Function<String[], ZonedDateTime> getDateTime() {
        return row -> ZonedDateTime.of(parse(row[0])
                        .withHour(parseInt(row[1].split(":")[0]))
                        .withMinute(parseInt(row[1].split(":")[1])),
                ZoneId.of("GMT"));
    }

    private static Stream<String[]> deleteHeader(Stream<String[]> inputCSV) {
        return inputCSV.skip(1);
    }

    private static Stream<String[]> sortByHighestCapacity(Stream<String[]> inputCSV) {
        return inputCSV.sorted(comparingInt(row -> -parseInt(row[4])));
    }

    private static Stream<String[]> sortByHighestSummedCapacity(Stream<String[]> inputCSV) {
        return inputCSV.sorted(comparingInt(row -> -parseInt(row[4])-parseInt(row[6])));
    }

    private static Stream<String[]> topX(Stream<String[]> inputCSV, int limit) {
        return inputCSV.limit(limit);
    }

    private static String dateTimeFormatting(Stream<String[]> inputCSV) {
        return inputCSV.map(getDateTime())
                .sorted()
                .map((zonedDateTime) -> zonedDateTime.format(RFC_1123_DATE_TIME))
                .collect(Collectors.joining("\n"));
    }


}
