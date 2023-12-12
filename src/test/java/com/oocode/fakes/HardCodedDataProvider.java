package com.oocode.fakes;

import com.oocode.NationalGridEsoDataProvider;

/*
The equivalent of this could have been implemented using a mocking framework, but I wanted to make it more
explicit what is going on
 */

public class HardCodedDataProvider implements NationalGridEsoDataProvider {
    private final String hardCodedContent;

    public HardCodedDataProvider(String hardCodedContent) {
        this.hardCodedContent = hardCodedContent;
    }

    @Override
    public String data() {
        return hardCodedContent;
    }
}
