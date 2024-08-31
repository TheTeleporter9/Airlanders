package org.theteleporter9.airlanders.Handelers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.theteleporter9.airlanders.Main;
import org.theteleporter9.airlanders.Util.MenueCreator;

public class onVillagerInteract implements Listener {

    private final MenueCreator mc;

    public onVillagerInteract(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        mc = new MenueCreator(plugin);

        // Create menus for different Villager names
        createMenus();
    }

    private void createMenus() {
        // Create different menus for different Villager names
        createMenuForVillager("DungeonMiner", Material.EMERALD, "Test Item", "You clicked on Dungeon Miner!");
        createMenuForVillager("Blacksmith", Material.IRON_INGOT, "Iron Bar", "You clicked on Blacksmith!");
    }

    private void createMenuForVillager(String villagerName, Material itemMaterial, String itemName, String message) {
        // Create the menu for the specific Villager
        mc.createMenu(villagerName + " Menu", 27);

        // Set item in the menu
        mc.setItem(villagerName + " Menu", 13, itemMaterial, itemName);

        // Register the item action for this menu
        mc.registerItemAction(itemName, player -> player.sendMessage(message));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (event.getRightClicked() instanceof Villager) {
            Villager villager = (Villager) event.getRightClicked();
            String villagerName = villager.getCustomName();

            if (villagerName != null && !villagerName.isEmpty()) {
                event.setCancelled(true);

                // Open the corresponding menu based on Villager's name
                mc.openMenu(player, villagerName + " Menu");
            } else {
                player.sendMessage("Dialog is still in progress");
            }
        }
    }
}
