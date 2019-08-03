/**
 *  @author nato3623
 */

package com.vaincecraft.infiniteanvil.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.vaincecraft.infiniteanvil.main.Main;
import com.vaincecraft.infiniteanvil.utils.Data;

public class RemoveAnvil implements Listener {
	private Data data = Main.getInstance().getData();

	@EventHandler
	public void removeAnvil(PlayerInteractEvent e) {
		String prefixs = Main.getInstance().getConfig().getString("settings.Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String alreadyremoved = Main.getLangFile().getString("messages.already-removed");
		String colormsg1 = alreadyremoved.replaceAll("&", "§");
		String nopermremove = Main.getLangFile().getString("messages.no-perm-remove");
		String colormsg2 = nopermremove.replaceAll("&", "§");
		String anvilremoves = Main.getLangFile().getString("messages.anvil-remove");
		String anvilremove = anvilremoves.replaceAll("&", "§"); //colormsg3
		if (!e.isCancelled()) {
			Player p = e.getPlayer();
			if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (p.isSneaking()) && (e.getClickedBlock().getType() == Material.ANVIL) && (Main.getInstance().getConfig().getBoolean("settings.shift-click"))) {
				if (p.hasPermission("infiniteanvil.remove")) {
					e.setCancelled(true);
					if (data.checkData(e.getClickedBlock())) {
						Location l = e.getClickedBlock().getLocation();
						int x = l.getBlockX();
						int y = l.getBlockY();
						int z = l.getBlockZ();
          
						Main.getInstance().getData().getLoadData().set(data.getUUID(), null);
						Main.getInstance().getData().saveData();
						if (Main.getInstance().getConfig().getBoolean("settings.notify")) {
		    				String colormsg3 = anvilremove.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
		    				p.getPlayer().sendMessage(prefix + colormsg3);
						}
						return;
					}
					if (Main.getInstance().getConfig().getBoolean("settings.notify")) {
						p.getPlayer().sendMessage(prefix + colormsg1);
					}
					return;
				}
				p.getPlayer().sendMessage(prefix + colormsg2);
			}
		}
	}
}
