package com.perceivedev.hydrusenchants.enchant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.EventExecutor;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.util.EventHandler;
import com.perceivedev.hydrusenchants.util.ItemFactory;
import com.perceivedev.hydrusenchants.util.TextUtils;

/**
 * @author Rayzr
 *
 */
public abstract class Enchant implements Listener, EventExecutor {

    public static final String                        ENCHANT_BOOK_IDENTIFIER = "CUSTOM_ENCHANT_BOOK:";

    private Map<Class<? extends Event>, EventHandler> eventHandlers           = new HashMap<>();

    @SafeVarargs
    public Enchant(Class<? extends Event>... targetEvents) {
        HydrusEnchants.getInstance().getEnchantManager().registerEnchant(this);
        HydrusEnchants.getInstance().getEventManager().add(this, targetEvents);

        for (Class<? extends Event> eventClass : targetEvents) {
            eventHandlers.put(eventClass, null);
        }
    }

    /**
     * @return The internal identifier of this enchant
     */
    public abstract String getIdentifier();

    /**
     * @return The display name of this enchantment
     */
    public abstract String getDisplay();

    /**
     * @return The maximum level possible to get of this enchantment
     */
    public abstract int maxLevel();

    /**
     * @return The rarity level of this enchantment
     */
    public abstract Rarity getRarity();

    /**
     * @return A list of applicable {@link ItemType item types}
     */
    public abstract List<ItemType> getApplicableItems();

    /**
     * @param item the item to check
     * @return Whether or not this enchantment is applicable to that item
     */
    public boolean isApplicableTo(ItemStack item) {
        for (ItemType type : getApplicableItems()) {
            if (type.isValid(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param item the item to check
     * @return If the item is enchanted with this enchantment
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

    public void registerEventHandler(Class<? extends Event> eventClass, EventHandler handler) {
        Objects.requireNonNull(eventClass, "eventClass can not be null");
        Objects.requireNonNull(handler, "handler can not be null");
        if (!eventHandlers.containsKey(eventClass)) {
            HydrusEnchants.getInstance().getLogger().warning(
                    "Attempted to register EventHandler for the event '\" + eventClass.getSimpleName() + \"' in the '\" + getClass().getSimpleName() + \"' enchantment, but it was not one of the target events!");
            return;
        }
        if (eventHandlers.get(eventClass) != null) {
            HydrusEnchants.getInstance().getLogger().warning(
                    "Attempted to register EventHandler for the event '" + eventClass.getSimpleName() + "' in the '" + getClass().getSimpleName() + "' enchantment, but one was already present!");
            return;
        }
        eventHandlers.put(eventClass, handler);
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

    /**
     * @return a custom enchant book for this item
     */
    public ItemStack createBook() {
        return ItemFactory.builder(Material.ENCHANTED_BOOK)
                .setName(String.format("&2%s", getDisplay()))
                .setLore(TextUtils.hideText(String.format("%s%s", ENCHANT_BOOK_IDENTIFIER, getIdentifier())),
                        "&7Click an item to apply",
                        "&7this enchant to it.")
                .build();
    }

}
