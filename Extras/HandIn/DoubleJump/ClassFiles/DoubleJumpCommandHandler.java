/*
Created By: Daniel Mossie
Double Jump Programming Assignment
Command Handler Class
*/

package com.javaminecraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.HashMap;

public class DoubleJumpCommandHandler implements CommandExecutor{
    
    //A list of commands
    private static final HashMap<String, CommandExecutor> commands = new HashMap<>();
    
    //Registers a command in the HashMap
    public void register(String name, CommandExecutor cmd){
        commands.put(name,cmd);
    }
    
    //Check to see if a command exists
    public boolean exists(String name){
        return commands.containsKey(name);
    }
    
    //Returns a command given the name
    public CommandExecutor getExecutor(String name){
        return commands.get(name);
    }

    //When a command is executed
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (args.length == 0){
            getExecutor("doublejump").onCommand(sender, cmd, label, args);
            return true;
        }
        else if (args.length > 0){
            if (exists(args[0])){
                getExecutor(args[0]).onCommand(sender, cmd, label, args);
            }
            else getExecutor("doublejump").onCommand(sender, cmd, label, args);
            return true;
        }
        else{
            sender.sendMessage("This command does not exist");
            return true;
        }
    }
}