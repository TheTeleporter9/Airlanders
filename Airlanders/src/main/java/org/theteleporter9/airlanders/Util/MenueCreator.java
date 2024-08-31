package org.theteleporter9.airlanders.Util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.theteleporter9.airlanders.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A utility class for creating and managing menus in a Minecraft plugin.
 * <p>
 * This class provides methods to create menus with specific sizes, add items to the menus,
 * and register actions that should be executed when an item in the menu is clicked.
 * </p>
 */
public class MenueCreator implements Listener {

    private final Main plugin;
    private final Map<String, Menu> menus = new HashMap<>();
    private final Map<String, Consumer<Player>> itemActions = new HashMap<>();

    /**
     * Constructs a new MenueCreator.
     *
     * @param plugin The main plugin instance.
     */
    public MenueCreator(Main plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Creates a new menu with the specified name and size.
     *
     * @param name The name of the menu.
     * @param size The size of the menu in slots. Must be a multiple of 9 between 9 and 54.
     * @return The created {@link Menu} object.
     * @throws IllegalArgumentException if the size is not a valid multiple of 9 between 9 and 54.
     */
    public Menu createMenu(String name, int size) {
        if (size < 9 || size > 54 || size % 9 != 0) {
            throw new IllegalArgumentException("Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got " + size + ")");
        }
        Menu menu = new Menu(name, size);
        menus.put(name, menu);
        return menu;
    }

    /**
     * Opens the specified menu for the given player.
     *
     * @param player The player to open the menu for.
     * @param menuName The name of the menu to open.
     */
    public void openMenu(Player player, String menuName) {
        Menu menu = menus.get(menuName);
        if (menu != null) {
            player.openInventory(menu.getInventory());
        } else {
            player.sendMessage("Menu not found!");
        }
    }

    /**
     * Sets an item in the specified slot of the menu.
     *
     * @param menuName The name of the menu.
     * @param slot The slot to set the item in.
     * @param material The material of the item.
     * @param displayName The display name of the item.
     */
    public void setItem(String menuName, int slot, Material material, String displayName) {
        Menu menu = menus.get(menuName);
        if (menu != null) {
            menu.setItem(slot, material, displayName);
        }
    }

    /**
     * Registers an action to be executed when an item with the specified display name is clicked.
     *
     * @param itemName The display name of the item.
     * @param action The action to be executed when the item is clicked. This action receives the player who clicked the item.
     */
    public void registerItemAction(String itemName, Consumer<Player> action) {
        itemActions.put(itemName, action);
    }

    /**
     * Gets the menu associated with the given inventory.
     *
     * @param inventory The inventory to match.
     * @return The matching {@link Menu} object, or null if no menu is associated with the inventory.
     */
    private Menu getMenuByInventory(Inventory inventory) {
        for (Menu menu : menus.values()) {
            if (menu.getInventory().equals(inventory)) {
                return menu;
            }
        }
        return null;
    }

    /**
     * A class representing a menu in the Minecraft server.
     */
    public class Menu {
        private final String name;
        private final Inventory inventory;

        /**
         * Constructs a new Menu.
         *
         * @param name The name of the menu.
         * @param size The size of the menu in slots.
         */
        public Menu(String name, int size) {
            this.name = name;
            this.inventory = Bukkit.createInventory(null, size, name);
        }

        /**
         * Gets the name of the menu.
         *
         * @return The name of the menu.
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the inventory associated with the menu.
         *
         * @return The inventory.
         */
        public Inventory getInventory() {
            return inventory;
        }

        /**
         * Sets an item in the specified slot of the menu.
         *
         * @param slot The slot to set the item in.
         * @param material The material of the item.
         * @param displayName The display name of the item.
         */
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

    /**
     * An event listener for handling clicks in menu inventories.
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
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
