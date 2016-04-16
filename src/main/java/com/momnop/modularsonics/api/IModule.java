
package com.momnop.modularsonics.api;

import net.minecraft.item.ItemStack;

/**
 * An item that implements this counts as a CAD component and can be used to
 * create a CAD.
 */
public interface IModule {

	public ModuleFunctionType getModuleFunctionType(ItemStack stack);

}