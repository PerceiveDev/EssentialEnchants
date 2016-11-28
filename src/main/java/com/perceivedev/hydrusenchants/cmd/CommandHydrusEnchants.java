package com.perceivedev.hydrusenchants.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perceivedev.hydrusenchants.gui.EnchantsGui;
import com.perceivedev.hydrusenchants.util.TextUtils;

/**
 * @author Rayzr
 *
 */
public class CommandHydrusEnchants implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            usage(sender, command);
            return true;
        }

        String sub = args[0].toLowerCase();
        if (sub.equals("list")) {
            if (!(sender instanceof Player)) {
                msg(sender, "Only players can do that!");
                return true;
            }
            new EnchantsGui().open((Player) sender);
        } else if (sub.equals("give")) {
            if (args.length < 4) {
                msg(sender, "Not enough arguments!");
                usage(sender, command);
                return true;
            }
            msg(sender, "---------- THIS FEATURE HAS YET TO BE COMPLETED ----------");
        } else {
            usage(sender, command);
        }

        return true;
    }

    private void usage(CommandSender sender, Command command) {
        msg(sender, "Usage: ");
        msg(sender, "&7/" + command.getLabel() + " list");
        msg(sender, "&7/" + command.getLabel() + " give <player> <book> <level>");
    }

    private void msg(CommandSender player, String msg) {
        player.sendMessage(TextUtils.colorize("&8[&aHydrusEnchants&8]&2 " + msg));
    }

}
