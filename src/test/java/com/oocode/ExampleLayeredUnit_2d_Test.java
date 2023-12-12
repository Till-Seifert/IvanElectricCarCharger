package com.oocode;

import com.oocode.fakes.HardCodedBestTimesFinder;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
A variant of ExampleLayeredUnit_2c_Test - this time faking the bit of code which finds the best times

Advantages compared to ExampleLayeredUnit_2c_Test:
    Further encapsulation - now this is a more focused test;
        if the test fails it should be easier to tell where the problem is

Disadvantages compared to ExampleLayeredUnit_2c_Test:
    This test tests slightly less of the system; you will need other tests to make sure everything works together
 */

public class ExampleLayeredUnit_2d_Test {
    @Test
    public void canInterpretNationalGridDataCorrectly() throws Exception {
        var report = new ChargeTimes(new HardCodedBestTimesFinder(List.of(
                ZonedDateTime.of(2023, 12, 11, 11,30, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12,0, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12,30, 0, 0, ZoneId.of("GMT"))
        ))).report();

        assertThat(report, equalTo("""
Best times to plug in:
Mon, 11 Dec 2023 11:30:00 GMT
Mon, 11 Dec 2023 12:00:00 GMT
Mon, 11 Dec 2023 12:30:00 GMT
""".trim()));
    }
}
