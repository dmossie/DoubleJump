/*
Created By: Daniel Mossie
Double Jump Programming Assignment
Reload Command Class
*/

package com.javaminecraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor{
    private final DoubleJump plugin;
    
    public ReloadCommand (DoubleJump plugin){
        this.plugin = plugin;
    }
    
    //This command reloads the configuration file and updates the values
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender.hasPermission("DoubleJump.reload")){
            plugin.updateValues();
            sender.sendMessage(ChatColor.GREEN + "The configuration file was reloaded");
            return true;
        }
        else{
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
        }
        return false;
    }
}