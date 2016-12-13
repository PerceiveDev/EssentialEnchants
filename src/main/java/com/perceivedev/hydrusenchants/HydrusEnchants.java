package com.perceivedev.hydrusenchants;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceivedev.hydrusenchants.cmd.CommandHydrusEnchants;
import com.perceivedev.hydrusenchants.enchant.EnchantList;
import com.perceivedev.hydrusenchants.enchant.EnchantManager;
import com.perceivedev.hydrusenchants.enchant.EventManager;
import com.perceivedev.hydrusenchants.enchant.PlayerTicker;
import com.perceivedev.hydrusenchants.enchant.TickableManager;
import com.perceivedev.hydrusenchants.listeners.InventoryListener;
import com.perceivedev.hydrusenchants.util.gui.GuiListener;

public class HydrusEnchants extends JavaPlugin {

    private static HydrusEnchants instance;

    private EnchantManager enchantManager;
    private EventManager eventManager;
    private TickableManager playerTicker;
    private Language language = new Language();

    @Override
    public void onEnable() {

        instance = this;

        enchantManager = new EnchantManager(this);
        eventManager = new EventManager(this);
        playerTicker = new TickableManager(this, 30L);

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        if (!getFile("messages.yml").exists()) {
            saveResource("messages.yml", false);
        }

        language.load(getConfig("messages.yml"));

        getCommand("hyrdusce").setExecutor(new CommandHydrusEnchants());
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);

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

    /**
     * @return the {@link Language} instance
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Alias for {@link Language#tr(String, Object...)}
     * 
     * @param key
     *            the key of the message
     * @param formattingObjects
     *            the objects to format the message with
     * @return The translated message
     */
    public String tr(String key, Object... formattingObjects) {
        return language.tr(key, formattingObjects);
    }

    public File getFile(String path) {
        return new File(getDataFolder(), path.replace('/', File.pathSeparatorChar));
    }

    public YamlConfiguration getConfig(String path) {
        return YamlConfiguration.loadConfiguration(getFile(path));
    }

}
