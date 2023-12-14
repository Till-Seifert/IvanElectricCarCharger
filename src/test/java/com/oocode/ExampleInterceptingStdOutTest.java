package com.oocode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleInterceptingStdOutTest {
    @Test
    public void canInterceptStdOut() throws Exception {
        var newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));

        System.out.print("Some text printed to stdout by the system under test");
        System.out.flush(); // to be sure

        assertThat(newOut.toString(), equalTo("Some text printed to stdout by the system under test"));
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
