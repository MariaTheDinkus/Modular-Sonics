package com.momnop.modularsonics.items;

import com.momnop.modularsonics.api.ModuleType;
import com.momnop.modularsonics.api.SonicType;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModularSonicsItems
{
	public static Item ninthSonic, tenthSonic;
	public static Item redstoneModule, blockScanningModule, mobScanningModule, ignitionModule, toggleDownfallModule, interdictionModule, featherFallingModule;
	
	public static void load()
    {
		ninthSonic = new ItemSonicScrewdriver("ninthSonic", SonicType.NINTH);
        tenthSonic = new ItemSonicScrewdriver("tenthSonic", SonicType.TENTH);
        
        redstoneModule = new ItemSonicModule("redstoneModule", ModuleType.SCREWDRIVER);
        blockScanningModule = new ItemSonicModule("blockScanningModule", ModuleType.SCREWDRIVER);
        mobScanningModule = new ItemSonicModule("mobScanningModule", ModuleType.SCREWDRIVER);
        ignitionModule = new ItemSonicModule("ignitionModule", ModuleType.SCREWDRIVER);
        toggleDownfallModule = new ItemSonicModule("toggleDownfallModule", ModuleType.SCREWDRIVER);
        interdictionModule = new ItemSonicModule("interdictionModule", ModuleType.SCREWDRIVER);
        featherFallingModule = new ItemSonicModule("featherFallingModule", ModuleType.SCREWDRIVER);
        
        register(ninthSonic);
        register(tenthSonic);
        
        register(redstoneModule);
        register(blockScanningModule);
        register(mobScanningModule);
        register(ignitionModule);
        register(toggleDownfallModule);
        register(interdictionModule);
        register(featherFallingModule);
    }
    
    public static void register(Item b) {
		GameRegistry.registerItem(b, b.getUnlocalizedName().substring(5));
	}
}