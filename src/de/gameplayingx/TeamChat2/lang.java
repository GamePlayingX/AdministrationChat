package de.gameplayingx.TeamChat2;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class lang extends Main {

    public static String TeamChatTag(){
    	String message = "";
		message = ChatColor.translateAlternateColorCodes('&', config.getString("tc.tag"));
		return message;
    }
    
    public static String TeamChatJoin(Player p, String kind){
    	String message = "";
    	if(kind.equalsIgnoreCase("chat")){
		message = ChatColor.translateAlternateColorCodes('&', config.getString("tc.join.chat.message"));
    	} else {
    	message = ChatColor.translateAlternateColorCodes('&', config.getString("tc.join.player.message"));
    	}
		message = message.replaceAll("%sender%", p.getName());
    	return message;
    }
    
    public static String TeamChatLeave(Player p, String kind){
    	String message = "";
    	if(kind.equalsIgnoreCase("chat")){
		message = ChatColor.translateAlternateColorCodes('&', config.getString("tc.leave.chat.message"));
    	} else {
    	message = ChatColor.translateAlternateColorCodes('&', config.getString("tc.leave.player.message"));
    	}
		message = message.replaceAll("%sender%", p.getName());
    	return message;
    }
    
    public static String TeamChatMessage(Player p, String ChatMessage){
    	String message = "";
		message = ChatColor.translateAlternateColorCodes('&', config.getString("tc.message.syntax"));
		message = message.replaceAll("%sender%", p.getName());
		message = message.replaceAll("%message%", ChatMessage);
		return message;
    }
    
    public static String TeamChatKick(Player p, Player target, String kind){
    	String message = "";
    	if(kind.equalsIgnoreCase("chat")){
    		message = config.getString("tc.leave.chat.kick");
    	} else {
    		message = config.getString("tc.leave.player.kick");
    	}
    	message = message.replaceAll("%sender%", p.getName());
    	message = message.replaceAll("%player%", target.getName());
    	message = ChatColor.translateAlternateColorCodes('&', message);
    	return message;
    }
    
    public static String TeamChatSpecialChar(){
    	String message = "";
    	message = config.getString("tc.general.global-chat");
    	return message;
    }

	public static String noPermissions(CommandSender sender){
		String message;
		message = ChatColor.translateAlternateColorCodes('&', config.getString("tc.general.no-permissions").replaceAll("%sender%", sender.getName()));
		return message;
	}

	public static String giveallMessage(Player p, CommandSender sender, int i,
			int itemNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
