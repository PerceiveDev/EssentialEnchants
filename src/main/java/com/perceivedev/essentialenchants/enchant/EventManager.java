package com.perceivedev.essentialenchants.enchant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import com.perceivedev.essentialenchants.EssentialEnchants;

/**
 * @author Rayzr
 *
 */
public class EventManager implements Listener {

    private List<EventExecutor> listeners = new ArrayList<>();

    private EssentialEnchants plugin;

    /**
     * @param plugin the instance of {@link EssentialEnchants}
     */
    public EventManager(EssentialEnchants plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("unchecked")
    public void add(EventExecutor executor, Class<? extends Event>... eventClasses) {
        listeners.add(executor);
        for (Class<? extends Event> eventClass : eventClasses) {
            plugin.getServer().getPluginManager().registerEvent(eventClass, this, EventPriority.NORMAL, executor, plugin);
        }
    }

}
