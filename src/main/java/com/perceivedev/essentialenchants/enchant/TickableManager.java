package com.perceivedev.essentialenchants.enchant;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Rayzr
 *
 */
public class TickableManager {

    private Set<PlayerTicker> tickers = new HashSet<PlayerTicker>();

    public TickableManager(Plugin plugin, long period) {
        new BukkitRunnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    tickers.forEach(ticker -> {
                        ticker.tick(player);
                    });
                }
            }
        }.runTaskTimer(plugin, 1L, period);
    }

    public void addTicker(PlayerTicker ticker) {
        tickers.add(ticker);
    }

    public void removeTicker(PlayerTicker ticker) {
        tickers.remove(ticker);
    }

}
