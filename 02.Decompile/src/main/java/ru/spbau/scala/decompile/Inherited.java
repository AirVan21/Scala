package ru.spbau.scala.decompile;


public class Inherited implements Base {
    private int xValue = Base.super.defaultValueOfX();
    private int yValue;
    private boolean wasCalculated = false;

    @Override
    public int defaultValueOfX() {
        return xValue;
    }

    @Override
    public int defaultLazyValueOfY() {
        if (!wasCalculated) {
            calculateValueOfY();
        }

        return yValue;
    }

    @Override
    public int implementedFooValue() {
        return 3;
    }

    @Override
    public int dependentOnXValue() {
        return xValue + 2;
    }

    private void calculateValueOfY() {
        wasCalculated = false;
        yValue = dependentOnXValue();
    }
}
