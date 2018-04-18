/*
Created By: Daniel Mossie
Double Jump Programming Assignment
Event Listener Class
*/

package com.javaminecraft;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DoubleJumpEventListener implements Listener{
    
    private Material replacementBlock;
    
    public void setBlock(Material replacementBlock){
        this.replacementBlock = replacementBlock;
    }
    
    //When a block is broken: replace it with the default block
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(true);
        event.getBlock().setType(replacementBlock, true);   
    }
}