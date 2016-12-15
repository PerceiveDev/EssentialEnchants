package com.perceivedev.essentialenchants.enchant.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.EventExecutor;

import com.perceivedev.essentialenchants.EssentialEnchants;
import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Enchants;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.util.EventHandler;
import com.perceivedev.essentialenchants.util.ItemFactory;
import com.perceivedev.essentialenchants.util.TextUtils;

/**
 * @author Rayzr
 *
 */
public abstract class Enchant implements EventExecutor {

    public static final String ENCHANT_BOOK_IDENTIFIER = "CUSTOM_ENCHANT_BOOK:";

    private Map<Class<? extends Event>, EventHandler> eventHandlers = new HashMap<>();

    private String description;

    @SafeVarargs
    public Enchant(Class<? extends Event>... targetEvents) {
        EssentialEnchants.getInstance().getEnchantManager().registerEnchant(this);

        if (targetEvents != null && targetEvents.length > 0) {
            EssentialEnchants.getInstance().getEventManager().add(this, targetEvents);

            for (Class<? extends Event> eventClass : targetEvents) {
                eventHandlers.put(eventClass, null);
            }
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
     * @return the description of this enchant
     */
    public String getDescription() {
        return TextUtils.colorize(description);
    }

    /**
     * @param description the description to set for this enchant
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
     * Get the level of this enchantment on the given item.
     * 
     * @param item the item to check
     * @return The level. This will return -1 if the item is not enchant with
     *         this enchantment.
     */
    public int getEnchantLevel(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return -1;
        }

        if (!item.getItemMeta().hasLore()) {
            return -1;
        }

        Enchants enchants = Enchants.load(item.getItemMeta().getLore());

        return enchants.get(this);
    }

    /**
     * @param item the item to check
     * @return If the item is enchanted with this enchantment
     */
    public boolean isEnchanted(ItemStack item) {
        return getEnchantLevel(item) > 0;
    }

    public void registerEventHandler(Class<? extends Event> eventClass, EventHandler handler) {
        Objects.requireNonNull(eventClass, "eventClass can not be null");
        Objects.requireNonNull(handler, "handler can not be null");
        if (!eventHandlers.containsKey(eventClass)) {
            EssentialEnchants.getInstance().getLogger().warning(
                    "Attempted to register EventHandler for the event '\" + eventClass.getSimpleName() + \"' in the '\" + getClass().getSimpleName() + \"' enchantment, but it was not one of the target events!");
            return;
        }
        if (eventHandlers.get(eventClass) != null) {
            EssentialEnchants.getInstance().getLogger().warning(
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
            EssentialEnchants.getInstance().getLogger()
                    .warning("The enchant '" + getIdentifier() + "' has the event '" + clazz.getSimpleName() + "' listed as a target class, but hasn't set up an event handler for it yet!");
            return;
        }

        EventHandler eventHandler = eventHandlers.get(clazz);
        eventHandler.listen(event);
    }

    /**
     * @return a custom enchant book for this item
     */
    public ItemStack createBook(int level) {
        return ItemFactory.builder(Material.ENCHANTED_BOOK)
                .setName(String.format("&2%s &ae%s", getDisplay(), TextUtils.numeral(level)))
                .setLore(TextUtils.hideText(String.format("%s%s;%d", ENCHANT_BOOK_IDENTIFIER, getIdentifier(), level)),
                        getDescription(),
                        "",
                        "&2Can be applied to:&a " + getApplicableItems().stream().map(ItemType::getDisplay).collect(Collectors.joining(", ")),
                        "",
                        "&7Click an item to apply",
                        "&7this enchant to it.")
                .build();
    }

    /**
     * @param item the item to apply this enchant to
     * @param level the level of this enchant on the item
     * @return
     */
    public ItemStack apply(ItemStack item, int level) {
        return item;
    }

}
