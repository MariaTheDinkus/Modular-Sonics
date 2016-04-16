package com.momnop.modularsonics.items;

import com.momnop.modularsonics.api.ModuleFunctionType;
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
		ninthSonic = new ItemSonicScrewdriver("ninthSonic", SonicType.NINTHTENTH);
        tenthSonic = new ItemSonicScrewdriver("tenthSonic", SonicType.TENTH);
        
        redstoneModule = new ItemSonicModule("redstoneModule", ModuleType.SCREWDRIVER, ModuleFunctionType.REDSTONE);
        blockScanningModule = new ItemSonicModule("blockScanningModule", ModuleType.SCREWDRIVER, ModuleFunctionType.BLOCKSCANNING);
        mobScanningModule = new ItemSonicModule("mobScanningModule", ModuleType.SCREWDRIVER, ModuleFunctionType.MOBSCANNING);
        ignitionModule = new ItemSonicModule("ignitionModule", ModuleType.SCREWDRIVER, ModuleFunctionType.IGNITION);
        toggleDownfallModule = new ItemSonicModule("toggleDownfallModule", ModuleType.SCREWDRIVER, ModuleFunctionType.DOWNFALL);
        interdictionModule = new ItemSonicModule("interdictionModule", ModuleType.SCREWDRIVER, ModuleFunctionType.INTERDICTION);
        featherFallingModule = new ItemSonicModule("featherFallingModule", ModuleType.SCREWDRIVER, ModuleFunctionType.FEATHERFALL);
        
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