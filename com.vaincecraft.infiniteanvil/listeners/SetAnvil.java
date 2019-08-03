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
import com.vaincecraft.infiniteanvil.utils.GenerateUUID;

public class SetAnvil implements Listener {
	private Data data = Main.getInstance().getData();
	private GenerateUUID uuid = Main.getInstance().getGenerateUUID();

	@EventHandler
	public void setAnvil(PlayerInteractEvent e) {
		String prefixs = Main.getInstance().getConfig().getString("settings.Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String alreadyset = Main.getLangFile().getString("messages.already-set");
		String colormsg1 = alreadyset.replaceAll("&", "§");
		String nopermset = Main.getLangFile().getString("messages.no-perm-set");
		String colormsg2 = nopermset.replaceAll("&", "§");
		String anvilsets = Main.getLangFile().getString("messages.anvil-set");
		String anvilset = anvilsets.replaceAll("&", "§"); //colormsg3
		if (!e.isCancelled()) {
			Player p = e.getPlayer();
			if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (p.isSneaking()) && (e.getClickedBlock().getType() == Material.ANVIL) && (Main.getInstance().getConfig().getBoolean("settings.shift-click"))) {
				if (p.hasPermission("infiniteanvil.set")) {
					Location l = e.getClickedBlock().getLocation();
					int x = l.getBlockX();
					int y = l.getBlockY();
					int z = l.getBlockZ();
        
					@SuppressWarnings("deprecation")
					Byte blockData = Byte.valueOf(e.getClickedBlock().getData());
					e.setCancelled(true);
					if (data.checkData(e.getClickedBlock())) {
						if (Main.getInstance().getConfig().getBoolean("settings.notify")) {
							p.getPlayer().sendMessage(prefix + colormsg1);;
						}
						return;
					}
					Main.getInstance().getData().getLoadData().set(uuid.generateUUID().toString(), e.getClickedBlock().getWorld().getName() + ", " + x + ", " + y + ", " + z + ", " + blockData);
					Main.getInstance().getData().saveData();
					if (Main.getInstance().getConfig().getBoolean("settings.notify")) {
	    				String colormsg3 = anvilset.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
	    				p.getPlayer().sendMessage(prefix + colormsg3);
					}
					return;
				}
				p.getPlayer().sendMessage(prefix + colormsg2);
			}
		}
	}
}
