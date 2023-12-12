package com.oocode;

import com.oocode.fakes.HardCodedHttpClient;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Tests a lot of the "system" which is under our control - excludes the external dependency and the format of the report.
It does this by faking the HTTP response from the external dependency and asserting on the data which the report is
based on.

Advantages compared to ExampleLayeredUnit_2a_Test:
    Separates out finding the right data from how that data is presented, which means that the presentation can
        be changed independently without having to modify this test

Disadvantages compared to ExampleLayeredUnit_2a_Test:
    Means we have to expose a method "bestTimes" which might not otherwise need to exist
    We have to have another test which checks the format of the report
 */

public class ExampleLayeredUnit_3a_Test {
    @Test
    public void canInterpretNationalGridDataCorrectly() throws Exception {
        var bestTimes = new ChargeTimes("some url - not used for this", new HardCodedHttpClient(hardCodedContent)).bestTimes();

        assertThat(bestTimes, equalTo(List.of(
                zonedDateTimeFor(13, 0),
                zonedDateTimeFor(14, 0),
                zonedDateTimeFor(14, 30)
        )));
    }

    private static ZonedDateTime zonedDateTimeFor(int hour, int minute) {
        return ZonedDateTime.of(2023, 12, 11, hour, minute, 0, 0, ZoneId.of("GMT"));
    }

    // etc - might have lots of permutations

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
