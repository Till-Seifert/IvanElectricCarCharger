package com.oocode;

import com.oocode.fakes.HardCodedHttpClient;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
A minor variant of ExampleLayeredUnit_2b_Test - the difference being here we are not testing that the
report is written to "System.out", but just directly testing the report String.

Advantages compared to ExampleLayeredUnit_2a_Test:
    Makes the test easier to read an write - less extraneous noise

Disadvantages compared to ExampleLayeredUnit_2a_Test:
    Means we have to expose a method "report" which might not otherwise need to exist
 */

public class ExampleLayeredUnit_2c_Test {
    @Test
    public void canInterpretNationalGridDataCorrectly() throws Exception {
        var report = new ChargeTimes("some url - not used for this", new HardCodedHttpClient(hardCodedContent)).report();

        assertThat(report, equalTo("""
Best times to plug in:
Mon, 11 Dec 2023 11:30:00 GMT
Mon, 11 Dec 2023 12:00:00 GMT
Mon, 11 Dec 2023 12:30:00 GMT
""".trim()));
    }

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
