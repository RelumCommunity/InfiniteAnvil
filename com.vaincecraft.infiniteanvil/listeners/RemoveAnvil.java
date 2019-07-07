package com.vaincecraft.infiniteanvil.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.vaincecraft.infiniteanvil.configurations.Configuration;
import com.vaincecraft.infiniteanvil.main.Main;
import com.vaincecraft.infiniteanvil.utils.Data;

public class RemoveAnvil implements Listener {
	private Configuration configuration = Main.getInstance().getConfiguration();
	private Data data = Main.getInstance().getData();

	@EventHandler
	public void removeAnvil(PlayerInteractEvent e) {
		if (!e.isCancelled()) {
			Player p = e.getPlayer();
			if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (p.isSneaking()) && (e.getClickedBlock().getType() == Material.ANVIL) && (configuration.shiftClick())) {
				if (p.hasPermission("infiniteanvil.remove")) {
					e.setCancelled(true);
					if (data.checkData(e.getClickedBlock())) {
						Location l = e.getClickedBlock().getLocation();
						int x = l.getBlockX();
						int y = l.getBlockY();
						int z = l.getBlockZ();
          
						Main.getInstance().getData().getLoadData().set(data.getUUID(), null);
						Main.getInstance().getData().saveData();
						if (configuration.notifyMessages()) {
							configuration.anvilRemoveMessage(p, x, y, z);
						}
						return;
					}
					if (configuration.notifyMessages()) {
						configuration.alreadyRemovedMessage(p);
					}
					return;
				}
				configuration.noPermRemoveMessage(p);
			}
		}
	}
}
