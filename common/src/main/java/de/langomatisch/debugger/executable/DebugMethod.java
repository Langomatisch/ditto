package de.langomatisch.debugger.executable;

import de.langomatisch.debugger.parser.DebugParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public class DebugMethod<T> extends DebugExecutable<T> {

    private final Method method;

    @Override
    public Object get(T t, String... objects) {
        if (method.getParameterCount() != objects.length) {
            throw new IllegalArgumentException("Method " + method.getName() + " has " + method.getParameterCount() + " parameters, but " + objects.length + " were given");
        }
        Object[] parsedObjects = new Object[objects.length];
        Class<?>[] parameterTypes = method.getParameterTypes();
        int index = 0;
        for (Class<?> parameterType : parameterTypes) {
            DebugParser<?> parserOfType = DebugParser.getParserOfType(parameterType);
            if(parserOfType == null) {
                throw new IllegalArgumentException("No parser for type " + parameterType.getName());
            }
            if(!parserOfType.isValidInput(objects[index])) {
                throw new IllegalArgumentException("Invalid input for type " + parameterType.getName());
            }
            String input = objects[index];
            // remove ClassName: from the beginning if it is not a primitive
            if(!parserOfType.isPrimitive()) {
                input = input.substring(input.indexOf(":") + 1);
            }
            Object parsed = parserOfType.parse(input);
            parsedObjects[index] = parsed;
        }
        try {
            Object invoke = method.invoke(t, parsedObjects);
            if(method.getReturnType().equals(Void.TYPE)) {
                return new DebugVoid();
            }
            return invoke;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void set(T t, String... objects) {
        throw new RuntimeException("Cannot set a method");
    }


}
