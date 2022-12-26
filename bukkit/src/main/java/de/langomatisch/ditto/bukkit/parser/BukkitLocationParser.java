package de.langomatisch.ditto.bukkit.parser;

import de.langomatisch.ditto.common.parser.DebugParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BukkitLocationParser extends DebugParser<Location> {
    public BukkitLocationParser() {
        super(Location.class);
    }

    @Override
    public Location parse(String input) {
        // input is in the format "world,x,y,z"
        String[] split = input.split(",");
        return new Location(
                Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3])
        );
    }
}
