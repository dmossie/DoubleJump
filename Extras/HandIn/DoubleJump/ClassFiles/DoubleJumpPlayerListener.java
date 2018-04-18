/*
Created By: Daniel Mossie
Double Jump Programming Assignment
Player Listener Class
*/

package com.javaminecraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DoubleJumpPlayerListener implements Listener{
    
    private String message;
    private String message_first;
                
    public void setMessage(String message){
        this.message = message;
    }
    
    public void setFirstMessage(String message_first){
        this.message_first = message_first;
    }
    
    //When a player joins the server: send them a message
    @EventHandler
    public void onLogin (PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (player.hasPlayedBefore()){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
        else{
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message_first));
        }      
    }
}