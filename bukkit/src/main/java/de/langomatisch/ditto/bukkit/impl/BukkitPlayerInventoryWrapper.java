package de.langomatisch.ditto.bukkit.impl;

import de.langomatisch.ditto.common.DebugWrapper;
import java.util.List;
import org.bukkit.inventory.PlayerInventory;

public class BukkitPlayerInventoryWrapper extends DebugWrapper<PlayerInventory> {

    public BukkitPlayerInventoryWrapper(Class<PlayerInventory> playerInventoryClass) {
        super(playerInventoryClass);
    }

    @Override
    public PlayerInventory getWrapped(String input) {
        return null;
    }

    @Override
    public List<String> getValidInput() {
        return null;
    }
}
