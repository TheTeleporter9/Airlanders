package org.theteleporter9.airlanders.Handelers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.theteleporter9.airlanders.Commands.Shop;
import org.theteleporter9.airlanders.Main;

public class onVillagerIneract implements Listener {
    Shop s;


    public onVillagerIneract(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();

        if (event.getRightClicked() instanceof Villager){
            event.setCancelled(true);

            player.sendMessage("OPen Inventory");
        }
    }


}
