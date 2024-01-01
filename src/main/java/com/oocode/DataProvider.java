package com.oocode;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface DataProvider {
    record Row(ZonedDateTime dateGmt, LocalTime timeGmt, int embeddedWindForecast) {}

    List<Row> data() throws IOException;
}
