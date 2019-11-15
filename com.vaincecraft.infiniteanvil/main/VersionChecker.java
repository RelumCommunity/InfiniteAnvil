package com.vaincecraft.infiniteanvil.main;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VersionChecker implements Listener {
	public String pluginVersion = Main.getInstance().getDescription().getVersion();
	public VersionChecker() {
		FileConfiguration main = Main.getInstance().getConfig();
		Logger mlog = Main.getInstance().getLogger();
		if (main.getBoolean("settings.ConsoleCheckUpdate")) {
			try {
				HttpURLConnection connection = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=69094").openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
				if (!pluginVersion.equals(version)) {
					mlog.log(Level.WARNING, " ");
					mlog.log(Level.WARNING, "         -= InfiniteAnvil =-");
					mlog.log(Level.WARNING, "You do not have the latest version!");
					mlog.log(Level.WARNING, " ");
					mlog.log(Level.WARNING, "Current: " + pluginVersion);
					mlog.log(Level.WARNING, "Latest: " + version);
					mlog.log(Level.WARNING, " ");
				}
				else {
					mlog.log(Level.INFO, " ");
					mlog.log(Level.INFO, "         -= InfiniteAnvil =-");
					mlog.log(Level.INFO, "You are running the latest version!");
					mlog.log(Level.INFO, " ");
				}
			}
			catch (IOException e) {
				mlog.log(Level.SEVERE, " ");
				mlog.log(Level.SEVERE, "         -= InfiniteAnvil =-");
				mlog.log(Level.SEVERE, "Could not make connection to SpigotMC.org!");
				mlog.log(Level.SEVERE, " ");
				e.printStackTrace();
			}
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent j) {
		Player p = j.getPlayer();
		FileConfiguration main = Main.getInstance().getConfig();
		if(p.hasPermission("infiniteanvil.updates") && main.getBoolean("settings.CheckUpdate")) {
			try {
				HttpURLConnection connection = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=69094").openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
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
