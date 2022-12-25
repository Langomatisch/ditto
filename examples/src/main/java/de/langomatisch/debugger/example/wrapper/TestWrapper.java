package de.langomatisch.debugger.example.wrapper;


import de.langomatisch.debugger.DebugWrapper;
import de.langomatisch.debugger.example.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Extend the DebugWrapper class with your own class
public class TestWrapper extends DebugWrapper<Test> {

    // Create a constructor with the same parameters as the super class
    public TestWrapper() {
        super(Test.class);
    }

    private final Map<String, Test> cache = new HashMap<>();

    // Override the getWrapped method to return the object you want to debug
    @Override
    public Test getWrapped(String input) {
        if(cache.containsKey(input)) {
            return cache.get(input);
        }
        Test value = new Test(input);
        cache.put(input, value);
        return value;
    }

    // Override the getWrappedList method to return a list of all object instances you want to debug
    @Override
    public List<String> getValidInput() {
        return new ArrayList<>(cache.keySet());
    }
}
