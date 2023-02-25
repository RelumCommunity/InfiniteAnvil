package com.relumcommunity.infiniteanvil.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import com.relumcommunity.infiniteanvil.main.Main;
import com.relumcommunity.infiniteanvil.utils.Data;
import com.relumcommunity.infiniteanvil.utils.GenerateUUID;

public class CommandHandler implements CommandExecutor {
    private static Set<UUID> togglePlayer = new HashSet<UUID>();
    private Data data = Main.getInstance().getData();
    private GenerateUUID uuid = Main.getInstance().getGenerateUUID();
    private int radius;
    private int total;
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration main = Main.getInstance().getConfig();
        FileConfiguration lang = Main.getLangFile();
        int maxradius = main.getInt("settings.max-radius");
        String prefixs = main.getString("settings.Prefix");
        String prefix = prefixs.replaceAll("&", "§");
        String alreadyset = lang.getString("messages.already-set");
        String colormsg1 = alreadyset.replaceAll("&", "§");
        String alreadyremoved = lang.getString("messages.already-removed");
        String colormsg2 = alreadyremoved.replaceAll("&", "§");
        String noneradiusset = lang.getString("messages.none-radius-set");
        String colormsg3 = noneradiusset.replaceAll("&", "§");
        String noneradiusremove = lang.getString("messages.none-radius-remove");
        String colormsg4 = noneradiusremove.replaceAll("&", "§");
        String alreadyempty = lang.getString("messages.already-empty");
        String colormsg5 = alreadyempty.replaceAll("&", "§");
        String toggleon = lang.getString("messages.toggle-on");
        String colormsg6 = toggleon.replaceAll("&", "§");
        String toggleoff = lang.getString("messages.toggle-off");
        String colormsg7 = toggleoff.replaceAll("&", "§");
        String alreadytoggledon = lang.getString("messages.already-toggled-on");
        String colormsg8 = alreadytoggledon.replaceAll("&", "§");
        String alreadytoggledoff = lang.getString("messages.already-toggled-off");
        String colormsg9 = alreadytoggledoff.replaceAll("&", "§");
        String reload = lang.getString("messages.reload");
        String colormsg10 = reload.replaceAll("&", "§");
        String numberzero = lang.getString("messages.number-zero");
        String colormsg11 = numberzero.replaceAll("&", "§");
        String numbernegative = lang.getString("messages.number-negative");
        String colormsg12 = numbernegative.replaceAll("&", "§");
        String invalidblock = lang.getString("messages.invalid-block");
        String colormsg13 = invalidblock.replaceAll("&", "§");
        String nopermcmd = lang.getString("messages.no-perm-cmd");
        String colormsg14 = nopermcmd.replaceAll("&", "§");
        String invalidsyntaxs = lang.getString("messages.invalid-syntax");
        String invalidsyntax = invalidsyntaxs.replaceAll("&", "§");
        String numberabovemax = lang.getString("messages.number-above-max");
        String colormsg15 = numberabovemax.replace("%radius%", Integer.toString(maxradius));
        String anvilsets = lang.getString("messages.anvil-set");
        String anvilset = anvilsets.replaceAll("&", "§");
        String anvilremoves = lang.getString("messages.anvil-remove");
        String anvilremove = anvilremoves.replaceAll("&", "§");
        String radiussets = lang.getString("messages.radius-set");
        String radiusset = radiussets.replaceAll("&", "§");
        String radiusremoves = lang.getString("messages.radius-remove");
        String radiusremove = radiusremoves.replaceAll("&", "§");
        String datapurges = lang.getString("messages.data-purge");
        String datapurge = datapurges.replaceAll("&", "§");
        String invalidnumbers = lang.getString("messages.invalid-number");
        String invalidnumber = invalidnumbers.replaceAll("&", "§");
        String anvilgui = lang.getString("guiname.anvil");
        String colormsg16 = anvilgui.replaceAll("&", "§");
        int length = args.length;
        if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
            Player p = null;
            if (sender instanceof Player) {
                p = (Player)sender;
            }
            if (length > 0) {
                if (args[0].equalsIgnoreCase("open")) {
                	if (sender.hasPermission("infiniteanvil.open")) {
                		Inventory inv = Bukkit.createInventory(null, InventoryType.ANVIL, colormsg16);
                		p.openInventory(inv);
                	}
                	else {
                		sender.sendMessage(prefix + colormsg14);
                		return true;
                	}
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (!sender.hasPermission("infiniteanvil.set")) {
                        sender.sendMessage(prefix + colormsg14);
                        return true;
                    }
                    else if (length > 1) {
                        try {
                            radius = Integer.parseInt(args[1]);
                        }
                        catch (NumberFormatException e) {
                            String colormsg17 = invalidnumber.replace("%number%", args[1]);
                            sender.sendMessage(prefix + colormsg17);
                            return true;
                        }
                        if (radius == 0) {
                            sender.sendMessage(prefix + colormsg11);
                            return true;
                        }
                        if (radius < 0) {
                            sender.sendMessage(prefix + colormsg12);
                            return true;
                        }
                        if (radius > main.getInt("settings.max-radius")) {
                            sender.sendMessage(prefix + colormsg15);
                            return true;
                        }
                        total = 0;
                        Block b = p.getLocation().getBlock();
                        new BukkitRunnable() {
                            @SuppressWarnings("deprecation")
							public void run() {
                                for (int x = -radius; x <= radius; ++x) {
                                    for (int y = -radius; y <= radius; ++y) {
                                        for (int z = -radius; z <= radius; ++z) {
                                            if (b.getRelative(x, y, z).getType() == Material.ANVIL) {
                                                Block foundAnvil = b.getRelative(x, y, z);
                                                if (!data.checkData(foundAnvil)) {
                                                    Location l = foundAnvil.getLocation();
                                                    int anvilX = l.getBlockX();
                                                    int anvilY = l.getBlockY();
                                                    int anvilZ = l.getBlockZ();
                                                    data.getLoadData().set(uuid.generateUUID().toString(), foundAnvil.getWorld().getName() + ", " + anvilX + ", " + anvilY + ", " + anvilZ + ", " + foundAnvil.getData());
                                                }
                                            }
                                        }
                                    }
                                }
                                if (total == 0) {
                                    sender.sendMessage(prefix + colormsg3);
                                }
                                else {
                                    data.saveData();
                                    String colormsg19 = radiusset.replace("%amount%", Integer.toString(total)).replace("%radius%", Integer.toString(maxradius));
                                    sender.sendMessage(prefix + colormsg19);
                                }
                            }
                        }.runTaskAsynchronously(Main.getInstance());
                        return true;
                    }
                    else {
                        @SuppressWarnings({ "unchecked", "rawtypes" })
						Block b = p.getTargetBlock((Set)null, 25);
                        if (b.getType() != Material.ANVIL) {
                            sender.sendMessage(prefix + colormsg13);
                            return true;
                        }
                        Location l = b.getLocation();
                        int x = l.getBlockX();
                        int y = l.getBlockY();
                        int z = l.getBlockZ();
                        @SuppressWarnings("deprecation")
						Byte blockData = b.getData();
                        if (data.checkData(b)) {
                            sender.sendMessage(prefix + colormsg1);
                            return true;
                        }
                        data.getLoadData().set(uuid.generateUUID().toString(), b.getWorld().getName() + ", " + x + ", " + y + ", " + z + ", " + blockData);
                        data.saveData();
                        String colormsg18 = anvilset.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
                        sender.sendMessage(prefix + colormsg18);
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("unset")) {
                    if (!sender.hasPermission("infiniteanvil.remove")) {
                        sender.sendMessage(prefix + colormsg14);
                        return true;
                    }
                    if (length > 1) {
                        try {
                            radius = Integer.parseInt(args[1]);
                        }
                        catch (NumberFormatException e) {
                            String colormsg17 = invalidnumber.replace("%number%", args[1]);
                            sender.sendMessage(prefix + colormsg17);
                            return true;
                        }
                        if (radius == 0) {
                            sender.sendMessage(prefix + colormsg11);
                            return true;
                        }
                        if (radius < 0) {
                            sender.sendMessage(prefix + colormsg12);
                            return true;
                        }
                        if (radius > main.getInt("settings.max-radius")) {
                            sender.sendMessage(prefix + colormsg15);
                            return true;
                        }
                        total = 0;
                        Block b = p.getLocation().getBlock();
                        new BukkitRunnable() {
                            public void run() {
                                for (int x = -radius; x <= radius; ++x) {
                                    for (int y = -radius; y <= radius; ++y) {
                                        for (int z = -radius; z <= radius; ++z) {
                                            if (b.getRelative(x, y, z).getType() == Material.ANVIL) {
                                                Block foundAnvil = b.getRelative(x, y, z);
                                                if (data.checkData(foundAnvil)) {
                                                    data.getLoadData().set(data.getUUID(), null);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (total == 0) {
                                    sender.sendMessage(prefix + colormsg4);
                                }
                                else {
                                    data.saveData();
                                    String colormsg20 = radiusremove.replace("%amount%", Integer.toString(total)).replace("%radius%", Integer.toString(maxradius));
                                    sender.sendMessage(prefix + colormsg20);
                                }
                            }
                        }.runTaskAsynchronously(Main.getInstance());
                        return true;
                    }
                    else {
                        @SuppressWarnings({ "rawtypes", "unchecked" })
						Block b = p.getTargetBlock((Set)null, 25);
                        if (b.getType() != Material.ANVIL) {
                            sender.sendMessage(prefix + colormsg13);
                            return true;
                        }
                        if (data.checkData(b)) {
                            Location l = b.getLocation();
                            int x = l.getBlockX();
                            int y = l.getBlockY();
                            int z = l.getBlockZ();
                            data.getLoadData().set(data.getUUID(), null);
                            data.saveData();
                            String colormsg19 = anvilremove.replace("%x%", Integer.toString(x)).replace("%y%", Integer.toString(y)).replace("%z%", Integer.toString(z));
                            sender.sendMessage(prefix + colormsg19);
                            return true;
                        }
                        sender.sendMessage(prefix + colormsg2);
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("toggle")) {
                    if (!sender.hasPermission("infiniteanvil.toggle")) {
                        sender.sendMessage(prefix + colormsg14);
                        return true;
                    }
                    if (length > 1) {
                        if (args[1].equalsIgnoreCase("off")) {
                            if (!togglePlayer.contains(p.getUniqueId())) {
                                sender.sendMessage(prefix + colormsg9);
                                return true;
                            }
                            sender.sendMessage(String.valueOf(prefix) + colormsg7);
                            togglePlayer.remove(p.getUniqueId());
                            return true;
                        }
                        else {
                            if (!args[1].equalsIgnoreCase("on")) {
                                String colormsg20 = invalidsyntax.replace("%label%", label);
                                sender.sendMessage(prefix + colormsg20);
                                return true;
                            }
                            if (togglePlayer.contains(p.getUniqueId())) {
                                sender.sendMessage(prefix + colormsg8);
                                return true;
                            }
                            sender.sendMessage(prefix + colormsg6);
                            togglePlayer.add(p.getUniqueId());
                            return true;
                        }
                    }
                    else {
                        if (togglePlayer.contains(p.getUniqueId())) {
                            sender.sendMessage(prefix + colormsg7);
                            togglePlayer.remove(p.getUniqueId());
                            return true;
                        }
                        sender.sendMessage(prefix + colormsg6);
                        togglePlayer.add(p.getUniqueId());
                        return true;
                    }
                }
                else {
                    if (args[0].equalsIgnoreCase("purge") && sender.hasPermission("infiniteanvil.purge")) {
                        new BukkitRunnable() {
                            public void run() {
                                data.clearData();
                                if (data.getAnvils() == 0) {
                                    sender.sendMessage(prefix + colormsg5);
                                }
                                else {
                                    String colormsg21 = datapurge.replace("%amount%", Integer.toString(data.getAnvils()));
                                    sender.sendMessage(prefix + colormsg21);
                                }
                            }
                        }.runTaskAsynchronously(Main.getInstance());
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (sender.hasPermission("infiniteanvil.reload")) {
                            Main.getInstance().reloadConfig();
                            Main.getInstance().CustomCrafting();
                            sender.sendMessage(prefix + colormsg10);
                            return true;
                        }
                        sender.sendMessage(prefix + colormsg14);
                        return true;
                    }
                    else {
                        if (args[0].equalsIgnoreCase("help")) {
                            help(label, sender);
                            return true;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void help(String label, CommandSender sender) {
        FileConfiguration lang = Main.getLangFile();
        String pluginVersion = Main.getInstance().getDescription().getVersion();
        List<?> help = new ArrayList<Object>();
        help.clear();
        help = (List<?>)lang.getList("messages.Help");
        for (int size = help.size(), i = 0; i < size; ++i) {
            String helps = new StringBuilder().append(help.get(i)).toString();
            String help2 = helps.replaceAll("&", "§");
            String pluginVersions = help2.replace("%label%", label);
            String colormsg23 = pluginVersions.replace("%ver%", pluginVersion);
            sender.sendMessage(colormsg23);
        }
    }
    
    public static Set<UUID> getTogglePlayer() {
        return togglePlayer;
    }
}
