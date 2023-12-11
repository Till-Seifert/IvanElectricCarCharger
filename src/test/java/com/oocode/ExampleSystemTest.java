package com.oocode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @Before
    public void rememberRealSystemOut() {
        this.oldOut = System.out;
    }

    @After
    public void restoreSystemOut() {
        System.setOut(this.oldOut);
    }
}
