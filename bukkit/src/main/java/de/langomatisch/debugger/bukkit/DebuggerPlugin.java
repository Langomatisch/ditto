package de.langomatisch.debugger.bukkit;

import de.langomatisch.debugger.DebugRegistry;
import de.langomatisch.debugger.bukkit.command.DebugCommand;
import de.langomatisch.debugger.bukkit.impl.BukkitPlayerWrapper;
import de.langomatisch.debugger.bukkit.impl.BukkitWorldWrapper;
import de.langomatisch.debugger.bukkit.parser.BukkitPlayerParser;
import de.langomatisch.debugger.bukkit.parser.BukkitWorldParser;
import de.langomatisch.debugger.parser.DebugParser;
import de.langomatisch.debugger.parser.IntegerParser;
import de.langomatisch.debugger.parser.StringParser;
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
        DebugParser.registerParser(new StringParser());
        DebugParser.registerParser(new IntegerParser());
        DebugParser.registerParser(new BukkitPlayerParser());
        DebugParser.registerParser(new BukkitWorldParser());
        getCommand("debug").setExecutor(new DebugCommand());
    }

    @Override
    public void onDisable() {
    }
}