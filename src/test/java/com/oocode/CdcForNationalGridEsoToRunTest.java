package com.oocode;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/*
A minimal CDC (Consumer Driven Contract) which you could somehow give to NationalGridEso to run, so they can tell whether they are giving us a response we understand.

Advantages:
    Allows a team outside ours to know whether a change they have made is compatible with our use of their service
        this enables the other team to modify their services knowing that they haven't broken consumers
    We can run this test ourselves to give early warning that there is a problem with the external service
        - it is more useful for this purpose than ExampleSystemTest because it involves less of our code
    When used well, enables safe evolution of a "producer" service, possibly without even needing API versioning, leading to less code and simpler services

Disadvantages:
    CDCs are only useful where there is a known set of consumers rather than an API open to everyone
        and also consumers and producer developers need to be able to work very collaboratively - usually this is only practical within a single organization
    CDCs have to strictly restrict tests to only the things that consumers care about, otherwise they prevent "producers" making changes
    Can be difficult to agree a mechanism for an external team to run such tests
        - ideally we want a mechanism to use with a release candidate, so they can prevent deploying a version of the service which causes problems
        - can require collaboration when CDCs unexpectedly fail
    Can be difficult to assert much, unless producer provides extra mechanisms for specifying test data

Comment:
    This is included only as a learning point - in reality, the service we use is available to everyone so there is no chance of using a CDC approach in this specific case.
    There are lots of different ways of doing something similar to this. e.g. see
    https://docs.pact.io/
    https://pactflow.io/blog/contract-testing-using-json-schemas-and-open-api-part-1/
 */

class CdcForNationalGridEsoToRunTest {
    @Test
    public void canParseResponseFromExternalService() throws Exception {
        String urlOfNationalGridEsoReleaseCandidate = "https://api.nationalgrideso.com/dataset/91c0c70e-0ef5-4116-b6fa-7ad084b5e0e8/resource/db6c038f-98af-4570-ab60-24d71ebd0ae5/download/embedded-forecast.csv";
        var underTest = new NationalGridEsoDataProvider(new SimpleHttpClient(), urlOfNationalGridEsoReleaseCandidate);

        // we can't assert much - just that we get something and the parsing doesn't blow up
        assertThat(underTest.data().size(), greaterThan(0));
    }
}
