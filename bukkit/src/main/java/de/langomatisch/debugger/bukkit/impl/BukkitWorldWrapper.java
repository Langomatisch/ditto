package de.langomatisch.debugger.bukkit.impl;

import de.langomatisch.debugger.DebugWrapper;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;
import java.util.stream.Collectors;

public class BukkitWorldWrapper extends DebugWrapper<World> {

    public BukkitWorldWrapper() {
        super(World.class);
    }

    @Override
    public World getWrapped(String input) {
        return Bukkit.getWorld(input);
    }

    @Override
    public List<String> getValidInput() {
        return Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList());
    }

}
