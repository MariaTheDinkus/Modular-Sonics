package com.momnop.modularsonics.api;

import net.minecraft.item.ItemStack;

/**
 * Base interface for a CAD. You probably shouldn't implement this.
 */
public interface ISonic	 {

	/**
	 * Gets the component used for this CAD in the given slot.
	 */
	public ItemStack getModuleInSlot(ItemStack stack, ModuleFunctionType type);

}