package com.javaminecraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class DoubleJump extends JavaPlugin implements Listener{
    //Permissions
    public Permission reload_permission = new Permission("DoubleJump.reload");
    public Permission block_permission = new Permission("DoubleJump.block");
    public Permission join_permission = new Permission("DoubleJump.join");
    public Permission firstjoin_permission = new Permission("DoubleJump.firstjoin");
    
    //When the plugin is enabled: create config.yml, set permisssions and listeners
    @Override
    public void onEnable(){
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        PluginManager pm = getServer().getPluginManager();
        pm.addPermission(reload_permission);
        pm.addPermission(block_permission);
        pm.addPermission(join_permission);
        pm.addPermission(firstjoin_permission);
        pm.registerEvents(this, this);
        
        getLogger().info("onEnable has been invoked");
    }
    //When the plugin is disabled
    @Override
    public void onDisable(){
        getLogger().info("onDisable has been invoked");
    }
    //When a command is executed
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("doublejump")){
            if (args.length == 0){
                sender.sendMessage("Please specify which command you want to use from the DoubleJump plugin.");
            }
            //Reloads the configuration file
            else if ("reload".equals(args[0])){
                if (sender.hasPermission("DoubleJump.reload")){
                    reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "The configuration file was reloaded");
                    return true;
                }
                else{
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
                }
            }
            //Sets the message when a player joins the server
            else if ("join".equals(args[0])){
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
                    configValueChange("message", message);
                    sender.sendMessage(ChatColor.GREEN + "Message set to: " + message);
                    return true;
                }
                else{
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
                }
            }
            //Sets the message when a player joins the server for the first time ever
            else if ("firstjoin".equals(args[0])){
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
                    configValueChange("message_first", message);
                    sender.sendMessage(ChatColor.GREEN + "message_first set to: " + message);
                    return true;
                }
                else{
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
                }
            }
            //Sets the block which broken blocks are changed to
            else if ("block".equals(args[0])){
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
                        configValueChange("new_block", block.toUpperCase());
                        sender.sendMessage(ChatColor.GREEN + "Replacement block set to: " + block);
                        return true;
                    }
                }
                else{
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "You do not have permission to use this command");
                }
            }
            else{
                sender.sendMessage(ChatColor.RED + "That is not a valid command.");
            }
        }
        return false;
    }
    //When a player joins the server: send them a message
    @EventHandler
    public void onLogin (PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (player.hasPlayedBefore()){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));
        }
        else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message_first")));
        }      
    }
    //When a block is broken: replace it with the default block
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(true);
        String material = getConfig().getString("new_block");
        Material replacement = Material.getMaterial(material);
        if (replacement == null){
            event.getPlayer().sendMessage(ChatColor.RED + "Error: Not a valid material, resetting material to GLASS");
            configValueChange("new_block","GLASS");
        }
        else{
            event.getBlock().setType(replacement,true);
        }    
    }
    //Change a value in the config file
    public void configValueChange(String key, String value){
        getConfig().set(key, value);
        saveConfig();
    }
}