package org.theteleporter9.airlanders.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.theteleporter9.airlanders.Main;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Listener, CommandExecutor {

    private String invName = "Upgrade";
    int lNeeds = 10;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!event.getView().getTitle().equals(invName)){
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        player.sendMessage("This is unavalible at the moment");

        event.setCancelled(true);

    }
    public  Shop(Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Only Ingameplayers can run this command");
        }

        Player player = (Player) commandSender;

        Inventory inventory = Bukkit.createInventory(player, 9 * 3, invName);

        inventory.setItem(13, getItem(new ItemStack(Material.COMMAND_BLOCK), "&4 Upgrade Item", "You will need " + lNeeds));

        player.openInventory(inventory);

        return true;
    }

    private ItemStack getItem(ItemStack item, String name, String ... lore){
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();
        for(String s : lore){
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);

        item.setItemMeta(meta);
        return item;
    }

    private void generateUIItems(){

    }

    public void upgradeItem(Item item){



    }
}
