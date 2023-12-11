package com.oocode;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public class ChargeTimes {
    public static void main(String[] args) throws Exception {
        new ChargeTimes().printReport();
    }

    private final BestTimesFinder bestTimesFinder;
    private final ReportGenerator reportGenerator = new ReportGenerator();

    public ChargeTimes() {
        bestTimesFinder = new BestTimesFinder(() -> new SimpleHttpClient()
                .readUrl("https://api.nationalgrideso.com/dataset/91c0c70e-0ef5-4116-b6fa-7ad084b5e0e8/resource/db6c038f-98af-4570-ab60-24d71ebd0ae5/download/embedded-forecast.csv"));
    }

    public ChargeTimes(String url) {
        bestTimesFinder = new BestTimesFinder(() -> new SimpleHttpClient().readUrl(url));
    }

    public ChargeTimes(String url, HttpClient httpClient) {
        bestTimesFinder = new BestTimesFinder(() -> httpClient.readUrl(url));
    }

    public ChargeTimes(NationalGridEsoDataProvider nationalGridEsoDataProvider) {
        bestTimesFinder = new BestTimesFinder(nationalGridEsoDataProvider);
    }

    void printReport() throws IOException, CsvException {
        System.out.println(report());
    }

    String report() throws IOException, CsvException {
        return reportGenerator.reportFor(bestTimesFinder.getZonedDateTimes());
    }
}
