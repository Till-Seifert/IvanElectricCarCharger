package com.oocode;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ExampleUnitTest {
    @Test
    public void canAssertSomething() {
        assertThat(2 + 2, equalTo(4));
    }
}
