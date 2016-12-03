package com.momnop.modularsonics;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModularSonicDevicesCreativeTab extends CreativeTabs {

	List list;
	public static ModularSonicDevicesCreativeTab INSTANCE = new ModularSonicDevicesCreativeTab();

	public ModularSonicDevicesCreativeTab() {
		super("sonicDevices");
	}

//	@Override
//	public ItemStack getIconItemStack() {
//		//return new ItemStack(ModularSonicsItems.ninthSonic);
//	}

	@Override
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}

	@Override
	public void displayAllRelevantItems(List list) {
		this.list = list;

		//this.addItem(ModularSonicsItems.ninthSonic);
		//this.addItem(ModularSonicsItems.tenthSonic);
	}

	private void addItem(Item item) {
		item.getSubItems(item, this, list);
	}

	private void addBlock(Block block) {
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.getItem(), this, list);
	}

}