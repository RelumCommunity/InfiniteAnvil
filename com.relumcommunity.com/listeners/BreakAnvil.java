package com.relumcommunity.infiniteanvil.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.relumcommunity.infiniteanvil.main.Main;
import com.relumcommunity.infiniteanvil.utils.Data;

public class BreakAnvil implements Listener{
	private Data data = Main.getInstance().getData();
	@EventHandler
	public void breakAnvil(BlockBreakEvent e) {
		String prefixs = Main.getInstance().getConfig().getString("settings.Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String nopermbreak = Main.getLangFile().getString("messages.no-perm-break");
		String colormsg1 = nopermbreak.replaceAll("&", "§");
		String anvilremoves = Main.getLangFile().getString("messages.anvil-remove");
		String anvilremove = anvilremoves.replaceAll("&", "§"); //colormsg2
		Player p = e.getPlayer();
		if (e.getBlock().getType() == Material.ANVIL) {
			if (data.checkData(e.getBlock())) {
	            Location l = e.getBlock().getLocation();
	            int x = l.getBlockX();
	            int y = l.getBlockY();
	            int z = l.getBlockZ();
	            
	            if (p.hasPermission("infiniteanvil.remove")) {
	            	Main.getInstance().getData().getLoadData().set(data.getUUID(), null);
		            Main.getInstance().getData().saveData();
	            	if (Main.getInstance().getConfig().getBoolean("settings.notify")) {
	    				String colormsg2 = anvilremove.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
	    				p.getPlayer().sendMessage(prefix + colormsg2);
	  	            }
	            }
	            else {
	            	e.setCancelled(true);
	            	p.getPlayer().sendMessage(prefix + colormsg1);
	            }
	        }
		}
	}
}
