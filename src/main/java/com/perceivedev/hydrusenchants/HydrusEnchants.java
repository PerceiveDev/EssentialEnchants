package com.perceivedev.hydrusenchants;

import org.bukkit.plugin.java.JavaPlugin;

import com.perceivedev.hydrusenchants.cmd.CommandHydrusEnchants;
import com.perceivedev.hydrusenchants.enchant.EnchantList;
import com.perceivedev.hydrusenchants.enchant.EnchantManager;
import com.perceivedev.hydrusenchants.enchant.EventManager;
import com.perceivedev.hydrusenchants.enchant.PlayerTicker;
import com.perceivedev.hydrusenchants.enchant.TickableManager;
import com.perceivedev.hydrusenchants.event.InventoryListener;
import com.perceivedev.hydrusenchants.util.gui.GuiListener;

public class HydrusEnchants extends JavaPlugin {

    private static HydrusEnchants instance;

    private EnchantManager        enchantManager;
    private EventManager          eventManager;
    private TickableManager       playerTicker;

    @Override
    public void onEnable() {
        instance = this;

        enchantManager = new EnchantManager(this);
        eventManager = new EventManager(this);
        playerTicker = new TickableManager(this, 30L);

        getCommand("hyrdusce").setExecutor(new CommandHydrusEnchants());
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        // Static access so that it loads all the variables
        getLogger().info("Loading " + EnchantList.load());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static HydrusEnchants getInstance() {
        return instance;
    }

    /**
     * @return the {@link EnchantManager} instance
     */
    public EnchantManager getEnchantManager() {
        return enchantManager;
    }

    /**
     * @return the {@link EventManager} instance
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * @return the {@link PlayerTicker} instance
     */
    public TickableManager getPlayerTicker() {
        return playerTicker;
    }

}
