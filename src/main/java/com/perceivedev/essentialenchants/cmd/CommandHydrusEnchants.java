package com.perceivedev.essentialenchants.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perceivedev.essentialenchants.EssentialEnchants;
import com.perceivedev.essentialenchants.enchant.types.Enchant;
import com.perceivedev.essentialenchants.gui.EnchantsGui;

/**
 * @author Rayzr
 *
 */
public class CommandHydrusEnchants implements CommandExecutor {

    private EssentialEnchants plugin;

    public CommandHydrusEnchants(EssentialEnchants plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            usage(sender, command);
            return true;
        }

        String sub = args[0].toLowerCase();
        if (sub.equals("list")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.tr("command.onlyPlayers"));
                return true;
            }
            new EnchantsGui().open((Player) sender);
        } else if (sub.equals("give")) {
            if (args.length < 4) {
                sender.sendMessage(plugin.tr("command.args.notEnough"));
                usage(sender, command);
                return true;
            }
            @SuppressWarnings("deprecation")
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(plugin.tr("command.player.notExist"));
                return true;
            }

            Enchant enchant = EssentialEnchants.getInstance().getEnchantManager().getEnchant(args[2]);
            if (enchant == null) {
                sender.sendMessage(plugin.tr("command.enchant.notExist"));
                return true;
            }

            int level = -1;
            try {
                level = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {

            }

            if (level < 1 || level > enchant.maxLevel()) {
                sender.sendMessage(plugin.tr("command.enchant.aboveMax", enchant.maxLevel()));
                return true;
            }

            player.getInventory().addItem(enchant.createBook(level));

        } else {
            usage(sender, command);
        }

        return true;
    }

    private void usage(CommandSender sender, Command command) {
        sender.sendMessage(plugin.tr("command.enchant.usage").split("\n"));
    }

}
