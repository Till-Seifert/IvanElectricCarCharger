package com.oocode;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class ReportGeneratorUnitTest {
    @Test
    public void canPresentBestTimesAsReport() {
        ReportGenerator underTest = new ReportGenerator();

        var actual = underTest.reportFor(List.of(
                ZonedDateTime.of(2023, 12, 11, 11, 30, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12, 0, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12, 30, 0, 0, ZoneId.of("GMT"))));

        assertThat(actual, CoreMatchers.equalTo("""
Best times to plug in:
Mon, 11 Dec 2023 11:30:00 GMT
Mon, 11 Dec 2023 12:00:00 GMT
Mon, 11 Dec 2023 12:30:00 GMT
""".trim()));
    }
}
