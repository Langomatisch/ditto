package de.langomatisch.ditto.bukkit.impl;

import de.langomatisch.ditto.common.DebugWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BukkitPlayerWrapper extends DebugWrapper<Player> {

    public BukkitPlayerWrapper() {
        super(Player.class);
    }

    @Override
    public Player getWrapped(String input) {
        if(input.contains("-")) {
            return Bukkit.getPlayer(UUID.fromString(input));
        } else {
            return Bukkit.getPlayer(input);
        }
    }

    @Override
    public List<String> getValidInput() {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }
}
