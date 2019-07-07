/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.vaincecraft.infiniteanvil.commands;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.vaincecraft.infiniteanvil.configurations.Configuration;
import com.vaincecraft.infiniteanvil.main.Main;
import com.vaincecraft.infiniteanvil.utils.Data;
import com.vaincecraft.infiniteanvil.utils.GenerateUUID;

public class CommandHandler implements CommandExecutor{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Set<UUID> togglePlayer = new HashSet();
	private Configuration configuration = Main.getInstance().getConfiguration();
	private Data data = Main.getInstance().getData();
	private GenerateUUID uuid = Main.getInstance().getGenerateUUID();
	private int radius;
	private int total;
	  
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
	    int length = args.length;
	    if (length > 0) {
	    	if (args[0].equalsIgnoreCase("set")) {
	    		if (sender.hasPermission("infiniteanvil.set")) {
	    			if (length > 1) {
	    				try {
	    					radius = Integer.parseInt(args[1]);
	    				}
	    				catch (NumberFormatException e) {
	    					configuration.invalidNumberMessage(sender, args[1]);
	    					return true;
	    				}
	    				if (radius == 0) {
	    					configuration.numberZeroMessage(sender);
	    					return true;
	    				}
	    				if (radius < 0) {
	    					configuration.numberNegativeMessage(sender);
	    					return true;
	    				}
	    				if (radius > configuration.maxRadius()) {
	    					configuration.numberAboveMaxMessage(sender);
	    					return true;
	    				}
	    				total = 0;
	    				final Block b = p.getLocation().getBlock();
	    				new BukkitRunnable() {
	    					@SuppressWarnings("deprecation")
							public void run() {
	    						for (int x = -CommandHandler.this.radius; x <= CommandHandler.this.radius; x++) {
	    							for (int y = -CommandHandler.this.radius; y <= CommandHandler.this.radius; y++) {
	    								for (int z = -CommandHandler.this.radius; z <= CommandHandler.this.radius; z++) {
	    									if (b.getRelative(x, y, z).getType() == Material.ANVIL) {
	    										Block foundAnvil = b.getRelative(x, y, z);
	    										if (!CommandHandler.this.data.checkData(foundAnvil)) {
	    											Location l = foundAnvil.getLocation();
	    											int anvilX = l.getBlockX();
	    											int anvilY = l.getBlockY();
	    											int anvilZ = l.getBlockZ();
	                          
	    											CommandHandler.this.data.getLoadData().set(CommandHandler.this.uuid.generateUUID().toString(), foundAnvil.getWorld().getName() + ", " + anvilX + ", " + anvilY + ", " + anvilZ + ", " + foundAnvil.getData());
	    										}
	    									}
	    								}
	    							}
	    						}
	    						if (CommandHandler.this.total == 0) {
	    							CommandHandler.this.configuration.noneRadiusSetMessage(sender);
	    						}
	    						else {
	    							CommandHandler.this.data.saveData();
	    							CommandHandler.this.configuration.radiusSetMessage(sender, CommandHandler.this.total, CommandHandler.this.radius);
	    						}
	    					}
	    				}.runTaskAsynchronously(Main.getInstance());
	    				return true;
	    			}
	    			@SuppressWarnings({ "unchecked", "rawtypes" })
					Block b = p.getTargetBlock((Set)null, 25);
	    			if (b.getType() == Material.ANVIL) {
	    				Location l = b.getLocation();
	    				int x = l.getBlockX();
	    				int y = l.getBlockY();
	    				int z = l.getBlockZ();
	            
	    				@SuppressWarnings("deprecation")
						Byte blockData = Byte.valueOf(b.getData());
	    				if (data.checkData(b)) {
	    					configuration.alreadySetMessage(p);
	    					return true;
	    				}
	    				data.getLoadData().set(uuid.generateUUID().toString(), b.getWorld().getName() + ", " + x + ", " + y + ", " + z + ", " + blockData);
	    				data.saveData();
	            
	    				configuration.anvilSetMessage(p, x, y, z);
	    				return true;
	    			}
	    			configuration.invalidBlockMessage(sender);
	    			return true;
	    		}
	    		configuration.noPermCmdMessage(sender);
	    		return true;
	    	}
	    	if ((args[0].equalsIgnoreCase("remove")) || (args[0].equalsIgnoreCase("unset"))) {
	    		if (sender.hasPermission("infiniteanvil.remove")) {
	    			if (length > 1) {
	    				try {
	    					radius = Integer.parseInt(args[1]);
	    				}
	    				catch (NumberFormatException e) {
	    					configuration.invalidNumberMessage(sender, args[1]);
	    					return true;
	    				}
	    				if (radius == 0) {
	    					configuration.numberZeroMessage(sender);
	    					return true;
	    				}
	    				if (radius < 0) {
	    					configuration.numberNegativeMessage(sender);
	    					return true;
	    				}
	    				if (radius > configuration.maxRadius()) {
	    					configuration.numberAboveMaxMessage(sender);
	    					return true;
	    				}
	    				total = 0;
	    				final Block b = p.getLocation().getBlock();
	    				new BukkitRunnable() {
	    					public void run() {
	    						for (int x = -CommandHandler.this.radius; x <= CommandHandler.this.radius; x++) {
	    							for (int y = -CommandHandler.this.radius; y <= CommandHandler.this.radius; y++) {
	    								for (int z = -CommandHandler.this.radius; z <= CommandHandler.this.radius; z++) {
	    									if (b.getRelative(x, y, z).getType() == Material.ANVIL) {
	    										Block foundAnvil = b.getRelative(x, y, z);
	    										if (CommandHandler.this.data.checkData(foundAnvil)) {
	    											CommandHandler.this.data.getLoadData().set(CommandHandler.this.data.getUUID(), null);
	    										}
	    									}
	    								}
	    							}
	    						}
	    						if (CommandHandler.this.total == 0) {
	    							CommandHandler.this.configuration.noneRadiusRemoveMessage(sender);
	    						}
	    						else {
	    							CommandHandler.this.data.saveData();
	    							CommandHandler.this.configuration.radiusRemoveMessage(sender, CommandHandler.this.total, CommandHandler.this.radius);
	    						}
	    					}
	    				}.runTaskAsynchronously(Main.getInstance());
	    				return true;
	    			}
	    			@SuppressWarnings({ "unchecked", "rawtypes" })
					Block b = p.getTargetBlock((Set)null, 25);
	    			if (b.getType() == Material.ANVIL) {
	    				if (data.checkData(b)) {
	    					Location l = b.getLocation();
	    					int x = l.getBlockX();
	    					int y = l.getBlockY();
	    					int z = l.getBlockZ();
	              
	    					data.getLoadData().set(data.getUUID(), null);
	    					data.saveData();
	              
	    					configuration.anvilRemoveMessage(p, x, y, z);
	    					return true;
	    				}
	    				configuration.alreadyRemovedMessage(p);
	    				return true;
	    			}
	    			configuration.invalidBlockMessage(sender);
	    			return true;
	    		}
	    		configuration.noPermCmdMessage(sender);
	    		return true;
	    	}
	    	if (args[0].equalsIgnoreCase("toggle")) {
	    		if (sender.hasPermission("infiniteanvil.toggle")) {
	    			if (length > 1) {
	    				if (args[1].equalsIgnoreCase("off")) {
	    					if (!togglePlayer.contains(p.getUniqueId())) {
	    						configuration.alreadyToggledOffMessage(sender);
	    						return true;
	    					}
	    					configuration.toggleOffMessage(sender);
	    					togglePlayer.remove(p.getUniqueId());
	    					return true;
	    				}
	    				if (args[1].equalsIgnoreCase("on")) {
	    					if (togglePlayer.contains(p.getUniqueId())) {
	    						configuration.alreadyToggledOnMessage(sender);
	    						return true;
	    					}
	    					configuration.toggleOnMessage(sender);
	    					togglePlayer.add(p.getUniqueId());
	    					return true;
	    				}
	    				configuration.invalidSyntaxMessage(sender, "/" + label + " toggle [on/off]");
	    				return true;
	    			}
	    			if (togglePlayer.contains(p.getUniqueId())) {
	    				configuration.toggleOffMessage(sender);
	    				togglePlayer.remove(p.getUniqueId());
	    				return true;
	    			}
	    			configuration.toggleOnMessage(sender);
	    			togglePlayer.add(p.getUniqueId());
	    			return true;
	    		}
	    		configuration.noPermCmdMessage(sender);
	    		return true;
	    	}
	    	if ((args[0].equalsIgnoreCase("purge")) && (sender.hasPermission("infiniteanvil.purge"))) {
	    		new BukkitRunnable() {
	    			public void run() {
	    				CommandHandler.this.data.clearData();
	    				if (CommandHandler.this.data.getAnvils() == 0) {
	    					CommandHandler.this.configuration.alreadyEmptyMessage(sender);
	    				} else {
	    					CommandHandler.this.configuration.dataPurgeMessage(sender, CommandHandler.this.data.getAnvils());
	    				}
	    			}
	    		}.runTaskAsynchronously(Main.getInstance());
	    		return true;
	    	}
	    	if (args[0].equalsIgnoreCase("reload")) {
	    		if (sender.hasPermission("infiniteanvil.reload")) {
	    		  	Main.getInstance().reloadConfig();
	    		  	configuration.reloadMessage(sender);
	    		  	return true;
	    	  	}
	    	  	configuration.noPermCmdMessage(sender);
	    	  	return true;
	      	}
	      	if (args[0].equalsIgnoreCase("version")) {
	      		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aRunning Infinite Anvil v" + Main.getInstance().getDescription().getVersion() + " by derive!"));
	        
	      		String version = Main.getInstance().getServer().getBukkitVersion().substring(0, 6).replace("-", "");
	      		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Server Version: " + version));
	      		return true;
	      	}
	      	if (args[0].equalsIgnoreCase("help")) {
	      		help(label, sender);
	      		return true;
	      	}
	      	return true;
	    }
	    help(label, sender);
	    return true;
	 }
	 private void help(String label, CommandSender sender) {
	    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e ------ &6Infinite Anvil Help &e------"));
	    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " help &7- Displays this page"));
	    if (sender.hasPermission("infiniteanvil.set")) {
	    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " set [radius] &7- Set anvil(s) to be infinite"));
	    }
	    if (sender.hasPermission("infiniteanvil.remove")) {
	    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " remove [radius] &7- Remove anvil(s) from infinite"));
	    }
	    if (sender.hasPermission("infiniteanvil.toggle")) {
	    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " toggle [on/off] &7- Toggle infinite anvil on place on/off"));
	    }
	    if (sender.hasPermission("infiniteanvil.purge")) {
	    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " purge &7- Clear all infinite anvils from the database"));
	    }
	    if (sender.hasPermission("infiniteanvil.reload")) {
	    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " reload &7- Reloads the configuration"));
	    }
	}
	  
	public static Set<UUID> getTogglePlayer() {
		return togglePlayer;
	}
}
