package com.perceivedev.hydrusenchants.enchant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.EventExecutor;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.util.EventHandler;

/**
 * @author Rayzr
 *
 */
public abstract class Enchant implements Listener, EventExecutor {

    private Map<Class<? extends Event>, EventHandler> eventHandlers = new HashMap<>();

    @SafeVarargs
    public Enchant(Class<? extends Event>... targetEvents) {
        HydrusEnchants.getInstance().getEventManager().add(this, targetEvents);

        // TODO: Allow adding event handlers
        for (Class<? extends Event> eventClass : targetEvents) {
            eventHandlers.put(eventClass, null);
        }
    }

    /**
     * @return The internal identifier of this enchant
     */
    public abstract String getIdentifier();

    /**
     * @return The display name of this enchant
     */
    public abstract String getDisplay();

    /**
     * @return The maximum level possible to get of this enchant
     */
    public abstract int maxLevel();

    /**
     * @return A list of applicable {@link ItemType item types}
     */
    public abstract List<ItemType> getApplicableItems();

    /**
     * @param item the item to check
     * @return If the item is enchanted with this enchant
     */
    public boolean isEnchanted(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return false;
        }

        if (!item.getItemMeta().hasLore()) {
            return false;
        }

        Enchants enchants = Enchants.load(item.getItemMeta().getLore());

        return enchants.get(this) != -1;
    }

    @Override
    public void execute(Listener listener, Event event) throws EventException {
        Class<?> clazz = event.getClass();
        if (!eventHandlers.containsKey(clazz)) {
            // Shouldn't ever happen, but might happen nonetheless
            return;
        }

        if (eventHandlers.get(clazz) == null) {
            HydrusEnchants.getInstance().getLogger()
                    .warning("The enchant '" + getIdentifier() + "' has the event '" + clazz.getSimpleName() + "' listed as a target class, but hasn't set up an event handler for it yet!");
            return;
        }

        EventHandler eventHandler = eventHandlers.get(clazz);
        eventHandler.listen(event);
    }
}
