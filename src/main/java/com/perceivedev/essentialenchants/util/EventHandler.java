package com.perceivedev.essentialenchants.util;

import org.bukkit.event.Event;

@FunctionalInterface
public interface EventHandler {
    void listen(Event event);
}
