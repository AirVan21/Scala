package ru.spbau.scala.decompile;

public interface Base {
    default int defaultValueOfX() {
        return 1;
    }

    default int dependentOnXValue() {
        return defaultValueOfX() + 2;
    }

    int defaultLazyValueOfY();

    int implementedFooValue();
}
