package org.theteleporter9.airlanders.Handelers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.theteleporter9.airlanders.Main;
import org.theteleporter9.airlanders.Storage.Storagge_before_SQL;
import org.theteleporter9.airlanders.Util.playerMessages;

public class onBlockBreakeEvent implements Listener {


    int blocksbroken = 0;

    public onBlockBreakeEvent(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    playerMessages messages = new playerMessages();

    @EventHandler
    void onBlockBreakEvent(BlockBreakEvent event){

        blocksbroken++;

        Player player = event.getPlayer();
        String message = ChatColor.GREEN + "You have broken " + blocksbroken + " blocks!";
        messages.sendActionBar(player, message);
        //levelUp(player);

    }

    void levelUp(Player player){
        player.giveExpLevels(blocksbroken / 10);
    }

}
