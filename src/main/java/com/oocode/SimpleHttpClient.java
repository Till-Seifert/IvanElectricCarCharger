package com.oocode;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleHttpClient implements HttpClient {
    @Override
    public String readUrl(String url) throws IOException {
        try (Scanner scanner = new Scanner(new URL(url).openStream(), UTF_8)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
