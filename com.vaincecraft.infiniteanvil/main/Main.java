/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.vaincecraft.infiniteanvil.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.vaincecraft.infiniteanvil.commands.CommandHandler;
import com.vaincecraft.infiniteanvil.configurations.Configuration;
import com.vaincecraft.infiniteanvil.listeners.BreakAnvil;
import com.vaincecraft.infiniteanvil.listeners.PlaceAnvil;
import com.vaincecraft.infiniteanvil.listeners.RemoveAnvil;
import com.vaincecraft.infiniteanvil.listeners.SetAnvil;
import com.vaincecraft.infiniteanvil.listeners.UseAnvil;
import com.vaincecraft.infiniteanvil.utils.Data;
import com.vaincecraft.infiniteanvil.utils.GenerateUUID;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public static Main plugin;
	public Data data;
	public Configuration configuration;
	public GenerateUUID generateUUID;
	public CommandHandler commandHandler;
	public String pluginVersion = "V.1.0";
	
	public void onEnable() {
		plugin = this;
		
		String versioncheck = Bukkit.getVersion();
		String version[] = versioncheck.split(" ");
		if (Bukkit.getVersion().contains("Spigot")) {
			String servercheck = Bukkit.getVersion();
			String server[] = servercheck.split("-");
			plugin.getServer().getConsoleSender().sendMessage("[InfiniteAnvil INFO] " + ChatColor.YELLOW + "InfiniteAnvil using: " + server[1] + " version " + version[1] + version[2]);
		}
		else plugin.getServer().getConsoleSender().sendMessage("[InfiniteAnvil INFO] " + ChatColor.YELLOW + "InfiniteAnvil using: " + Bukkit.getVersion() + ChatColor.RED + ("UNTESTED SERVER VERSION"));
		  
		plugin.getServer().getConsoleSender().sendMessage("[InfiniteAnvil] " + ChatColor.GREEN + "InfiniteAnvil has been enabled (" + pluginVersion + ")");
		
		data = new Data();
		configuration = new Configuration();
		generateUUID = new GenerateUUID();
		commandHandler = new CommandHandler();
		
		Bukkit.getPluginManager().registerEvents(new VersionChecker(), this);
		getServer().getPluginManager().registerEvents(new BreakAnvil(), this);
		getServer().getPluginManager().registerEvents(new PlaceAnvil(), this);
		getServer().getPluginManager().registerEvents(new RemoveAnvil(), this);
	  getServer().getPluginManager().registerEvents(new SetAnvil(), this);
	  getServer().getPluginManager().registerEvents(new UseAnvil(), this);
	  getCommand("anvil").setExecutor(new CommandHandler());
	    
		data.setup();
		
		saveDefaultConfig();
	}
	public static Main getInstance() {
		return plugin;
	}
	public Data getData() {
		return data;
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public GenerateUUID getGenerateUUID() {
		return generateUUID;
	}
	public CommandHandler getCommandHandler() {
		return commandHandler;
	}
	public void onDenyBreak(CommandSender sender) {
		configuration.noPermBreakMessage(sender);
	}
	public void onDisable() {
		  plugin.getServer().getConsoleSender().sendMessage("[InfiniteAnvil] " + ChatColor.RED + "InfiniteAnvil has been disabled (" + pluginVersion + ")");
	  }
}
