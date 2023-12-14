package com.oocode;

import io.fusionauth.http.server.HTTPHandler;
import io.fusionauth.http.server.HTTPListenerConfiguration;
import io.fusionauth.http.server.HTTPServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Writer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleUsingHttpServerToFakeExternalServiceTest {
    @Test
    public void canReadUrl() throws Exception {
        var contents = new SimpleHttpClient().readUrl("http://localhost:8123");

        assertThat(contents, equalTo("some test data"));
    }

    private HTTPServer server;

    @BeforeEach
    public void startLocalServerPretendingToBeExternalDependency() {
        HTTPHandler handler = (req, res) -> {
            try(Writer writer = res.getWriter()) {
                writer.write("some test data");
            }
        };
        // starts a server on port 8123
        server = new HTTPServer().withHandler(handler).withListener(new HTTPListenerConfiguration(8123));
        server.start();
    }

    @AfterEach
    public void stopLocalServerPretendingToBeNationalGridEso() {
        server.close();
    }
}
