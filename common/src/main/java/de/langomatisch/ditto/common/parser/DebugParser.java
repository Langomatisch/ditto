package de.langomatisch.ditto.common.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class DebugParser<T> {

    @Getter
    private static final List<DebugParser<?>> parsers = new ArrayList<>();

    private final Class<T> tClass;
    private boolean primitive = false;

    DebugParser(Class<T> tClass, boolean primitive) {
        this.tClass = tClass;
        this.primitive = primitive;
    }

    public static <T> DebugParser<T> getParserOfType(Class<T> tClass) {
        for (DebugParser<?> parser : parsers) {
            if (parser.getTClass().equals(tClass)) {
                return (DebugParser<T>) parser;
            }
        }
        return null;
    }

    public static void registerParser(DebugParser<?> parser) {
        parsers.add(parser);
    }

    public abstract T parse(String input);

    public boolean isValidInput(String input) {
        if(isPrimitive()) {
            return true;
        }
        return input.startsWith(tClass.getName() + ":");
    }

}