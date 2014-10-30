package de.gameplayingx.TeamChat2;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.gameplayingx.TeamChat2.listener.TeamChat2;


public class Main extends JavaPlugin {

	public static Logger logger = Logger.getLogger("Minecraft");
	public static boolean CanUseGoldenTouch;

	public static String Tag           =   "§6[§bTeamChat§6]";
	public static String noPermissions = Tag + ChatColor.AQUA + "Sry, aber du hast keine Rechte um diesen Befehl nutzen zu können.";
	protected static FileConfiguration config;
	public static Main instance;
	public static HashMap<String, String> teamchat = new HashMap<String, String>();

	public void onEnable(){
			instance = this;
			config = getConfig();
			functions.loadConfig();
			logger.info("[TeamChat2] is now enabled.");
			getServer().getPluginManager().registerEvents(new TeamChat2(), this);
			logger.info("[TeamChat2] Createt by RikuX17 (GamePlayingX)");

	}

	public void onDisable(){
		logger.info("[TeamChat2] is now disabled.");
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){

		if(sender instanceof Player){

			Player pl = (Player)sender;

			  if(commandLable.equalsIgnoreCase("tc") || commandLable.equalsIgnoreCase("teamchat")){
				  if(pl.hasPermission("tc.use")){
					  if(args.length == 0){
						  if(functions.isPlayerInTC(pl)){
						  	teamchat.remove(pl.getName());
						  	pl.sendMessage(Tag + lang.TeamChatLeave(pl, "player"));
						  	for(Player p : getServer().getOnlinePlayers()){
							  	if(functions.isPlayerInTC(p)){
								  	if(!(p.getName().equalsIgnoreCase(pl.getName()))){
									  	p.sendMessage(Tag + lang.TeamChatLeave(pl, "chat"));
								  	}
							  	}
						  	}
						  	return true;
					  	} else {
						    teamchat.put(pl.getName(), pl.getName());
						  	pl.sendMessage(Tag + lang.TeamChatJoin(pl, "player"));
						  	for(Player p : getServer().getOnlinePlayers()){
							  	if(functions.isPlayerInTC(p)){
								  	if(!(p.getName().equalsIgnoreCase(pl.getName()))){
									  	p.sendMessage(Tag + lang.TeamChatJoin(pl, "chat"));
								  	}
							  	}
						  	}
						  	return true;
					  	}
					  } else {
						  if(args[0].equalsIgnoreCase("list")){
							  if(pl.hasPermission("tc.list")){
							  	String message = "";
							  	Map<String, String>tclistmap = teamchat;
							  	String[] tclist = tclistmap.keySet().toArray(new String[tclistmap.size()]);
							  	if(tclist.length > 0){
							  		for(int i = 0; i < tclist.length; i++){
							  			message = message + ChatColor.AQUA + tclist[i] + ChatColor.GOLD + ChatColor.MAGIC + " | ";
							  		}
							  	} else {
							  		message = ChatColor.DARK_RED + " - - - ";
							  	}
							  	pl.sendMessage(Tag + "§a§lStafflist: " + message);
							  	return true;
						  	  } else {
						  		  pl.sendMessage(lang.noPermissions(pl));
						  		  return true;
						  	  }
						  	}

						  if(args[0].equalsIgnoreCase("kick")){
							  if(pl.hasPermission("tc.kick")){
								  if(args.length == 2){
									  Player target = Bukkit.getServer().getPlayer(args[1]);
									  if(target != null){
										  functions.kickPlayer(pl, target);
										  return true;
									  } else {
										  pl.sendMessage(Tag + ChatColor.GREEN + "Die ausgewählte Person ist nicht Online.");
										  return true;
									  }
								  } else {
									  pl.sendMessage(Tag + ChatColor.DARK_RED + "Um jemanden zukicken Benutz /tc kick <NAME>.");
									  return true;
								  }
							  } else {
								  pl.sendMessage(lang.noPermissions(pl));
								  return true;
							  }
						  }
						  return true;
					  }
				  } else {
					  pl.sendMessage(lang.noPermissions(pl));
					  return true;
				  }
			  }

		}

		return CanUseGoldenTouch;
	}

}
