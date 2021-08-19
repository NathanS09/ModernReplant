package fr.moderncraft.nathans.modernreplant.events;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import fr.moderncraft.nathans.modernreplant.Main;


public class BlocksEvent implements Listener {
	WorldGuardPlugin worldguardplugin;
	public WorldEditPlugin worldeditplugin;
	File file = new File(Main.getPlugin().getDataFolder(), "config.yml");
	FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	List<String> listZones = config.getStringList("zones");
	
	

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
			  Block block = e.getBlock();
			  WorldGuardPlatform platform = WorldGuard.getInstance().getPlatform();
			  RegionContainer container = platform.getRegionContainer();
			  RegionManager regionManager = container.get(BukkitAdapter.adapt(e.getPlayer().getWorld()));
			  Location loc = e.getBlock().getLocation();
			  
			  for(ProtectedRegion pr : regionManager.getRegions().values()) {

				  if(listZones.contains(pr.getId())) {
					  if(pr.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
						  if((block.getType() == Material.WHEAT && config.getBoolean("agriculture.ble") == true && block.getData() == 7) || (block.getType() == Material.CARROTS && config.getBoolean("agriculture.carotte") == true  && block.getData() == 7) || (block.getType() == Material.POTATOES && config.getBoolean("agriculture.patate") == true && block.getData() == 7) || (block.getType() == Material.NETHER_WART && config.getBoolean("agriculture.verrue") == true && block.getData() == 3)) {
							// C'est ble, carrote, patate ou verrue et c'est en true
							  e.setCancelled(true);
							  Material mat = loc.getBlock().getType();
							  loc.getBlock().breakNaturally();
							  loc.getBlock().setType(mat);
							  return;
						  }else {
							 if(e.getPlayer().isOp()) {
								 e.setCancelled(false);
								 return;
							 } else {
								 e.setCancelled(true);
								 return;
							 }
						  }
						  
					  } 
				  } 
			  }
	}
	
	@EventHandler
	public void noUproot(PlayerInteractEvent event)
	{
		  WorldGuardPlatform platform = WorldGuard.getInstance().getPlatform();
		  RegionContainer container = platform.getRegionContainer();
		  RegionManager regionManager = container.get(BukkitAdapter.adapt(event.getPlayer().getWorld()));
		  Block block = event.getClickedBlock();
		  for(ProtectedRegion pr : regionManager.getRegions().values()) {

			  if(listZones.contains(pr.getId())) {
			    if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.FARMLAND) {
			    	 event.setCancelled(true);
			    	 return;
			    }
			    
				if(event.getClickedBlock().getType() == Material.WHEAT ||event.getClickedBlock().getType() == Material.CARROTS ||event.getClickedBlock().getType() == Material.POTATOES ||event.getClickedBlock().getType() == Material.NETHER_WART ) {
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						event.setCancelled(true);
					}
				}
			  }
		  }
	       
	}
	

	 
	
}
