package org.theteleporter9.airlanders.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.theteleporter9.airlanders.Main;
import org.theteleporter9.airlanders.Util.MenueCreator;
import sun.security.util.math.intpoly.IntegerPolynomial1305;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class testCommand implements Listener, CommandExecutor {


    private final MenueCreator mc;

    public testCommand(Main plugin) {
        mc = new MenueCreator(plugin);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This can only be run by a player");
            return true;
        }

        Player player = (Player) commandSender;

        // Create the menus
        mc.createMenu("Test Menu", 27); // Valid size

        mc.setItem("Test Menu", 11, Material.DIAMOND_SWORD, "Sword of Power");
        mc.setItem("Test Menu", 15, Material.GOLDEN_APPLE, "Golden Apple");

        // Register item actions
        mc.registerItemAction("Sword of Power", p -> p.sendMessage("You clicked the Sword of Power!"));
        mc.registerItemAction("Golden Apple", p -> p.sendMessage("You clicked the Golden Apple!"));

        // Open the menu for the player
        mc.openMenu(player, "Test Menu");
        

        return true;
    }
}
