package com.oocode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Tests the whole "system" - including its external dependency (a National Grid ESO service).

Advantages:
    Shows whether the whole "system" works (to some extent)

Disadvantages:
    Limited in what you can assert about the output, because it depends on the result of the
        external service which is not under our control.
    Could fail due to a problem with the external service rather than our code
    Slow to run
 */

public class ExampleSystemTest {
    @Test
    public void canInterpretNationalGridDataCorrectly() throws Exception {
        var newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));

        ChargeTimes.main(new String[]{""});
        System.out.flush(); // to be sure

        assertThat(newOut.toString(), containsString("Best times to plug in"));
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
