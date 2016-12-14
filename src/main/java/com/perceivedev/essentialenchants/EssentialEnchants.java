package com.perceivedev.essentialenchants;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceivedev.essentialenchants.cmd.CommandHydrusEnchants;
import com.perceivedev.essentialenchants.enchant.EnchantList;
import com.perceivedev.essentialenchants.enchant.EnchantManager;
import com.perceivedev.essentialenchants.enchant.EventManager;
import com.perceivedev.essentialenchants.enchant.PlayerTicker;
import com.perceivedev.essentialenchants.enchant.TickableManager;
import com.perceivedev.essentialenchants.listeners.InventoryListener;
import com.perceivedev.essentialenchants.util.gui.GuiListener;

public class EssentialEnchants extends JavaPlugin {

    private static EssentialEnchants instance;

    private EnchantManager enchantManager;
    private EventManager eventManager;
    private TickableManager playerTicker;
    private Language language = new Language();

    @Override
    public void onEnable() {

        instance = this;

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        if (!getFile("messages.yml").exists()) {
            saveResource("messages.yml", false);
        }

        if (!getFile("enchants.yml").exists()) {
            saveResource("enchants.yml", false);
        }

        enchantManager = new EnchantManager(this);
        eventManager = new EventManager(this);
        playerTicker = new TickableManager(this, 30L);

        language.load(getConfig("messages.yml"));

        getCommand("essentialenchants").setExecutor(new CommandHydrusEnchants(this));
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);

        // Static access so that it loads all the variables
        EnchantList.load();

        enchantManager.load();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static EssentialEnchants getInstance() {
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
