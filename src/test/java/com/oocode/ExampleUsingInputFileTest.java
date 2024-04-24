package com.oocode;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleUsingInputFileTest {
    @Test
    public void producesOutputForFile() throws Exception {
        testFileInput("src/test/resources/embedded-forecast_0solar.csv", """
                Best times to plug in:
                Tue, 30 Apr 2024 12:00:00 GMT
                Tue, 30 Apr 2024 12:30:00 GMT
                Tue, 30 Apr 2024 13:00:00 GMT""");

        testFileInput("src/test/resources/embedded-forecast_short.csv", """
                Best times to plug in:
                Wed, 24 Apr 2024 08:30:00 GMT
                Wed, 24 Apr 2024 09:00:00 GMT
                Wed, 24 Apr 2024 09:30:00 GMT""");

        testFileInput("src/test/resources/embedded-forecast.csv", """
                Best times to plug in:
                Sun, 5 May 2024 12:00:00 GMT
                Sun, 5 May 2024 12:30:00 GMT
                Sun, 5 May 2024 13:00:00 GMT""");

        testFileInput("src/test/resources/embedded-forecast_duplicate.csv", """
                Best times to plug in:
                Wed, 24 Apr 2024 08:30:00 GMT
                Wed, 24 Apr 2024 09:00:00 GMT
                Wed, 24 Apr 2024 09:30:00 GMT""");
    }

    public void testFileInput(String filename, String expectedOutput) throws IOException, CsvException {
        String content = Files.readString(Paths.get(filename));

        ChargeTimes c = new ChargeTimes();
        String output = c.generateOutput(content);

        assertThat(output, equalTo(expectedOutput));
    }
}
