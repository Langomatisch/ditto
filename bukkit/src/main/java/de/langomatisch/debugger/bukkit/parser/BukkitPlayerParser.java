package de.langomatisch.debugger.bukkit.parser;

import de.langomatisch.debugger.parser.DebugParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitPlayerParser extends DebugParser<Player> {

    public BukkitPlayerParser() {
        super(Player.class);
    }

    @Override
    public Player parse(String input) {
        return Bukkit.getPlayer(input);
    }

}
