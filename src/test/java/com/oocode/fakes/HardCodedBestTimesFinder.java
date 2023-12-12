package com.oocode.fakes;

import com.oocode.BestTimesFinder;

import java.time.ZonedDateTime;
import java.util.List;

/*
The equivalent of this could have been implemented using a mocking framework, but I wanted to make it more
explicit what is going on
 */

public class HardCodedBestTimesFinder implements BestTimesFinder {
    private final List<ZonedDateTime> hardCodedBestTimes;

    public HardCodedBestTimesFinder(List<ZonedDateTime> hardCodedBestTimes) {
        this.hardCodedBestTimes = hardCodedBestTimes;
    }

    @Override
    public List<ZonedDateTime> bestTimes() {
        return hardCodedBestTimes;
    }
}
