package com.oocode;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class CsvReader {
    public List<String[]> readRows(String input) throws IOException, CsvException {
        List<String[]> result;
        try (CSVReader csvReader = new CSVReader(new StringReader(input))) {
            result = csvReader.readAll();
        }
        return result;
    }
}
