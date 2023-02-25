/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.relumcommunity.infiniteanvil.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.relumcommunity.infiniteanvil.commands.CommandHandler;
import com.relumcommunity.infiniteanvil.crafting.RemoveAnvilCrafting;
import com.relumcommunity.infiniteanvil.listeners.BreakAnvil;
import com.relumcommunity.infiniteanvil.listeners.PlaceAnvil;
import com.relumcommunity.infiniteanvil.listeners.RemoveAnvil;
import com.relumcommunity.infiniteanvil.listeners.SetAnvil;
import com.relumcommunity.infiniteanvil.listeners.UseAnvil;
import com.relumcommunity.infiniteanvil.utils.Data;
import com.relumcommunity.infiniteanvil.utils.GenerateUUID;
import com.relumcommunity.infiniteanvil.messages.LanguageFile;
import com.relumcommunity.infiniteanvil.messages.messages;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public static Main plugin;
	public Data data;
	public GenerateUUID generateUUID;
	public CommandHandler commandHandler;
	public String pluginVersion = "V.2.0";
	
	public void onEnable() {
		plugin = this;
		
        String ver = Bukkit.getVersion();
        ConsoleCommandSender cmsg = plugin.getServer().getConsoleSender();
        PluginManager bpm = Bukkit.getPluginManager();
        String versioncheck = ver;
        String[] version = versioncheck.split(" ");
        String servercheck = ver;
        String[] server = servercheck.split("-");
		if (Bukkit.getVersion().contains("Spigot")) {
			cmsg.sendMessage("[InfiniteAnvil INFO] " + ChatColor.YELLOW + "InfiniteAnvil using: " + server[1] + " version " + version[1] + version[2]);
		}
		else {
			cmsg.sendMessage("[InfiniteAnvil INFO] " + ChatColor.YELLOW + "InfiniteAnvil using: " + Bukkit.getVersion() + ChatColor.RED + ("UNTESTED SERVER VERSION"));
		}
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
		CustomCrafting();
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
		if (getInstance().getConfig().getBoolean("settings.CustomAnvilRecipe")) {
			ItemStack anvil = new ItemStack(Material.ANVIL);
	        ItemMeta itemMeta = anvil.getItemMeta();
	        if (getInstance().getConfig().getBoolean("settings.Lore")) {
	            List<String> customlore = new ArrayList<String>();
	            String anvilLore = getLangFile().getString("customanvilsettings.lore");
	            String colormsg2 = anvilLore.replaceAll("&", "§");
	            customlore.clear();
	            customlore.add(colormsg2);
	            itemMeta.setLore(customlore);
	        }
	        if (getInstance().getConfig().getBoolean("settings.Name")) {
	            String anvilname = getLangFile().getString("customanvilsettings.name");
	            String colormsg3 = anvilname.replaceAll("&", "§");
	            itemMeta.setDisplayName(colormsg3);
	        }
	        anvil.setItemMeta(itemMeta);
	        ShapedRecipe recipe = new ShapedRecipe(anvil);
	        FileConfiguration main = getInstance().getConfig();
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
	            c = "AIR";
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
	        recipe.shape(new String[] { "ABC", "DEF", "GHI" });
	        if (!a.equals("AIR")) {
	            recipe.setIngredient('A', Material.getMaterial(a));
	        }
	        if (!b.equals("AIR")) {
	            recipe.setIngredient('B', Material.getMaterial(b));
	        }
	        if (!c.equals("AIR")) {
	            recipe.setIngredient('C', Material.getMaterial(c));
	        }
	        if (!d.equals("AIR")) {
	            recipe.setIngredient('D', Material.getMaterial(d));
	        }
	        if (!e.equals("AIR")) {
	            recipe.setIngredient('E', Material.getMaterial(e));
	        }
	        if (!f.equals("AIR")) {
	            recipe.setIngredient('F', Material.getMaterial(f));
	        }
	        if (!g.equals("AIR")) {
	            recipe.setIngredient('G', Material.getMaterial(g));
	        }
	        if (!h.equals("AIR")) {
	            recipe.setIngredient('H', Material.getMaterial(h));
	        }
	        if (!i.equals("AIR")) {
	            recipe.setIngredient('I', Material.getMaterial(i));
	        }
		    getInstance().getServer().addRecipe(recipe);
		}
    }
}
