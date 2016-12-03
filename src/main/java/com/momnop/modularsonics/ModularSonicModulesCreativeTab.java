package com.momnop.modularsonics;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModularSonicModulesCreativeTab extends CreativeTabs {

	List list;
	public static ModularSonicModulesCreativeTab INSTANCE = new ModularSonicModulesCreativeTab();

	public ModularSonicModulesCreativeTab() {
		super("sonicModules");
	}

//	@Override
//	public ItemStack getIconItemStack() {
//		return new ItemStack(ModularSonicsItems.redstoneModule);
//	}

	@Override
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}

	@Override
	public void displayAllRelevantItems(List list) {
		this.list = list;
		
//		this.addItem(ModularSonicsItems.redstoneModule);
//		this.addItem(ModularSonicsItems.blockScanningModule);
//		this.addItem(ModularSonicsItems.mobScanningModule);
//		this.addItem(ModularSonicsItems.ignitionModule);
//		this.addItem(ModularSonicsItems.toggleDownfallModule);
//		this.addItem(ModularSonicsItems.interdictionModule);
//		this.addItem(ModularSonicsItems.featherFallingModule);
	}

	private void addItem(Item item) {
		item.getSubItems(item, this, list);
	}

	private void addBlock(Block block) {
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.getItem(), this, list);
	}

}