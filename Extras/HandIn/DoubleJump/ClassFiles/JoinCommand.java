/*
Created By: Daniel Mossie
Double Jump Programming Assignment
Join Command Class
*/

package com.javaminecraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class JoinCommand implements CommandExecutor{
    private final DoubleJump plugin;
    
    public JoinCommand (DoubleJump plugin){
        this.plugin = plugin;
    }
    
    //This command updates the message which players recieve when joining the server
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender.hasPermission("DoubleJump.join")){
            if (args.length == 1){
                sender.sendMessage(ChatColor.RED + "Please specify a message");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                str.append(args[i]).append(" ");
            }
            String message = str.toString();
            plugin.configValueChange("message", message);
            sender.sendMessage(ChatColor.GREEN + "Message set to: " + message);
            return true;
        }
        else{
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
        }        
        return false;
    }
}