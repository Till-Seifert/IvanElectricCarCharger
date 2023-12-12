package com.oocode;

import com.oneeyedmen.okeydoke.Approver;
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension;
import com.oocode.fakes.HardCodedDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.File;

/*
A variant of ExampleLayeredUnit_2c_Test, using an approval testing tool instead of String comparison for the assertion

Advantages compared to ExampleLayeredUnit_2c_Test:
    Easier to see what the difference is in the report if it changes
    Possibly easier to see what an example report looks like (certainly if using a version of Java which does
        not support multi-line strings).
    Easier to read if the report is very long

Disadvantages compared to ExampleLayeredUnit_2c_Test:
    The expectation of what the report should look like is not directly in the test, but in an external file
 */

public class ExampleLayeredApprovalTest {
    @RegisterExtension
    ApprovalsExtension approvals = new ApprovalsExtension(new File("src/test/resources/approval"));

    @Test
    public void canInterpretNationalGridDataCorrectly(Approver approver) throws Exception {
        var report = new ChargeTimes(new HardCodedDataProvider(hardCodedContent)).report();

        approver.assertApproved(report);
    }

    private final String hardCodedContent = """
"DATE_GMT","TIME_GMT","SETTLEMENT_DATE","SETTLEMENT_PERIOD","EMBEDDED_WIND_FORECAST","EMBEDDED_WIND_CAPACITY","EMBEDDED_SOLAR_FORECAST","EMBEDDED_SOLAR_CAPACITY"
"2023-12-11T00:00:00","11:30","2023-12-11T00:00:00",23,1333,6488,2417,15595
"2023-12-11T00:00:00","12:00","2023-12-11T00:00:00",24,1283,6488,2580,15595
"2023-12-11T00:00:00","12:30","2023-12-11T00:00:00",25,1197,6488,2652,15595
"2023-12-11T00:00:00","13:00","2023-12-11T00:00:00",26,1111,6488,2578,15595
"2023-12-11T00:00:00","13:30","2023-12-11T00:00:00",27,1012,6488,2304,15595
"2023-12-11T00:00:00","14:00","2023-12-11T00:00:00",28,913,6488,1849,15595
"2023-12-11T00:00:00","14:30","2023-12-11T00:00:00",29,860,6488,1271,15595
"2023-12-11T00:00:00","15:00","2023-12-11T00:00:00",30,806,6488,701,15595
""".trim();
}
