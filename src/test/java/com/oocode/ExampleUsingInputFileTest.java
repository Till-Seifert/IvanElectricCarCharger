package com.oocode;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleUsingInputFileTest {
    @Test
    public void producesOutputForFile() throws Exception {
        String content = Files.readString(Paths.get("src/test/resources/embedded-forecast.csv"));

        ChargeTimes c = new ChargeTimes();
        String output = c.printReport(content);

        assertThat(output, equalTo("""
                Best times to plug in:
                Tue, 30 Apr 2024 12:00:00 GMT
                Tue, 30 Apr 2024 12:30:00 GMT
                Tue, 30 Apr 2024 13:00:00 GMT"""));
    }
}
