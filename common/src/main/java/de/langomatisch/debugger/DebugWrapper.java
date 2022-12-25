package de.langomatisch.debugger;

import de.langomatisch.debugger.executable.DebugExecutable;
import de.langomatisch.debugger.executable.DebugField;
import de.langomatisch.debugger.executable.DebugMethod;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public abstract class DebugWrapper<T> {

    private final Class<T> tClass;
    private final Map<String, DebugExecutable<?>> debugExecutableMap = new HashMap<>();

    public DebugWrapper(Class<T> tClass) {
        this.tClass = tClass;
        System.out.println(tClass.getSimpleName()+" has " + tClass.getFields().length + " fields");
        for (Field field : tClass.getFields()) {
            debugExecutableMap.put(field.getName(), new DebugField<>(field));
        }
        for (Field field : tClass.getDeclaredFields()) {
            debugExecutableMap.put(field.getName(), new DebugField<>(field));
        }
        for (Method method : tClass.getMethods()) {
            handleMethod(method);
        }
        System.out.println("we got " + debugExecutableMap.size() + " debug executables");
        System.out.println("we got " + getCountOf(DebugField.class) + " fields");
        System.out.println("we got " + getCountOf(DebugMethod.class) + " methods");
    }

    private <V extends DebugExecutable<?>> int getCountOf(Class<V> exec) {
        int count = 0;
        for (DebugExecutable<?> debugExecutable : debugExecutableMap.values()) {
            if (debugExecutable.getClass().equals(exec)) {
                count++;
            }
        }
        return count;
    }

    private void handleMethod(Method method) {
        // handle methods with same name - rename them to name#firstParameterType#secondParameterType
        String name = method.getName();
        if (debugExecutableMap.containsKey(name)) {
            // rename the old method
            DebugExecutable<?> debugExecutable = debugExecutableMap.get(name);
            debugExecutableMap.remove(name);
            debugExecutableMap.put(getMethodSignature(((DebugMethod<?>) debugExecutable).getMethod()), debugExecutable);

            name = getMethodSignature(method);
        }
        debugExecutableMap.put(name, new DebugMethod<>(method));
    }

    private String getMethodSignature(Method method) {
        if (method.getParameterCount() == 0) {
            return method.getName();
        }
        return method.getName() + "#" + Arrays.stream(method.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining("#"));
    }

    public abstract T getWrapped(String input);

    public abstract List<String> getValidInput();

    public DebugExecutable<T> getExecutable(String name) {
        return (DebugExecutable<T>) debugExecutableMap.get(name);
    }

    public Object get(T t, DebugExecutable<T> debugExecutable, String... objects) {
        return debugExecutable.get(t, objects);
    }

    public Object get(String input, DebugExecutable<T> debugExecutable, String... objects) {
        return get(getWrapped(input), debugExecutable, objects);
    }

    public Object get(String input, String executable, String... objects) {
        DebugExecutable<T> executable1 = getExecutable(executable);
        if (executable1 == null) {
            return null;
        }
        return get(input, executable1, objects);
    }

    public void set(String input, String executable, String... objects) {
        DebugExecutable<T> executable1 = getExecutable(executable);
        if (!(executable1 instanceof DebugField<T>)) {
            return;
        }
        set(input, executable1, objects);
    }

    public void set(String input, DebugExecutable<T> debugExecutable, String... objects) {
        debugExecutable.set(getWrapped(input), objects);
    }

    public void set(DebugExecutable<T> debugExecutable, T t, String... values) {
        debugExecutable.set(t, values);
    }

}
