package com.oocode.fakes;

import com.oocode.HttpClient;

/*
The equivalent of this could have been implemented using a mocking framework, but I wanted to make it more
explicit what is going on
 */

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
