package org.theteleporter9.airlanders.Util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.theteleporter9.airlanders.Main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MenueCreator implements Listener {

    private final Main plugin;
    private final Map<String, Menu> menus = new HashMap<>();
    private final Map<String, Consumer<Player>> itemActions = new HashMap<>();

    public MenueCreator(Main plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
    }

    public Menu createMenu(String name, int size) {
        if (size < 9 || size > 54 || size % 9 != 0) {
            throw new IllegalArgumentException("Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got " + size + ")");
        }
        Menu menu = new Menu(name, size);
        menus.put(name, menu);
        return menu;
    }

    public void openMenu(Player player, String menuName) {
        Menu menu = menus.get(menuName);
        if (menu != null) {
            player.openInventory(menu.getInventory());
        } else {
            player.sendMessage("Menu not found!");
        }
    }

    public void setItem(String menuName, int slot, Material material, String displayName) {
        Menu menu = menus.get(menuName);
        if (menu != null) {
            menu.setItem(slot, material, displayName);
        }
    }

    public void registerItemAction(String itemName, Consumer<Player> action) {
        itemActions.put(itemName, action);
    }

    private Menu getMenuByInventory(Inventory inventory) {
        for (Menu menu : menus.values()) {
            if (menu.getInventory().equals(inventory)) {
                return menu;
            }
        }
        return null;
    }

    private class MenuListener implements org.bukkit.event.Listener {
        @org.bukkit.event.EventHandler
        public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
            Menu menu = getMenuByInventory(event.getInventory());
            if (menu != null) {
                event.setCancelled(true); // Prevent items from being moved around

                if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                    ItemMeta meta = event.getCurrentItem().getItemMeta();
                    if (meta != null && meta.hasDisplayName()) {
                        String itemName = meta.getDisplayName();
                        Consumer<Player> action = itemActions.get(itemName);

                        if (action != null) {
                            if (event.getWhoClicked() instanceof Player) {
                                Player player = (Player) event.getWhoClicked();
                                action.accept(player); // Execute the action associated with the item
                            }
                        }
                    }
                }
            }
        }
    }

    public static class Menu {
        private final String name;
        private final Inventory inventory;

        public Menu(String name, int size) {
            this.name = name;
            this.inventory = Bukkit.createInventory(null, size, name);
        }

        public String getName() {
            return name;
        }

        public Inventory getInventory() {
            return inventory;
        }

        public void setItem(int slot, Material material, String displayName) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(displayName);
                item.setItemMeta(meta);
            }
            inventory.setItem(slot, item);
        }
    }

}
