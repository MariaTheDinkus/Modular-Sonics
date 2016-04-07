package com.momnop.modularsonics;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Level;

import com.momnop.modularsonics.items.ModularSonicsItems;
import com.momnop.modularsonics.proxy.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(name = "Modular Sonics", modid = "sonicscrewdriver", version = "1.0.0")
public class ModularSonics
{
	@SidedProxy(clientSide="com.momnop.modularsonics.proxy.ClientProxy", serverSide="com.momnop.modularsonics.proxy.CommonProxy")
	public static CommonProxy proxy;
	
    public static ModularSonics INSTANCE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ModularSonicsItems.load();
    	proxy.initRenderers();
    	RecipeHandler.doRecipes();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(new ModEventHandler());
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        
    }
}