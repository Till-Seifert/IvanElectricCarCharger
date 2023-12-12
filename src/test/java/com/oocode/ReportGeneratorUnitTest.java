package com.oocode;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/*
A unit test of just the report generation

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

class ReportGeneratorUnitTest {
    @Test
    public void canPresentBestTimesAsReport() {
        ReportGenerator underTest = new ReportGenerator();

        var actual = underTest.reportFor(List.of(
                ZonedDateTime.of(2023, 12, 11, 11, 30, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12, 0, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12, 30, 0, 0, ZoneId.of("GMT"))));

        assertThat(actual, CoreMatchers.equalTo("""
Best times to plug in:
Mon, 11 Dec 2023 11:30:00 GMT
Mon, 11 Dec 2023 12:00:00 GMT
Mon, 11 Dec 2023 12:30:00 GMT
""".trim()));
    }
}
