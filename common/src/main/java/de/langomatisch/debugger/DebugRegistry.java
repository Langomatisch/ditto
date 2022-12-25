package de.langomatisch.debugger;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class DebugRegistry {

    @Getter
    private final static Map<String, DebugWrapper<?>> wrapperMap = new HashMap<>();

    public static void register(DebugWrapper<?> wrapper) {
        String simpleName = wrapper.getClass().getSimpleName();
        System.out.println("Registered " + simpleName + " test");
        wrapperMap.put(simpleName, wrapper);
    }

    public static DebugWrapper<?> get(String name) {
        return wrapperMap.get(name);
    }

}
