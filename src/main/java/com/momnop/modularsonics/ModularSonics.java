package com.momnop.modularsonics;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import com.momnop.modularsonics.proxy.CommonProxy;

@Mod(name = "Modular Sonics", modid = "sonicscrewdriver", version = "1.0.0")
public class ModularSonics
{
	@SidedProxy(clientSide="com.momnop.modularsonics.proxy.ClientProxy", serverSide="com.momnop.modularsonics.proxy.CommonProxy")
	public static CommonProxy proxy;
	
    public static ModularSonics INSTANCE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	//ModularSonicsItems.load();
    	proxy.initRenderers();
    	//RecipeHandler.doRecipes();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//FMLCommonHandler.instance().bus().register(new ModEventHandler());
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