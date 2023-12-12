package com.oocode;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/*
A unit test of just finding the best times

Advantages:
    Allows simple and direct setup and assertion of just the bit of behaviour that is relevant
    Very suitable for where there is complicated logic - a unit test like this can test a lot of permutations
    If it fails, easier to find where the problem is
    Runs very quickly

Disadvantages:
    Only tests the report generation - that is the whole point of this sort of test
    If this test passes, it doesn't mean the system as a whole works - you need other tests for that
    If you have badly designed the API or interface of the class under test, then this sort of test could need
        changing when you want to improve the design; i.e. with badly designed code and badly designed unit tests
        the unit tests can make refactoring more difficult
 */

class NationalGridEsoBestTimesFinderUnitTest {
    @Test
    public void canFindBestTimes() throws IOException, CsvException {
        NationalGridEsoBestTimesFinder underTest = new NationalGridEsoBestTimesFinder();

        var actual = underTest.bestTimes(hardCodedContent);

        assertThat(actual, equalTo(List.of(
                zonedDateTimeFor(11, 30),
                zonedDateTimeFor(12, 0),
                zonedDateTimeFor(12, 30)
        )));
    }

    private static ZonedDateTime zonedDateTimeFor(int hour, int minute) {
        return ZonedDateTime.of(2023, 12, 11, hour, minute, 0, 0, ZoneId.of("GMT"));
    }

    // etc - might have lots of permutations

    private final String hardCodedContent = """
"DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
"2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1333,6488,2417,15595
"2023-12-11T00:00:00","12:00","2023-12-11T00:00:00",24,1283,6488,2580,15595
"2023-12-11T00:00:00","12:30","2023-12-11T00:00:00",25,1197,6488,2652,15595
"2023-12-11T00:00:00","13:00","2023-12-11T00:00:00",26,1111,6488,2578,15595
"2023-12-11T00:00:00","13:30","2023-12-11T00:00:00",27,1012,6488,2304,15595
"2023-12-11T00:00:00","14:00","2023-12-11T00:00:00",28,913,6488,1849,15595
"2023-12-11T00:00:00","14:30","2023-12-11T00:00:00",29,860,6488,1271,15595
"2023-12-11T00:00:00","15:00","2023-12-11T00:00:00",30,806,6488,701,15595
""".trim();
}