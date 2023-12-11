package com.oocode;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public interface BestTimesFinder {
    /*
    "DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
    "2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1333,6488,2417,15595
         */
    List<ZonedDateTime> bestTimes() throws IOException, CsvException;
}
