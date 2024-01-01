package com.oocode;

import com.oocode.fakes.HardCodedHttpClient;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/*
A unit test of just parsing the response from the external service.

Advantages:
    Allows simple and direct setup and assertion of just the bit of behaviour that is relevant
    Very suitable for where there is complicated logic - a unit test like this can test a lot of permutations
    If it fails, easier to find where the problem is
    Runs very quickly

Disadvantages:
    Only tests parsing the response from the external service - that is the whole point of this sort of test
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

class NationalGridEsoDataProviderUnitTest {
    @Test
    public void canParseResponseFromExternalService() throws Exception {
        var underTest = new NationalGridEsoDataProvider(new HardCodedHttpClient(hardCodedContent), "some url - not used for this");

        assertThat(underTest.data(), equalTo(List.of(
                rowFrom(11, 30,1),
                rowFrom(12, 0, 3),
                rowFrom(12, 30, 5),
                rowFrom(13, 0, 7),
                rowFrom(13, 30, 4),
                rowFrom(14, 0, 8),
                rowFrom(14, 30, 6),
                rowFrom(15, 0, 2)
        )));
    }

    private DataProvider.Row rowFrom(int hour, int minute, int windForecast) {
        return new DataProvider.Row(
                zonedDateTimeFor(0, 0),
                LocalTime.of(hour, minute, 0),
                windForecast);
    }

    private static ZonedDateTime zonedDateTimeFor(int hour, int minute) {
        return ZonedDateTime.of(2023, 12, 11, hour, minute, 0, 0, ZoneId.of("GMT"));
    }

    private final String hardCodedContent = """
"DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
"2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1,6488,2417,15595
"2023-12-11T00:00:00","12:00","2023-12-11T00:00:00",24,3,6488,2580,15595
"2023-12-11T00:00:00","12:30","2023-12-11T00:00:00",25,5,6488,2652,15595
"2023-12-11T00:00:00","13:00","2023-12-11T00:00:00",26,7,6488,2578,15595
"2023-12-11T00:00:00","13:30","2023-12-11T00:00:00",27,4,6488,2304,15595
"2023-12-11T00:00:00","14:00","2023-12-11T00:00:00",28,8,6488,1849,15595
"2023-12-11T00:00:00","14:30","2023-12-11T00:00:00",29,6,6488,1271,15595
"2023-12-11T00:00:00","15:00","2023-12-11T00:00:00",30,2,6488,701,15595
""".trim();
}