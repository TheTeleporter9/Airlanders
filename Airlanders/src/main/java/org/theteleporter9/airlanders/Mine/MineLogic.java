package org.theteleporter9.airlanders.Mine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.theteleporter9.airlanders.Main;

import java.util.logging.Level;

public class MineLogic implements Listener {

    World world = Bukkit.getServer().getWorld("world");

    MineFieldGeneration MFG = new MineFieldGeneration();

    public MineLogic(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // This is dependent for the function
    boolean once = true;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        boolean isPlayerInArea = isPlayerInArea(event.getPlayer(),
                new Location(world, -57, 167, -18),
                new Location(world, -68, 143, -45));

        if (!isPlayerInArea && once) {
            once = false;
            MFG.generateMineArea("world", -57,163 ,-18,-68,143,-45);
        } else if (isPlayerInArea && !once) {
            once = true; // Reset the flag when the player returns
        }
    }


    public boolean isPlayerInArea(Player player, Location corner1, Location corner2) {
        Location playerLocation = player.getLocation();

        // Determine the min and max coordinates
        double minX = Math.min(corner1.getX(), corner2.getX());
        double maxX = Math.max(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double maxY = Math.max(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        double maxZ = Math.max(corner1.getZ(), corner2.getZ());

        // Check if the player is within the defined area
        return playerLocation.getX() >= minX && playerLocation.getX() <= maxX &&
                playerLocation.getY() >= minY && playerLocation.getY() <= maxY &&
                playerLocation.getZ() >= minZ && playerLocation.getZ() <= maxZ;
    }

}
