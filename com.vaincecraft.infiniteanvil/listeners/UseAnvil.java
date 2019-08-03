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

public class UseAnvil implements Listener {
	private Data data = Main.getInstance().getData();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void useAnvil(PlayerInteractEvent e) {
		if (!e.isCancelled()) {
			String prefixs = Main.getInstance().getConfig().getString("settings.Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String nopermuse = Main.getLangFile().getString("messages.no-perm-use");
			String colormsg1 = nopermuse.replaceAll("&", "§");
			Player p = e.getPlayer();
			if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.ANVIL) && (data.checkData(e.getClickedBlock()))) {
				Location l = e.getClickedBlock().getLocation();
				if (!p.hasPermission("infiniteanvil.use")) {
					e.setCancelled(true);
					p.getPlayer().sendMessage(prefix + colormsg1);
				}
				if (e.getClickedBlock().getData() != data.getBlockData().byteValue()) {
					e.getClickedBlock().getWorld().getBlockAt(l).setData(data.getBlockData().byteValue());
				}
			}
		}
	}
}
