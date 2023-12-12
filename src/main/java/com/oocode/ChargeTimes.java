package com.oocode;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public class ChargeTimes {
    public static void main(String[] args) throws Exception {
        new ChargeTimes().printReport();
    }

    private final BestTimesFinder bestTimesFinder;
    private final ReportGenerator reportGenerator;

    public ChargeTimes() {
        this(() -> new SimpleHttpClient()
                .readUrl("https://api.nationalgrideso.com/dataset/91c0c70e-0ef5-4116-b6fa-7ad084b5e0e8/resource/db6c038f-98af-4570-ab60-24d71ebd0ae5/download/embedded-forecast.csv"));
    }

    public ChargeTimes(String url) {
        this(() -> new SimpleHttpClient().readUrl(url));
    }

    public ChargeTimes(String url, HttpClient httpClient) {
        this(() -> httpClient.readUrl(url));
    }

    public ChargeTimes(NationalGridEsoDataProvider nationalGridEsoDataProvider) {
        this(() -> new NationalGridEsoBestTimesFinder().bestTimes(nationalGridEsoDataProvider.data()));
    }

    public ChargeTimes(BestTimesFinder bestTimesFinder) {
        this.bestTimesFinder = bestTimesFinder;
        reportGenerator = new ReportGenerator();
    }

    public ChargeTimes(BestTimesFinder bestTimesFinder, ReportGenerator reportGenerator) {
        this.bestTimesFinder = bestTimesFinder;
        this.reportGenerator = reportGenerator;
    }

    void printReport() throws IOException, CsvException {
        System.out.println(report());
    }

    String report() throws IOException, CsvException {
        return reportGenerator.reportFor(bestTimes());
    }

    List<ZonedDateTime> bestTimes() throws IOException, CsvException {
        return bestTimesFinder.bestTimes();
    }
}
