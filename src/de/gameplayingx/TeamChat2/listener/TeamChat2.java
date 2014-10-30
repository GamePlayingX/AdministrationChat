package de.gameplayingx.TeamChat2.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.gameplayingx.TeamChat2.Main;
import de.gameplayingx.TeamChat2.functions;
import de.gameplayingx.TeamChat2.lang;

public class TeamChat2 extends Main implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTeamChatEvent(AsyncPlayerChatEvent event){
		if(functions.isPlayerInTC(event.getPlayer())){
			if(!(teamchat.isEmpty())){
				if(!(event.getMessage().startsWith(lang.TeamChatSpecialChar()))){
					for(Player p : Bukkit.getServer().getOnlinePlayers()){
						if(functions.isPlayerInTC(p)){
							p.sendMessage(Tag + lang.TeamChatMessage(event.getPlayer(), event.getMessage()));
							event.setCancelled(true);
						}
					}
				} else {
					event.setMessage(event.getMessage().replaceAll(lang.TeamChatSpecialChar(), ""));
				}
			}	
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler 
	public void onTeamChatLeave(PlayerQuitEvent event){
		if(functions.isPlayerInTC(event.getPlayer())){
			if(config.getBoolean("tc.general.kick-offline-players")){
				teamchat.remove(event.getPlayer().getName());
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(functions.isPlayerInTC(p)){
						p.sendMessage(Tag + lang.TeamChatLeave(event.getPlayer(), "chat"));
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler 
	public void onTeamChatKick(PlayerKickEvent event){
		if(functions.isPlayerInTC(event.getPlayer())){
			if(config.getBoolean("tc.general.kick-offline-players")){
				TeamChat2.remove(event.getPlayer().getName());
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(functions.isPlayerInTC111(p)){
						p.sendMessage(Tag + lang.TeamChatLeave(event.getPlayer(), "chat"));
					}
				}
			}
		}
	}

	public static void remove(String name) {
		
	}




}
