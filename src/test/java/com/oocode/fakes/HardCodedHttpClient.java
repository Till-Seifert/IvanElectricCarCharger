package com.oocode.fakes;

import com.oocode.HttpClient;

public class HardCodedHttpClient implements HttpClient {
    private final String hardCodedContent;

    public HardCodedHttpClient(String hardCodedContent) {
        this.hardCodedContent = hardCodedContent;
    }

    @Override
    public String readUrl(String url) {
        return hardCodedContent;
    }
}
