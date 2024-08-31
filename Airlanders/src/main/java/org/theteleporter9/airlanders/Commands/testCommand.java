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



        return true;
    }
}
