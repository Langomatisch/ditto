package de.langomatisch.ditto.common.executable;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;

@RequiredArgsConstructor
public class DebugField<T> extends DebugExecutable<T> {

    private final Field field;

    @Override
    public Object get(T t, String... objects) {
        // FIXME: private fields are not accessible by default - make them accessible
        try {
            return field.get(t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void set(T t, String... objects) {
        try {
            field.set(t, objects[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
