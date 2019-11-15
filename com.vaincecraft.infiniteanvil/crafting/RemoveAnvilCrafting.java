/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.vaincecraft.infiniteanvil.crafting;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.vaincecraft.infiniteanvil.main.Main;

public class RemoveAnvilCrafting implements Listener{
	@EventHandler
	public void noAnvilCrafting(PrepareItemCraftEvent l) {
		if (Main.getInstance().getConfig().getBoolean("settings.RemoveAnvilRecipe")) {
			ItemStack[] matrix = l.getInventory().getMatrix();
			if (matrix[0].getType().equals(Material.IRON_BLOCK)) {
				if (matrix[1].getType().equals(Material.IRON_BLOCK)) {
					if (matrix[2].getType().equals(Material.IRON_BLOCK)) {
						if (matrix[3].getType().equals(Material.AIR)) {
							if (matrix[4].getType().equals(Material.IRON_INGOT)) {
								if (matrix[5].getType().equals(Material.AIR)) {
									if (matrix[6].getType().equals(Material.IRON_INGOT)) {
										if (matrix[7].getType().equals(Material.IRON_INGOT)) {
											if (matrix[8].getType().equals(Material.IRON_INGOT)) {
												l.getInventory().setResult(new ItemStack(Material.AIR));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
