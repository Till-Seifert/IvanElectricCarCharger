package com.oocode;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public interface BestTimesFinder {
    List<ZonedDateTime> bestTimes() throws IOException, CsvException;
}
