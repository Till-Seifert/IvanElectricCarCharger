package com.oocode;

import java.io.IOException;

public interface HttpClient {
    String readUrl(String url) throws IOException;
}
