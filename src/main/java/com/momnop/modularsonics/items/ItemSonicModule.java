package com.momnop.modularsonics.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.momnop.modularsonics.ModularSonicDevicesCreativeTab;
import com.momnop.modularsonics.ModularSonicModulesCreativeTab;
import com.momnop.modularsonics.api.IModule;
import com.momnop.modularsonics.api.ModuleFunctionType;
import com.momnop.modularsonics.api.ModuleType;

import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSonicModule extends Item implements IModule {
	
	private ModuleType moduleType;
	private ModuleFunctionType moduleFunctionType;
	private NBTTagCompound cmp = new NBTTagCompound();

	public ItemSonicModule(String unlocalizedName, ModuleType moduleType, ModuleFunctionType moduleFunctionType) {
		super();
		setCreativeTab(ModularSonicModulesCreativeTab.INSTANCE);
		setUnlocalizedName(unlocalizedName);
		setMaxStackSize(1);
		this.moduleType = moduleType;
		this.moduleFunctionType = moduleFunctionType;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister register) {
		this.itemIcon = register.registerIcon("sonicscrewdriver" + ":"
				+ getUnlocalizedName().substring(5));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player,
			List info, boolean useInfo) {
		NBTTagCompound tag = itemStack.stackTagCompound;
		info.add("Module is For: " + moduleType.toString());
	}
	
	public ModuleType getModuleType() {
		return moduleType;
	}
	
	@Override
	public ModuleFunctionType getModuleFunctionType(ItemStack stack) {
		return moduleFunctionType;
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		
        if (p_77648_7_ == 0)
        {
            --p_77648_5_;
        }

        if (p_77648_7_ == 1)
        {
            ++p_77648_5_;
        }

        if (p_77648_7_ == 2)
        {
            --p_77648_6_;
        }

        if (p_77648_7_ == 3)
        {
            ++p_77648_6_;
        }

        if (p_77648_7_ == 4)
        {
            --p_77648_4_;
        }

        if (p_77648_7_ == 5)
        {
            ++p_77648_4_;
        }

        if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, itemStack))
        {
            return false;
        }
        else
        {
            if (p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_) && itemStack.getItemDamage() == 4)
            {
                p_77648_3_.playSoundEffect(p_77648_4_, p_77648_5_, p_77648_6_, "sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
                p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.fire);
            }
            return true;
        }
    }
}