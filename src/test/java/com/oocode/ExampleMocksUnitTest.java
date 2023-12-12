package com.oocode;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/*
WARNING: I don't think this is a good test - here as an example to show how mocks are often used rather than as a
good example of using mocks.

Tests whether the building blocks of the ChargeTimes are plugged together; i.e. whether the report is generated
(by the ReportGenerator) from the best times found (by the BestTimesFinder).

Advantages:
    Independent of the real BestTimesFinder and ReportGenerator

Disadvantages:
    Difficult to see what this is really testing - probably not a useful test
    Arguably this is testing the particular implementation rather than the behaviour we want;
        this sort of test can get in the way of refactoring without adding much benefit in terms of useful testing
 */

public class ExampleMocksUnitTest {
    @Test
    public void everythingInPluggedTogether() throws Exception {
        BestTimesFinder bestTimesFinder = mock(BestTimesFinder.class);
        List<ZonedDateTime> aListOfZonedDateTimes = List.of(ZonedDateTime.now());
        given(bestTimesFinder.bestTimes()).willReturn(aListOfZonedDateTimes);
        ReportGenerator reportGenerator = mock(ReportGenerator.class);
        given(reportGenerator.reportFor(aListOfZonedDateTimes)).willReturn("some string");

        ChargeTimes underTest = new ChargeTimes(bestTimesFinder, reportGenerator);

        assertThat(underTest.report(), equalTo("some string"));
    }
}
