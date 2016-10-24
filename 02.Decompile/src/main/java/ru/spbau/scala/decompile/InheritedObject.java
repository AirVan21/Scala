package ru.spbau.scala.decompile;

public final class InheritedObject implements Runnable {
    public static final InheritedObject item = new InheritedObject();

    @Override
    public void run() {}

    private int foo() {
        return 4;
    }
    
    private InheritedObject() {}
}
