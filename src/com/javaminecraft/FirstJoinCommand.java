/*
Created By: Daniel Mossie
Double Jump Programming Assignment
FirstJoin Command Class
*/

package com.javaminecraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FirstJoinCommand implements CommandExecutor{
    private final DoubleJump plugin;
    
    public FirstJoinCommand (DoubleJump plugin){
        this.plugin = plugin;
    }
    
    //This command updates the values which players recieve when joining the server for the first time
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender.hasPermission("DoubleJump.firstjoin")){
            if (args.length == 1){
                sender.sendMessage(ChatColor.RED + "Please specify a message");
                return false;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                str.append(args[i]).append(" ");
            }
            String message = str.toString();
            plugin.configValueChange("message_first", message);
            sender.sendMessage(ChatColor.GREEN + "message_first set to: " + message);
            return true;
        }
        else{
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
        }
        return false;
    }
}