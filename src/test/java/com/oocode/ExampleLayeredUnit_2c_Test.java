package com.oocode;

import com.oocode.fakes.HardCodedBestTimesFinder;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleLayeredUnit_2c_Test {
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
