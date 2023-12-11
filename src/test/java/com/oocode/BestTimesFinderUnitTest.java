package com.oocode;

import com.oocode.fakes.HardCodedDataProvider;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class BestTimesFinderUnitTest {
    @Test
    public void canFindBestTimes() throws IOException, CsvException {
        BestTimesFinder underTest = new NationalGridEsoBestTimesFinder(new HardCodedDataProvider(hardCodedContent));

        var actual = underTest.bestTimes();

        assertThat(actual, equalTo(List.of(
                ZonedDateTime.of(2023, 12, 11, 11,30, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12,0, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12,30, 0, 0, ZoneId.of("GMT"))
                )));
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