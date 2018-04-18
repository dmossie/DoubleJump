/*
Created By: Daniel Mossie
Double Jump Programming Assignment
DoubleJump Command Class
*/

package com.javaminecraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DoubleJumpCommand implements CommandExecutor{
    private final DoubleJump plugin;
    
    public DoubleJumpCommand (DoubleJump plugin){
        this.plugin = plugin;
    }
    
    //Show options for the double jump plugin
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        sender.sendMessage("First argument must be: reload, join, firstjoin or block");
        return true;
    }
}