package org.theteleporter9.airlanders.Handelers;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.theteleporter9.airlanders.Main;
import org.theteleporter9.airlanders.Util.playerMessages;

public class onJoinEvent implements Listener {

    playerMessages playerMessage = new playerMessages();
    public onJoinEvent(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJon(PlayerJoinEvent event){
        Player newPlayer = event.getPlayer();
        String welcomeMessage = ChatColor.AQUA + "Welcome " + newPlayer.getName();
        playerMessage.sendActionBar(newPlayer ,welcomeMessage);
    }
}
