package de.langomatisch.debugger.parser;

public class IntegerParser extends DebugParser<Integer> {

    public IntegerParser() {
        super(Integer.class, true);
    }

    @Override
    public Integer parse(String input) {
        return Integer.parseInt(input);
    }

    @Override
    public boolean isValidInput(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
