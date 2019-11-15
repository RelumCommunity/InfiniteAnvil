/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.vaincecraft.infiniteanvil.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
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
	public String pluginVersion = "V.1.6";
	
	public void onEnable() {
		plugin = this;
		String ver = Bukkit.getVersion();
		ConsoleCommandSender cmsg = plugin.getServer().getConsoleSender();
		PluginManager bpm = Bukkit.getPluginManager();
		
		String versioncheck = ver;
		String version[] = versioncheck.split(" ");
		if (ver.contains("Spigot")) {
			String servercheck = ver;
			String server[] = servercheck.split("-");
			cmsg.sendMessage("[InfiniteAnvil INFO] " + ChatColor.YELLOW + "InfiniteAnvil using: " + server[1] + " version " + version[1] + version[2]);
		}
		else cmsg.sendMessage("[InfiniteAnvil INFO] " + ChatColor.YELLOW + "InfiniteAnvil using: " + ver + ChatColor.RED + ("UNTESTED SERVER VERSION"));
		  
		cmsg.sendMessage("[InfiniteAnvil] " + ChatColor.GREEN + "InfiniteAnvil has been enabled (" + pluginVersion + ")");
		
		data = new Data();
		generateUUID = new GenerateUUID();
		commandHandler = new CommandHandler();
		
		bpm.registerEvents(new VersionChecker(), this);
		bpm.registerEvents(new RemoveAnvilCrafting(), this);
		bpm.registerEvents(new BreakAnvil(), this);
		bpm.registerEvents(new PlaceAnvil(), this);
		bpm.registerEvents(new RemoveAnvil(), this);
		bpm.registerEvents(new SetAnvil(), this);
		bpm.registerEvents(new UseAnvil(), this);
	    getCommand("anvil").setExecutor(new CommandHandler());
	    
		data.setup();
		
		saveDefaultConfig();
		
		new LanguageFile();
		new messages();
		if (Main.getInstance().getConfig().getBoolean("settings.CustomAnvilRecipe")) {
			CustomCrafting();
		}
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
		ConsoleCommandSender cmsg = plugin.getServer().getConsoleSender();
		cmsg.sendMessage("[InfiniteAnvil] " + ChatColor.RED + "InfiniteAnvil has been disabled (" + pluginVersion + ")");
	}
	public static FileConfiguration getLangFile() {
		messages lang =  new messages();
		return lang.getFile();
	}
	public static String getLang() {
		String Lang = Main.getInstance().getConfig().getString("settings.Language");
		return Lang;
	}
	public void CustomCrafting() {
		ItemStack anvil = new ItemStack(Material.ANVIL);
		ItemMeta itemMeta = anvil.getItemMeta();
		if (Main.getInstance().getConfig().getBoolean("settings.Lore")) {
			List<String> customlore = new ArrayList<String>();
			String anvilLore = Main.getLangFile().getString("customanvilsettings.lore");
			String colormsg2 = anvilLore.replaceAll("&", "§");
			customlore.clear();
			customlore.add(colormsg2);
			itemMeta.setLore(customlore);
		}
		if (Main.getInstance().getConfig().getBoolean("settings.Name")) {
			String anvilname =  Main.getLangFile().getString("customanvilsettings.name");
			String colormsg1 = anvilname.replaceAll("&", "§");
			itemMeta.setDisplayName(colormsg1);
		}
		anvil.setItemMeta(itemMeta);
		ShapedRecipe recipe = new ShapedRecipe(anvil);
		FileConfiguration main = Main.getInstance().getConfig();
		//RecipeList
		String a = main.getString("recipe.A");
		if (a == null) {
			a = "AIR";
		}
		String b = main.getString("recipe.B");
		if (b == null) {
			b = "AIR";
		}
		String c = main.getString("recipe.C");
		if (c == null) {
			c= "AIR";
		}
		String d = main.getString("recipe.D");
		if (d == null) {
			d = "AIR";
		}
		String e = main.getString("recipe.E");
		if (e == null) {
			e = "AIR";
		}
		String f = main.getString("recipe.F");
		if (f == null) {
			f = "AIR";
		}
		String g = main.getString("recipe.G");
		if (g == null) {
			g = "AIR";
		}
		String h = main.getString("recipe.H");
		if (h == null) {
			h = "AIR";
		}
		String i = main.getString("recipe.I");
		if (i == null) {
			i = "AIR";
		}
		
		recipe.shape("ABC", "DEF", "GHI");
		if (!(a.equals("AIR"))) {
			recipe.setIngredient('A', Material.getMaterial(a));
		}
		if (!(b.equals("AIR"))) {
			recipe.setIngredient('B', Material.getMaterial(b));
		}
		if (!(c.equals("AIR"))) {
			recipe.setIngredient('C', Material.getMaterial(c));
		}
		if (!(d.equals("AIR"))) {
			recipe.setIngredient('D', Material.getMaterial(d));
		}
		if (!(e.equals("AIR"))) {
			recipe.setIngredient('E', Material.getMaterial(e));
		}
		if (!(f.equals("AIR"))) {
			recipe.setIngredient('F', Material.getMaterial(f));
		}
		if (!(g.equals("AIR"))) {
			recipe.setIngredient('G', Material.getMaterial(g));
		}
		if (!(h.equals("AIR"))) {
			recipe.setIngredient('H', Material.getMaterial(h));
		}
		if (!(i.equals("AIR"))) {
			recipe.setIngredient('I', Material.getMaterial(i));
		}
		Main.getInstance().getServer().addRecipe(recipe);
	}
}
