/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.relumcommunity.infiniteanvil.crafting;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.relumcommunity.infiniteanvil.main.Main;

public class RemoveAnvilCrafting implements Listener {
	@EventHandler
	public void noAnvilCrafting(PrepareItemCraftEvent l) {
		if (Main.getInstance().getConfig().getBoolean("settings.RemoveAnvilRecipe")) {
			ItemStack[] matrix = l.getInventory().getMatrix();
			if (!l.getInventory().getType().equals(InventoryType.WORKBENCH)) {
                return;
            }
			for (int i = 0; i < 9; ++i) {
                if (i != 3 && i != 5 && matrix[i] == null) {
                    return;
                }
            }
			if (matrix[0].getType().equals(Material.IRON_BLOCK) && matrix[0].getType() != null && matrix[1].getType().equals(Material.IRON_BLOCK) && matrix[1].getType() != null && matrix[2].getType().equals(Material.IRON_BLOCK) && matrix[2].getType() != null && matrix[3] == null && matrix[4].getType().equals(Material.IRON_INGOT) && matrix[4].getType() != null && matrix[5] == null && matrix[6].getType().equals(Material.IRON_INGOT) && matrix[6].getType() != null && matrix[7].getType().equals(Material.IRON_INGOT) && matrix[7].getType() != null && matrix[8].getType().equals(Material.IRON_INGOT) && matrix[8].getType() != null) {
                l.getInventory().setResult(new ItemStack(Material.AIR));
            }
		}
		if (!Main.getInstance().getConfig().getBoolean("settings.CustomAnvilRecipe")) {
			ItemStack[] matrix = l.getInventory().getMatrix();
			if (l.getInventory().getType().equals(InventoryType.WORKBENCH)) {
				for (int i = 0; i < 9; ++i) {
	                if (i != 3 && i != 5 && matrix[i] == null) {
	                    return;
	                }
	            }
				FileConfiguration main = Main.getInstance().getConfig();
				String a = main.getString("recipe.A");
		        String b = main.getString("recipe.B");
		        String c = main.getString("recipe.C");
		        String d = main.getString("recipe.D");
		        String e = main.getString("recipe.E");
		        String f = main.getString("recipe.F");
		        String g = main.getString("recipe.G");
		        String h = main.getString("recipe.H");
		        String i = main.getString("recipe.I");
				if ((matrix[0] == null || (matrix[0].getType().equals(Material.valueOf(a)) && matrix[0].getType() != null)) && (matrix[1] == null || (matrix[1].getType().equals(Material.valueOf(b)) && matrix[1].getType() != null)) && (matrix[2] == null || (matrix[2].getType().equals(Material.valueOf(c)) && matrix[2].getType() != null)) && (matrix[3] == null || (matrix[3].getType().equals(Material.valueOf(d)) && matrix[3].getType() != null)) && (matrix[4] == null || (matrix[4].getType().equals(Material.valueOf(e)) && matrix[4].getType() != null)) && (matrix[5] == null || (matrix[5].getType().equals(Material.valueOf(f)) && matrix[5].getType() != null)) && (matrix[6] == null || (matrix[6].getType().equals(Material.valueOf(g)) && matrix[6].getType() != null)) && (matrix[7] == null || (matrix[7].getType().equals(Material.valueOf(h)) && matrix[7].getType() != null)) && (matrix[8] == null || (matrix[8].getType().equals(Material.valueOf(i)) && matrix[8].getType() != null))) {
	                l.getInventory().setResult(new ItemStack(Material.AIR));
	            }
			}
		}
	}
}
