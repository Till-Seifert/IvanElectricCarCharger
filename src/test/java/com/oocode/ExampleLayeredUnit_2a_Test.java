package com.oocode;

import com.oocode.fakes.HardCodedHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Tests all of the "system" which is under our control - excludes the external dependency.
It does this by faking the HTTP response from the external dependency.

Advantages:
    Shows whether most of the "system" works correctly
    Can assert about the output because everything is under our control
    Runs very quickly because does not really use HTTP and doesn't read a file
    Allows for refactoring the vast majority of the code, without having to change this test, because this test is
        testing the overall behaviour of the system rather than anything specific to the implementation
        (apart from the ability to specify the URL and HTTP client, which could have been done differently)

Disadvantages:
    Tests everything together, so if the test fails it could be difficult to work out what is wrong
    If there is part of the code which has a lot of logic, and we want to test a lot of permutations of
        inputs for it, it could be difficult to set up this sort of test (or at very least, could be difficult
        to make it neat without having a lot of distractions from the essence of what we want to test).
    Could be difficult for someone reading the test to understand the intention of the system
    Could fail due to the main method being incorrect - this test bypasses the main method
 */

public class ExampleLayeredUnit_2a_Test {
    @Test
    public void canInterpretNationalGridDataCorrectly() throws Exception {
        var newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));

        new ChargeTimes("some url - not used for this", new HardCodedHttpClient(hardCodedContent)).printReport();
        System.out.flush(); // to be sure

        assertThat(newOut.toString().trim(), equalTo("""
Best times to plug in:
Mon, 11 Dec 2023 11:30:00 GMT
Mon, 11 Dec 2023 12:00:00 GMT
Mon, 11 Dec 2023 12:30:00 GMT
""".trim()));
    }

    private PrintStream oldOut;

    @BeforeEach
    public void rememberRealSystemOut() {
        this.oldOut = System.out;
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(this.oldOut);
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
