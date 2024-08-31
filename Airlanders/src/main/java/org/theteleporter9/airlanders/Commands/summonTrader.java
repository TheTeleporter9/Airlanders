package org.theteleporter9.airlanders.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Listener;
import org.theteleporter9.airlanders.Main;

public class summonTrader implements CommandExecutor, Listener {

    public summonTrader(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("This command can only be run by a in game player");
            return true;
        }

        createTrader(((Player) commandSender).getLocation(), "Trader Test");

        return true;
    }

    void createTrader(Location loc, String name) {

        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setCustomName(name);
        villager.setCustomNameVisible(true);
        villager.setAI(false);

    }
}
