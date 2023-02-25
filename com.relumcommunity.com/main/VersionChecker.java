package com.relumcommunity.infiniteanvil.main;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class VersionChecker implements Listener{
	public String pluginVersion = Main.getInstance().getDescription().getVersion();
	public VersionChecker() {
		if (Main.getInstance().getConfig().getBoolean("settings.ConsoleCheckUpdate")) {
			try {
				HttpsURLConnection connection = (HttpsURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=69094").openConnection();
				String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
				if (!pluginVersion.equals(version)) {
					Main.getInstance().getLogger().log(Level.WARNING, " ");
					Main.getInstance().getLogger().log(Level.WARNING, "         -= InfiniteAnvil =-");
					Main.getInstance().getLogger().log(Level.WARNING, " You do not have the latest version!");
					Main.getInstance().getLogger().log(Level.WARNING, " ");
					Main.getInstance().getLogger().log(Level.WARNING, "Current: " + pluginVersion);
					Main.getInstance().getLogger().log(Level.WARNING, "Latest: " + version);
					Main.getInstance().getLogger().log(Level.WARNING, " ");
				}
				else {
					Main.getInstance().getLogger().log(Level.INFO, " ");
					Main.getInstance().getLogger().log(Level.INFO, "         -= InfiniteAnvil =-");
					Main.getInstance().getLogger().log(Level.INFO, " You are running the latest version!");
					Main.getInstance().getLogger().log(Level.INFO, " ");
				}
			}
			catch (IOException e) {
				Main.getInstance().getLogger().log(Level.SEVERE, " ");
				Main.getInstance().getLogger().log(Level.SEVERE, "         -= InfiniteAnvil =-");
				Main.getInstance().getLogger().log(Level.SEVERE, "Could not make connection to SpigotMC.org!");
				Main.getInstance().getLogger().log(Level.SEVERE, " ");
				e.printStackTrace();
			}
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent j) {
		Player p = j.getPlayer();
		if(p.hasPermission("infiniteanvil.updates") && Main.getInstance().getConfig().getBoolean("settings.CheckUpdate")) {
			try {
				HttpsURLConnection connection = (HttpsURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=69094").openConnection();
				String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
				if (!pluginVersion.equals(version)) {
					p.sendMessage(ChatColor.GRAY + "[InfiniteAnvil] " + ChatColor.RED + "You are not in the latest version, please update the plugin from our spigot page: http://bit.ly/infiniteanvil");
				}
		    }
		    catch (IOException e) {
		    	e.printStackTrace();
			}
		}
	}
}
