package de.langomatisch.ditto.bukkit;

import de.langomatisch.ditto.bukkit.command.DebugCommand;
import de.langomatisch.ditto.bukkit.impl.BukkitLocationWrapper;
import de.langomatisch.ditto.bukkit.impl.BukkitPlayerWrapper;
import de.langomatisch.ditto.bukkit.impl.BukkitWorldWrapper;
import de.langomatisch.ditto.bukkit.parser.BukkitLocationParser;
import de.langomatisch.ditto.bukkit.parser.BukkitPlayerParser;
import de.langomatisch.ditto.bukkit.parser.BukkitWorldParser;
import de.langomatisch.ditto.common.DebugRegistry;
import de.langomatisch.ditto.common.parser.BooleanParser;
import de.langomatisch.ditto.common.parser.DebugParser;
import de.langomatisch.ditto.common.parser.IntegerParser;
import de.langomatisch.ditto.common.parser.StringParser;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

@Plugin(name = "Debugger", version = "1.0")
@Commands({
        @Command(name = "debug", permission = "debugger.debug")
})
public class DebuggerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        DebugRegistry.register(new BukkitPlayerWrapper());
        DebugRegistry.register(new BukkitWorldWrapper());
        DebugRegistry.register(new BukkitLocationWrapper());
        DebugParser.registerParser(new StringParser());
        DebugParser.registerParser(new IntegerParser());
        DebugParser.registerParser(IntegerParser.createPrimitiveParser());
        DebugParser.registerParser(new BooleanParser());
        DebugParser.registerParser(BooleanParser.createPrimitiveParser());
        DebugParser.registerParser(new BukkitPlayerParser());
        DebugParser.registerParser(new BukkitWorldParser());
        DebugParser.registerParser(new BukkitLocationParser());
        getCommand("debug").setExecutor(new DebugCommand());
    }

    @Override
    public void onDisable() {
    }
}