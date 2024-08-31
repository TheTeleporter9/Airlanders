package org.theteleporter9.airlanders.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.theteleporter9.airlanders.Main;

public class summonTrader implements CommandExecutor {

    private final Main plugin;

    public summonTrader(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("summontrader").setExecutor(this); // Ensure the command is registered
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;

        // Check if the command is correct
        if (args.length != 1) {
            player.sendMessage("Usage: /summontrader <name>");
            return true;
        }

        // Get the name for the trader
        String traderName = args[0];

        // Create the trader at the player's location
        createTrader(player.getLocation(), traderName);

        player.sendMessage("Trader '" + traderName + "' has been summoned at your location.");
        return true;
    }

    private void createTrader(Location loc, String name) {
        if (loc.getWorld() == null) {
            return; // Exit if the world is null
        }

        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setCustomName(name);
        villager.setCustomNameVisible(true);
        villager.setAI(false);

        // Optionally, you can also configure the villager's profession or other attributes
        // villager.setProfession(Villager.Profession.FARMER);
    }
}
