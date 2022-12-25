package de.langomatisch.debugger.parser;

public class StringParser extends DebugParser<String>{

    public StringParser() {
        super(String.class, true);
    }

    @Override
    public String parse(String input) {
        // remove " at the beginning and end
        return input.substring(1, input.length() - 1);
    }

    @Override
    public boolean isValidInput(String input) {
        if(input == null) return false;
        return input.startsWith("\"") && input.endsWith("\"");
    }
}
