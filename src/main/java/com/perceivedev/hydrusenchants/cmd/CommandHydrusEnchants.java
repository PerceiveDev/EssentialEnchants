package com.perceivedev.hydrusenchants.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;
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
			@SuppressWarnings("deprecation")
			Player player = Bukkit.getPlayer(args[1]);
			if (player == null) {
				msg(sender, "That player doesn't exist!");
				return true;
			}

			Enchant enchant = HydrusEnchants.getInstance().getEnchantManager().getEnchant(args[2]);
			if (enchant == null) {
				msg(sender, "That enchant doesn't exist!");
				return true;
			}

			int level = -1;
			try {
				level = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {

			}

			if (level < 1 || level > enchant.maxLevel()) {
				msg(sender,
						"The level must be a number between 1 and the max enchant level! In this case, the max enchant level is "
								+ enchant.maxLevel());
				return true;
			}

			player.getInventory().addItem(enchant.createBook(level));

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
