//package org.theteleporter9.airlanders.Util;
//
//import org.bukkit.Material;
//import org.bukkit.inventory.ItemStack;
//
//import java.util.List;
//
//public class uiGenerator {
//
//    public int invSlot = 0;
//    String[][] uiLayout = { {"", "", "", "", "", "", "", "", ""},
//                            {"", "", "", "", "", "", "", "", ""},
//                            {"", "", "", "", "", "", "", "", ""}};
//    public ItemStack setItem() {
//        for (int i = 0; i < uiLayout.length; i++) {
//            // Loop through columns (inner array)
//            for (int j = 0; j < uiLayout[i].length; j++) {
//                invSlot = i + j;
//                String value = uiLayout[i][j];
//                switch (value){
//                    case "e":
//                        return new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
//                    case "b":
//                        return null;
//                }
//            }
//            System.out.println(); // Move to the next row
//        }
//        return null;
//    }
//}
