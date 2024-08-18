package org.theteleporter9.airlanders;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.theteleporter9.airlanders.Commands.Shop;
import org.theteleporter9.airlanders.Commands.testCommand;
import org.theteleporter9.airlanders.Handelers.onBlockBreakeEvent;
import org.theteleporter9.airlanders.Handelers.onJoinEvent;
import org.theteleporter9.airlanders.Mine.MineLogic;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Hello World!");

        new onJoinEvent(this);
        new onBlockBreakeEvent(this);
        new Shop(this);
        new MineLogic(this);
        //new GUI_Manager(this);
        Objects.requireNonNull(getCommand("menu")).setExecutor(new Shop(this));
        Objects.requireNonNull(getCommand("testUtil")).setExecutor(new testCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().warning("The Plugin is now disabled");
    }
}
