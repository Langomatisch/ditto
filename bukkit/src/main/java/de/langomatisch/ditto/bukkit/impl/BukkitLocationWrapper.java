package de.langomatisch.ditto.bukkit.impl;

import de.langomatisch.ditto.bukkit.parser.BukkitLocationParser;
import de.langomatisch.ditto.common.DebugWrapper;
import de.langomatisch.ditto.common.parser.DebugParser;
import org.bukkit.Location;

import java.util.List;

public class BukkitLocationWrapper extends DebugWrapper<Location> {
    public BukkitLocationWrapper() {
        super(Location.class);
    }

    @Override
    public Location getWrapped(String input) {
        DebugParser<Location> parserOfType = DebugParser.getParserOfType(Location.class);
        return parserOfType.parse(input.replace("Location", ""));
    }

    @Override
    public List<String> getValidInput() {
        return null;
    }
}
