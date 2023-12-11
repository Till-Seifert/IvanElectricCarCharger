package com.oocode.fakes;

import com.oocode.NationalGridEsoDataProvider;

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
