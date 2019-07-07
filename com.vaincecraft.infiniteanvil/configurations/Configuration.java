/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.vaincecraft.infiniteanvil.configurations;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vaincecraft.infiniteanvil.main.Main;

public class Configuration {
	public boolean notifyMessages() {
		return Main.getInstance().getConfig().getBoolean("settings.notify");
	}
	public boolean shiftClick() {
		return Main.getInstance().getConfig().getBoolean("settings.shift-click");
	}
	public int maxRadius() {
		return Main.getInstance().getConfig().getInt("settings.max-radius");
	}
	public boolean protectInfiniteAnvils() {
		return Main.getInstance().getConfig().getBoolean("settings.protect-infinite-anvils");
 	}
	public void anvilSetMessage(Player p, int x, int y, int z) {
		String message = Main.getInstance().getConfig().getString("messages.anvil-set");
		message = message.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
		p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void anvilRemoveMessage(Player p, int x, int y, int z) {
		String message = Main.getInstance().getConfig().getString("messages.anvil-remove");
		message = message.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
		p.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void alreadySetMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.already-set");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void alreadyRemovedMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.already-removed");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void radiusSetMessage(CommandSender sender, int amount, int radius) {
		String message = Main.getInstance().getConfig().getString("messages.radius-set");
		message = message.replace("%amount%", Integer.toString(amount)).replace("%radius%", Integer.toString(radius));
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void radiusRemoveMessage(CommandSender sender, int amount, int radius) {
		String message = Main.getInstance().getConfig().getString("messages.radius-remove");
		message = message.replace("%amount%", Integer.toString(amount)).replace("%radius%", Integer.toString(radius));
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void noneRadiusSetMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.none-radius-set");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void noneRadiusRemoveMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.none-radius-remove");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void dataPurgeMessage(CommandSender sender, int amount) {
		String message = Main.getInstance().getConfig().getString("messages.data-purge");
		message = message.replace("%amount%", Integer.toString(amount));
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void alreadyEmptyMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.already-empty");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void toggleOnMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.toggle-on");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void toggleOffMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.toggle-off");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void alreadyToggledOnMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.already-toggled-on");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void alreadyToggledOffMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.already-toggled-off");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void reloadMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.reload");
		sender.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void numberZeroMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.number-zero");
		sender.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void numberNegativeMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.number-negative");
		sender.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void numberAboveMaxMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.number-above-max");
		message = message.replace("%radius%", Integer.toString(maxRadius()));
		sender.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void invalidBlockMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.invalid-block");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void invalidNumberMessage(CommandSender sender, String number) {
		String message = Main.getInstance().getConfig().getString("messages.invalid-number");
		message = message.replace("%number%", number);
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void invalidSyntaxMessage(CommandSender sender, String command) {
		String message = Main.getInstance().getConfig().getString("messages.invalid-syntax");
		message = message.replace("%command%", command);
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void noPermBreakMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.no-perm-break");
		sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void noPermSetMessage(Player p) {
		String message = Main.getInstance().getConfig().getString("messages.no-perm-set");
		p.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void noPermRemoveMessage(Player p) {
		String message = Main.getInstance().getConfig().getString("messages.no-perm-remove");
		p.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void noPermUseMessage(Player p) {
		String message = Main.getInstance().getConfig().getString("messages.no-perm-use");
		p.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
	public void noPermCmdMessage(CommandSender sender) {
		String message = Main.getInstance().getConfig().getString("messages.no-perm-cmd");
		sender.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
	}
}
