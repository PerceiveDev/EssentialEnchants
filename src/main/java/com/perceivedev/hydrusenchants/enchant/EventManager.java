package com.perceivedev.hydrusenchants.enchant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import com.perceivedev.hydrusenchants.HydrusEnchants;

/**
 * @author Rayzr
 *
 */
public class EventManager implements Listener {

    private List<EventExecutor> listeners = new ArrayList<>();

    private HydrusEnchants      plugin;

    /**
     * @param plugin the instance of {@link HydrusEnchants}
     */
    public EventManager(HydrusEnchants plugin) {
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
