package de.langomatisch.ditto.common.parser;

public class BooleanParser extends DebugParser<Boolean> {

    public static DebugParser<Boolean> createPrimitiveParser() {
        return new DebugParser<>(Boolean.TYPE, true) {
            @Override
            public Boolean parse(String input) {
                return Boolean.parseBoolean(input);
            }
        };
    }

    public BooleanParser() {
        super(Boolean.class, true);
    }

    @Override
    public Boolean parse(String input) {
        return Boolean.parseBoolean(input);
    }
}
