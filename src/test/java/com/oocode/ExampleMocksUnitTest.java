package com.oocode;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExampleMocksUnitTest {
    @Test
    public void tellsDependencyToDoSomething() throws Exception {
        SomeDependency someDependency = mock(SomeDependency.class);

        var underTest = new ClassWhichHasDependency(someDependency);
        underTest.doYourThing();

        verify(someDependency).someCommand();
    }

    public static class ClassWhichHasDependency {
        private final SomeDependency someDependency;

        public ClassWhichHasDependency(SomeDependency someDependency) {
            this.someDependency = someDependency;
        }

        public void doYourThing() {
            someDependency.someCommand();
        }
    }

    public interface SomeDependency {
        void someCommand();
    }
}
