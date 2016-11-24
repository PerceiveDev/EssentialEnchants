package com.perceivedev.hydrusenchants;

import org.bukkit.plugin.java.JavaPlugin;

import com.perceivedev.hydrusenchants.enchant.EnchantManager;
import com.perceivedev.hydrusenchants.enchant.EventManager;

public class HydrusEnchants extends JavaPlugin {

    private static HydrusEnchants instance;

    private EnchantManager        enchantManager;
    private EventManager          eventManager;

    @Override
    public void onEnable() {
        instance = this;

        enchantManager = new EnchantManager(this);
        eventManager = new EventManager(this);
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

}
