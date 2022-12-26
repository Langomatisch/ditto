package de.langomatisch.ditto.common.executable;

public abstract class DebugExecutable<T> {

    public abstract Object get(T t, String... objects);
    public abstract void set(T t, String... objects);

}
