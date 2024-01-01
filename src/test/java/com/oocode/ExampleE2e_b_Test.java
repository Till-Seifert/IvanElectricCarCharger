package com.oocode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/*
A variant of ExampleE2e_a_Test - the difference being we don't need to run a real HTTP server, because we're just
reading a URL, so can use a file URL.

Advantages compared to ExampleE2e_a_Test:
    Should run a bit quicker than ExampleE2e_a_Test, and less likely to have flakiness

Disadvantages compared to ExampleE2e_a_Test:
    This approach is very limited in what it can support - but it's enough for this example code.
 */
public class ExampleE2e_b_Test {
    @Test
    public void canInterpretNationalGridDataCorrectly() throws Exception {
        var newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));

        ChargeTimes underTest = new ChargeTimes(new File("src/test/resources/testdata.csv").toURI().toString());
        underTest.printReport();
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
}
