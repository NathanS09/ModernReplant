package fr.moderncraft.nathans.modernreplant;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;

import fr.moderncraft.nathans.modernreplant.events.EventsManager;


public class Main extends JavaPlugin{
	Main instance;
	File file = new File(getDataFolder(), "config.yml");
	FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	public static PluginManager pm = Bukkit.getPluginManager();
	public WorldGuardPlugin worldguardplugin;
	public WorldEditPlugin worldeditplugin;
	ArrayList<Player> entered = new ArrayList<>();
	ArrayList<Player> left = new ArrayList<>();
	
	@Override
	public void onEnable() {
		instance = this;
		sendLog("------------------------------");
		sendLog("        ModernReplant   ");
		sendLog("Développé par : NathanS#7932");
		sendLog("------------------------------");
		if(!file.exists()) {
			saveDefaultConfig();
		}
		save(file);
		worldguardplugin = getWorldGuard();
		
		
		EventsManager.registerEvents(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void sendLog(String log) {
		Bukkit.getConsoleSender().sendMessage(log);
	}
	
	public static Main getPlugin() {
        return Main.getPlugin(Main.class);
    }
	
	public static void save(File file) {
		try {
			Main.getPlugin().getConfig().save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		
		return (WorldGuardPlugin) plugin;
	}
	
}
