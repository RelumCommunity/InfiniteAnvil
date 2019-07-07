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
import com.vaincecraft.infiniteanvil.utils.GenerateUUID;

public class SetAnvil implements Listener {
	private Configuration configuration = Main.getInstance().getConfiguration();
	private Data data = Main.getInstance().getData();
	private GenerateUUID uuid = Main.getInstance().getGenerateUUID();

	@EventHandler
	public void setAnvil(PlayerInteractEvent e) {
		if (!e.isCancelled()) {
			Player p = e.getPlayer();
			if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (p.isSneaking()) && (e.getClickedBlock().getType() == Material.ANVIL) && (configuration.shiftClick())) {
				if (p.hasPermission("infiniteanvil.set")) {
					Location l = e.getClickedBlock().getLocation();
					int x = l.getBlockX();
					int y = l.getBlockY();
					int z = l.getBlockZ();
        
					@SuppressWarnings("deprecation")
					Byte blockData = Byte.valueOf(e.getClickedBlock().getData());
					e.setCancelled(true);
					if (data.checkData(e.getClickedBlock())) {
						if (configuration.notifyMessages()) {
							configuration.alreadySetMessage(p);
						}
						return;
					}
					Main.getInstance().getData().getLoadData().set(uuid.generateUUID().toString(), e.getClickedBlock().getWorld().getName() + ", " + x + ", " + y + ", " + z + ", " + blockData);
					Main.getInstance().getData().saveData();
					if (configuration.notifyMessages()) {
						configuration.anvilSetMessage(p, x, y, z);
					}
					return;
				}
				configuration.noPermSetMessage(p);
			}
		}
	}
}
