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
Tests all of the "system" which is under our control - excludes the external dependency.
It does this by using uri for a file instead of an HTTP url.

Advantages:
    Shows whether that part of the "system" which is under our control, works correctly
    Can assert about the output because everything is under our control
    Allows for refactoring without having to change this test, because this test is testing the overall
        behaviour of the system rather than anything specific to the implementation (apart from the ability to
        specify the URL, which could have been done differently)

Disadvantages:
    Slightly slower to run than it could be because it is reading a file.
    Tests everything together, so if the test fails it could be difficult to work out what is wrong
    If there is part of the code which has a lot of logic, and we want to test a lot of permutations of
        inputs for it, it could be difficult to set up this sort of test (or at very least, could be difficult
        to make it neat without having a lot of distractions from the essence of what we want to test).
    Could be difficult for someone reading the test to understand the intention of the system
    Could fail due to a problem with the file system which we use to mimic the real external dependency, rather
        than our code
    Could fail due to the main method being incorrect - this test bypasses the main method
    Does not test whether the configuration of the real system is correct, i.e. the test tells the code what URL
        to use (which corresponds to the file we are using to mimic the real external dependency).
        The configuration in the production code could be incorrect, which would mean that even if this tests passes,
        the real system could be broken.
 */
public class ExampleE2eTest {
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
