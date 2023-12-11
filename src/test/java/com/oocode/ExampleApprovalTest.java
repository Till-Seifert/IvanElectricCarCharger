package com.oocode;

import com.oneeyedmen.okeydoke.Approver;
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.File;

public class ExampleApprovalTest {
    @RegisterExtension
    ApprovalsExtension approvals = new ApprovalsExtension(new File("src/test/resources/approval"));

    @Test
    public void canInterpretNationalGridDataCorrectly(Approver approver) throws Exception {
        var report = new ChargeTimes(new HardCodedDataProvider(hardCodedContent)).report();

        approver.assertApproved(report);
    }

    public static class HardCodedDataProvider implements NationalGridEsoDataProvider {
        private final String hardCodedContent;

        public HardCodedDataProvider(String hardCodedContent) {
            this.hardCodedContent = hardCodedContent;
        }

        @Override
        public String data() {
            return hardCodedContent;
        }
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
