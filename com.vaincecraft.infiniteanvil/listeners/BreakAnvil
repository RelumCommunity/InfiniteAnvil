package com.vaincecraft.infiniteanvil.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.vaincecraft.infiniteanvil.configurations.Configuration;
import com.vaincecraft.infiniteanvil.main.Main;
import com.vaincecraft.infiniteanvil.utils.Data;

public class BreakAnvil implements Listener{
	private Configuration configuration = Main.getInstance().getConfiguration();
	private Data data = Main.getInstance().getData();
	@EventHandler
	public void breakAnvil(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (e.getBlock().getType() == Material.ANVIL) {
			if (this.data.checkData(e.getBlock())) {
	            Location l = e.getBlock().getLocation();
	            int x = l.getBlockX();
	            int y = l.getBlockY();
	            int z = l.getBlockZ();
	            
	            if (p.hasPermission("infiniteanvil.remove")) {
	            	Main.getInstance().getData().getLoadData().set(data.getUUID(), null);
		            Main.getInstance().getData().saveData();
	            	if (configuration.notifyMessages()) {
	  	              configuration.anvilRemoveMessage(p, x, y, z);
	  	            }
	            }
	            else {
	            	e.setCancelled(true);
	            	CommandSender sender = p;
					Main.getInstance().onDenyBreak(sender);
	            }
	        }
			return;
		}
	}
}
