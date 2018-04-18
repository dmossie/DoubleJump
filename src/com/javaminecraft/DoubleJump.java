/*
Created By: Daniel Mossie
Double Jump Programming Assignment
Main Class
*/

package com.javaminecraft;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class DoubleJump extends JavaPlugin {
    //Listeners
    DoubleJumpPlayerListener playerListener;
    DoubleJumpEventListener eventListener;
    
    //Command Handler
    DoubleJumpCommandHandler commandHandler;
           
    //Permissions
    public Permission reload_permission = new Permission("DoubleJump.reload");
    public Permission block_permission = new Permission("DoubleJump.block");
    public Permission join_permission = new Permission("DoubleJump.join");
    public Permission firstjoin_permission = new Permission("DoubleJump.firstjoin");
    
    //When the plugin is enabled: create config.yml, set permisssions, command executor and listeners
    @Override
    public void onEnable(){
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        PluginManager pm = getServer().getPluginManager();
        pm.addPermission(reload_permission);
        pm.addPermission(block_permission);
        pm.addPermission(join_permission);
        pm.addPermission(firstjoin_permission);
        
        playerListener = new DoubleJumpPlayerListener();
        eventListener = new DoubleJumpEventListener();
        
        pm.registerEvents(playerListener, this);
        pm.registerEvents(eventListener, this);
             
        registerCommands();
        updateValues();
        
        getLogger().info("onEnable has been invoked");
    }
    
    //When the plugin is disabled
    @Override
    public void onDisable(){
        getLogger().info("onDisable has been invoked");
    }
    
    //Register the Commands
    public void registerCommands(){
        commandHandler = new DoubleJumpCommandHandler();
        commandHandler.register("doublejump", new DoubleJumpCommand(this));
        commandHandler.register("reload", new ReloadCommand(this));
        commandHandler.register("block", new BlockCommand(this));
        commandHandler.register("join", new JoinCommand(this));
        commandHandler.register("firstjoin", new FirstJoinCommand(this));
        getCommand("DoubleJump").setExecutor(commandHandler);
        
    }
    
    //Change a value in the config file
    public void configValueChange(String key, String value){
        getConfig().set(key, value);
        saveConfig();
    }
    
    //Read the value in the config file
    public String getConfigValue(String key){
        return getConfig().getString(key);
    }
    
    //Set the values from the config file
    public void updateValues(){
	Material replacementBlock;
	String materialName = getConfigValue("new_block");
	if (Material.getMaterial(materialName) != null){
		replacementBlock = Material.getMaterial(materialName);
	}
	else {
                //Reset the material to glass if there is an error in the config file
		configValueChange("new_block","GLASS");
		replacementBlock = Material.GLASS;
	}

	eventListener.setBlock(replacementBlock);
	playerListener.setMessage(getConfigValue("message"));
	playerListener.setFirstMessage(getConfigValue("message_first"));

	
    }
}