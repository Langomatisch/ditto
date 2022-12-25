package de.langomatisch.debugger.example;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Test {

    private final String name;
    private String test = "this is a test";

    public String getName() {
        return name;
    }

    public String setTest(String test) {
        this.test = test;
        return test;
    }

    public void broadcast() {
        broadcast(test);
    }

    public void broadcast(String message) {
        // just a method to test the debugger
    }

}
