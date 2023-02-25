/**
 * 
 */
/**
 * @author nato3623
 *
 */
package com.relumcommunity.infiniteanvil.utils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.relumcommunity.infiniteanvil.main.Main;

public class Data {
	private FileConfiguration loadData;
	private File data;
	private Byte blockData;
	private String UUID;
	private int anvils;
  
	public void saveData() {
		try {
			loadData.save(data);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public boolean checkData(Block anvil) {
		Location l = anvil.getLocation();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
    
		Set<String> anvilList = Main.getInstance().getData().getLoadData().getConfigurationSection("").getKeys(false);
		for (String getAnvil : anvilList) {
			String[] anvilData = Main.getInstance().getData().getLoadData().getString(getAnvil).split(", ");
			if ((anvil.getWorld().getName().equals(anvilData[0])) && (Integer.toString(x).equals(anvilData[1])) && (Integer.toString(y).equals(anvilData[2])) && (Integer.toString(z).equals(anvilData[3]))) {
				blockData = Byte.valueOf(Byte.parseByte(anvilData[4]));
				UUID = getAnvil;
				return true;
			}
		}
		return false;
	}
  
	public Byte getBlockData() {
		return blockData;
	}
	public String getUUID() {
		return UUID;
	}
	public void clearData() {
		anvils = 0;
		Set<String> anvilList = Main.getInstance().getData().getLoadData().getConfigurationSection("").getKeys(false);
		for (String getAnvil : anvilList) {
			Main.getInstance().getData().getLoadData().set(getAnvil, null);
			anvils += 1;
		}
	}
	public int getAnvils() {
		return anvils;
	}
	public void setup() {
		data = new File(Main.getInstance().getDataFolder(), "data.yml");
		loadData = YamlConfiguration.loadConfiguration(data);
	}
	public FileConfiguration getLoadData() {
		return loadData;
	}
	public File getData() {
		return data;
	}
}