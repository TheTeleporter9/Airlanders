package org.theteleporter9.airlanders.Mine;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.xml.stream.Location;

public class MineFieldGeneration implements Listener {


    Material[] materials = {Material.STONE, Material.CLAY, Material.SMOOTH_BASALT,
            Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.REDSTONE_ORE,
            Material.DIAMOND_ORE, Material.EMERALD_ORE,
            Material.STONE, Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,
            Material.STONE, Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,
            Material.STONE, Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,
            Material.STONE, Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE,Material.STONE};


    public void generateMineArea(String worldName, int x1, int y1, int z1, int x2, int y2, int z2) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            Bukkit.getLogger().warning("World not found!");
            return;
        }

        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                    Block block = world.getBlockAt(x, y, z);

                    int randIndex = (int) (Math.random() * materials.length);

                    block.setType(materials[randIndex]);
                }
            }
        }
    }
}
