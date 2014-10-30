package de.gameplayingx.TeamChat2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class functions extends Main {

	public static boolean isPlayerInTC(Player p){
		boolean r;
			r = teamchat.containsKey(p.getName());
		return r;
	}

	@SuppressWarnings("deprecation")
	public static void kickPlayer(Player sender, Player target){
		if(isPlayerInTC(target)){
			if(target != sender){
				teamchat.remove(target.getName());
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(isPlayerInTC(p)){
						p.sendMessage(Tag + lang.TeamChatKick(sender, target, "chat"));
					}
				}
				target.sendMessage(Tag + lang.TeamChatKick(sender, target, "player"));
			} else {
				sender.sendMessage(Tag + ChatColor.DARK_RED + "Du Kannst dich nicht selber kicken.");
			}
		} else {
			sender.sendMessage(Tag + "§4Der Spieler ist nicht im TeamChat drinne");
		}
	}

	public static void loadConfig() {
		FileConfiguration cfg = Main.instance.getConfig();
		cfg.options().copyDefaults(true);
		Main.instance.saveConfig();
	}



	public static boolean isPlayerInTC111(Player p) {
		return true;
	}

	public static Object debugMod() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void debugMessag(String string, String message,
			CommandSender sender, Object debugMod) {
		// TODO Auto-generated method stub
		
	}

}