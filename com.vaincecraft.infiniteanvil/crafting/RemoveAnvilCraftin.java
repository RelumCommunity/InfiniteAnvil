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
	public void noAnvilCrafting(PrepareItemCraftEvent e) {
		if (Main.getInstance().getConfig().getBoolean("settings.RemoveAnvilRecipe")) {
			if(e.getRecipe().getResult().getType() == Material.ANVIL) {
				e.getInventory().setResult(new ItemStack(Material.AIR));
			}
		}
	}
}
