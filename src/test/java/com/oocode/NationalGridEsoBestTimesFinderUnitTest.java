package com.oocode;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/*
A unit test of just finding the best times.

Advantages:
    Allows simple and direct setup and assertion of just the bit of behaviour that is relevant
    Very suitable for where there is complicated logic - a unit test like this can test a lot of permutations
    If it fails, easier to find where the problem is
    Runs very quickly

Disadvantages:
    Only tests the finding of the best time from all the data - that is the whole point of this sort of test
    If this test passes, it doesn't mean the system as a whole works - you need other tests for that
    If you have badly designed the API or interface of the class under test, then this sort of test could need
        changing when you want to improve the design; i.e. with badly designed code and badly designed unit tests
        the unit tests can make refactoring more difficult

Comment:
    Note the duplication of "rowFrom" and "zonedDateTimeFor" between NationalGridEsoDataProviderUnitTest and NationalGridEsoBestTimesFinderUnitTest
    This is because one use of that code is for creating the expectation of what should be returned by
    NationalGridEsoDataProvider and the other is for creating the input into NationalGridEsoBestTimesFinder.
    There is a tension between the benefits of the separation of concerns vs the coupling that has been introduced.
 */

class NationalGridEsoBestTimesFinderUnitTest {
    @Test
    public void canFindBestTimes() throws Exception {
        NationalGridEsoBestTimesFinder underTest = new NationalGridEsoBestTimesFinder();

        var actual = underTest.bestTimes(List.of(
                rowFrom(11, 30,1),
                rowFrom(12, 0, 3),
                rowFrom(12, 30, 5),
                rowFrom(13, 0, 7),
                rowFrom(13, 30, 4),
                rowFrom(14, 0, 8),
                rowFrom(14, 30, 6),
                rowFrom(15, 0, 2)
        ));

        assertThat(actual, equalTo(List.of(
                zonedDateTimeFor(13, 0),
                zonedDateTimeFor(14, 0),
                zonedDateTimeFor(14, 30)
        )));
    }

    // etc - might have lots of permutations

    private DataProvider.Row rowFrom(int hour, int minute, int windForecast) {
        return new DataProvider.Row(
                zonedDateTimeFor(0, 0),
                LocalTime.of(hour, minute, 0),
                windForecast);
    }

    private static ZonedDateTime zonedDateTimeFor(int hour, int minute) {
        return ZonedDateTime.of(2023, 12, 11, hour, minute, 0, 0, ZoneId.of("GMT"));
    }
}
