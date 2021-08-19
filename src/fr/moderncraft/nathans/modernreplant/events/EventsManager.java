package fr.moderncraft.nathans.modernreplant.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.moderncraft.nathans.modernreplant.Main;


public class EventsManager {

	 public static void registerEvents(Main main) {
	        PluginManager pm = Bukkit.getPluginManager();
	       
	      pm.registerEvents(new BlocksEvent(), main);
	        
	 }
	
	
}
