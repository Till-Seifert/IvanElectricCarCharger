package com.oocode;

import com.oneeyedmen.okeydoke.Approver;
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

class ReportGeneratorApprovalTest {
    @RegisterExtension
    ApprovalsExtension approvals = new ApprovalsExtension(new File("src/test/resources/approval"));

    @Test
    public void canPresentBestTimesAsReport(Approver approver) {
        ReportGenerator underTest = new ReportGenerator();

        var actual = underTest.reportFor(List.of(
                ZonedDateTime.of(2023, 12, 11, 11, 30, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12, 0, 0, 0, ZoneId.of("GMT")),
                ZonedDateTime.of(2023, 12, 11, 12, 30, 0, 0, ZoneId.of("GMT"))));

        approver.assertApproved(actual);
    }
}
