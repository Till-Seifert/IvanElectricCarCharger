package com.oocode;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleUsingFileUrlTest {
    @Test
    public void canReadUrl() throws Exception {
        String urlString = new File("src/test/resources/testdata.csv").toURI().toString();

        var contents = new SimpleHttpClient().readUrl(urlString);

        assertThat(contents, equalTo("some test data"));
    }
}
