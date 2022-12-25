package de.langomatisch.debugger.bukkit.parser;

import de.langomatisch.debugger.parser.DebugParser;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class BukkitWorldParser extends DebugParser<World> {

    public BukkitWorldParser() {
        super(World.class);
    }

    @Override
    public World parse(String input) {
        return Bukkit.getWorld(input);
    }


}
