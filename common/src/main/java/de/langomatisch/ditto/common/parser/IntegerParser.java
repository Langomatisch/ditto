package de.langomatisch.ditto.common.parser;

public class IntegerParser extends DebugParser<Integer> {
    public static DebugParser<Integer> createPrimitiveParser() {
        return new DebugParser<>(Integer.TYPE, true) {
            @Override
            public Integer parse(String input) {
                return Integer.parseInt(input);
            }
        };
    }

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
