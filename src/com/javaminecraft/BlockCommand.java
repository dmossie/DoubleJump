/*
Created By: Daniel Mossie
Double Jump Programming Assignment
Block Command Class
*/

package com.javaminecraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BlockCommand implements CommandExecutor{
    private final DoubleJump plugin;
    
    public BlockCommand (DoubleJump plugin){
        this.plugin = plugin;
    }
    
    //This command changes the block which broken blocks are replaced with
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender.hasPermission("DoubleJump.block")){
            if (args.length == 1){
                sender.sendMessage(ChatColor.RED + "Please specify the block which broken blocks will be turned into");
                return false;
            }
            String block = args[1];
            if (Material.getMaterial(block.toUpperCase()) == null){
                sender.sendMessage(ChatColor.RED + "That is not a valid block");
                return false;
            }
            else{
                plugin.configValueChange("new_block", block.toUpperCase());
                sender.sendMessage(ChatColor.GREEN + "Replacement block set to: " + block);
                return true;
            }
        }
        else{
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
        }
        return false;
    }
}