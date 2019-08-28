/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.vaincecraft.infiniteanvil.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.vaincecraft.infiniteanvil.commands.CommandHandler;
import com.vaincecraft.infiniteanvil.crafting.RemoveAnvilCrafting;
import com.vaincecraft.infiniteanvil.listeners.BreakAnvil;
import com.vaincecraft.infiniteanvil.listeners.PlaceAnvil;
import com.vaincecraft.infiniteanvil.listeners.RemoveAnvil;
import com.vaincecraft.infiniteanvil.listeners.SetAnvil;
import com.vaincecraft.infiniteanvil.listeners.UseAnvil;
import com.vaincecraft.infiniteanvil.utils.Data;
import com.vaincecraft.infiniteanvil.utils.GenerateUUID;
import com.vaincecraft.infiniteanvil.messages.LanguageFile;
import com.vaincecraft.infiniteanvil.messages.messages;

public class Main extends JavaPlugin {
	public static Main plugin;
	public Data data;
	public GenerateUUID generateUUID;
	public CommandHandler commandHandler;
	public String pluginVersion = "V.1.3";
	
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
		generateUUID = new GenerateUUID();
		commandHandler = new CommandHandler();
		
		Bukkit.getPluginManager().registerEvents(new VersionChecker(), this);
		Bukkit.getPluginManager().registerEvents(new RemoveAnvilCrafting(), this);
		getServer().getPluginManager().registerEvents(new BreakAnvil(), this);
		getServer().getPluginManager().registerEvents(new PlaceAnvil(), this);
		getServer().getPluginManager().registerEvents(new RemoveAnvil(), this);
	    getServer().getPluginManager().registerEvents(new SetAnvil(), this);
	    getServer().getPluginManager().registerEvents(new UseAnvil(), this);
	    getCommand("anvil").setExecutor(new CommandHandler());
	    
		data.setup();
		
		saveDefaultConfig();
		
		new LanguageFile();
		new messages();
	}
	public static Main getInstance() {
		return plugin;
	}
	public Data getData() {
		return data;
	}
	public GenerateUUID getGenerateUUID() {
		return generateUUID;
	}
	public CommandHandler getCommandHandler() {
		return commandHandler;
	}
	public void onDisable() {
		  plugin.getServer().getConsoleSender().sendMessage("[InfiniteAnvil] " + ChatColor.RED + "InfiniteAnvil has been disabled (" + pluginVersion + ")");
	}
	public static FileConfiguration getLangFile() {
		messages lang =  new messages();
		return lang.getFile();
	}
	public static String getLang() {
		String Lang = Main.getInstance().getConfig().getString("settings.Language");
		return Lang;
	}
}
