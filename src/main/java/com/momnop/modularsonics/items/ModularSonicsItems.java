package com.momnop.modularsonics.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModularSonicsItems
{
	public static Item ninthSonic, tenthSonic;
	
	public static void load()
    {
		ninthSonic = new ItemSonicScrewdriver("ninthSonic", "Ninth Sonic");
        tenthSonic = new ItemSonicScrewdriver("tenthSonic", "Tenth Sonic");
        
        register(ninthSonic);
        register(tenthSonic);
    }
    
    public static void register(Item b) {
		GameRegistry.registerItem(b, b.getUnlocalizedName().substring(5));
	}
}