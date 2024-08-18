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
import sun.security.util.math.intpoly.IntegerPolynomial1305;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class testCommand implements Listener, CommandExecutor {



    public  testCommand(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("This can only be run by a player");
        }

        commandSender.sendMessage("This has no use at the moment!");

        return false;
    }
}
