package com.oocode;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ExampleMocksUnitTest {
    @Test
    public void canInterpretNationalGridDataCorrectly() throws Exception {
        BestTimesFinder bestTimesFinder = mock(BestTimesFinder.class);
        List<ZonedDateTime> aListOfZonedDateTimes = List.of(ZonedDateTime.now());
        given(bestTimesFinder.getZonedDateTimes()).willReturn(aListOfZonedDateTimes);

        ReportGenerator reportGenerator = mock(ReportGenerator.class);
        given(reportGenerator.reportFor(aListOfZonedDateTimes)).willReturn("some string");

        var report = new ChargeTimes(bestTimesFinder, reportGenerator).report();

        assertThat(report, equalTo("some string"));
    }
}
