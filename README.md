Ivan Electric Car Chargers
====

This is a collection of example tests at different levels for educational purposes.

The tests are for a simple program for finding out the best time to plug in your car to charge, using data from [National Grid ESO](https://www.nationalgrideso.com/).
It is not meant to be a useful program - it is just something for which tests can be written to show how you can write tests at different levels and what the advantages and disadvantages are of each type of test and where they might fit into a testing strategy.

If you want to try out this program, execute: `./gradlew run`

In practice, you would be unlikely to want to include tests at all of these different levels for the same bit of code.

The code has been written to be testable in the different ways which are demonstrated; you might or might not want to split it up in this way, and in particular you would be unlikely to want to be able to construct ChargeTimes in all the different ways which have been supported in order to allow tests to be written at so many different levels.

Each test has a comment describing what it is intended to illustrate.

Tests at different "levels"
----

Consider what this program (or "system") involves:

- **external service** provides data
- **reads data** from external web service
- **parses data** from external web service
- **finds best time** to plug in car from that data
- **creates report** about the best times
- **displays report** of the best times

This code combines **parses data** and **finds best time** in NationalGridEsoBestTimesFinder.
This could be split up, and probably would have been if this had been developed using TDD.
It isn't important for the purposes of this repo.

Representing this as below, the example tests cover roughly:

```text
                                        **external service** --> **reads data** --> **parses data** --> **finds best time** --> **creates report** --> **displays report**
ExampleSystemTest:                      ...................................................................................................................................
ExampleE2e_a_Test:                                            .............................................................................................................
ExampleE2e_b_Test:                                            .............................................................................................................
ExampleLayeredUnit_2a_Test:                                                      ..........................................................................................
ExampleLayeredUnit_2b_Test:                                                      ..........................................................................................
ExampleLayeredUnit_2c_Test:                                                      ....................................................................
NationalGridEsoBestTimesFinderUnitTest:                                          .............................................
ReportGeneratorUnitTest:                                                                                                     ........................
```

ExampleLayeredApprovalTest is just a minor variation of ExampleLayeredUnit_2c_Test. ReportGeneratorApprovalTest is just a minor variation of ReportGeneratorUnitTest.
They are included to show that use of approval testing tools are orthogonal to the "level" of the tests they are used for.
ExampleLayeredUnit_2a_Test and ExampleLayeredUnit_2b_Test are very similar - just showing a minor difference in "design".
