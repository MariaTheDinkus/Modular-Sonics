package com.momnop.modularsonics;

import com.momnop.modularsonics.items.ModularSonicsItems;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Handles the recipes
 */
public class RecipeHandler
{
    /**
     * Registers the mod's recipes
     */
    public static void doRecipes()
    {		
		GameRegistry.addRecipe(new ItemStack(ModularSonicsItems.tenthSonic, 1), new Object[] {" X ", "LIL", "OEO", 'X', Blocks.diamond_block, 'L', new ItemStack(Items.dye, 1, 4), 'O', Blocks.obsidian, 'I', Items.iron_ingot, 'E', Items.ender_pearl});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModularSonicsItems.ninthSonic, 1), new Object[] {ModularSonicsItems.tenthSonic, Blocks.sand});
		GameRegistry.addShapelessRecipe(new ItemStack(ModularSonicsItems.tenthSonic, 1), new Object[] {ModularSonicsItems.ninthSonic, Items.water_bucket});
    }

}