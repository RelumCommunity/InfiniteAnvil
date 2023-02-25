/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.relumcommunity.infiniteanvil.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.relumcommunity.infiniteanvil.commands.CommandHandler;
import com.relumcommunity.infiniteanvil.main.Main;
import com.relumcommunity.infiniteanvil.utils.GenerateUUID;

public class PlaceAnvil implements Listener {
	private GenerateUUID uuid = Main.getInstance().getGenerateUUID();

	@EventHandler
	public void placeAnvil(BlockPlaceEvent e) {
		if (!e.isCancelled()) {
			String prefixs = Main.getInstance().getConfig().getString("settings.Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String anvilsets = Main.getLangFile().getString("messages.anvil-set");
			String anvilset = anvilsets.replaceAll("&", "§"); //colormsg1
			Player p = e.getPlayer();
			if (e.getBlock().getType() == Material.ANVIL) {
				Main.getInstance().getCommandHandler();
				if (CommandHandler.getTogglePlayer().contains(p.getUniqueId())) {
					Location l = e.getBlock().getLocation();
					int x = l.getBlockX();
					int y = l.getBlockY();
					int z = l.getBlockZ();
        
					@SuppressWarnings("deprecation")
					Byte data = Byte.valueOf(e.getBlockPlaced().getData());
        
					Main.getInstance().getData().getLoadData().set(uuid.generateUUID().toString(), e.getBlockPlaced().getWorld().getName() + ", " + x + ", " + y + ", " + z + ", " + data);
					Main.getInstance().getData().saveData();
					if (Main.getInstance().getConfig().getBoolean("settings.notify")) {
	    				String colormsg1 = anvilset.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
	    				p.getPlayer().sendMessage(prefix + colormsg1);
					}
				}
			}
		}
	}
}