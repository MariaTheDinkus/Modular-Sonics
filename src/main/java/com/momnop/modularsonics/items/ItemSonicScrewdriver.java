package com.momnop.modularsonics.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.momnop.modularsonics.ModularSonicDevicesCreativeTab;
import com.momnop.modularsonics.ModularSonics;
import com.momnop.modularsonics.api.IModule;
import com.momnop.modularsonics.api.ISonic;
import com.momnop.modularsonics.api.ItemNBTHelper;
import com.momnop.modularsonics.api.ModuleFunctionType;
import com.momnop.modularsonics.api.SonicType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSonicScrewdriver extends Item implements ISonic {
	
	private SonicType sonicType;
	private NBTTagCompound tag = new NBTTagCompound();
	private static final String TAG_COMPONENT_PREFIX = "module";

	public ItemSonicScrewdriver(String unlocalizedName, SonicType sonicType) {
		super();
		setCreativeTab(ModularSonicDevicesCreativeTab.INSTANCE);
		setUnlocalizedName(unlocalizedName);
		setMaxStackSize(1);
		this.sonicType = sonicType;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
	
	public SonicType getSonicType() {
		return sonicType;
	}
	
	public static void initNBT(ItemStack stack) {
		if (!(stack.hasTagCompound())) {
			//stack.stackTagCompound = new NBTTagCompound();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player,
			List info, boolean useInfo) {
		//NBTTagCompound tag = itemStack.stackTagCompound;
		info.add("Casing: " + sonicType.toString());
		if (itemStack.stackTagCompound != null && !(tag.getString("owner").isEmpty())) {
			info.add("Owner: " + tag.getString("owner"));
		}
	}
	
	public static void setModule(ItemStack stack, ItemStack moduleStack) {
		if(moduleStack != null && moduleStack.getItem() instanceof IModule) {
			IModule module = (IModule) moduleStack.getItem();
			ModuleFunctionType moduleFunctionType = module.getModuleFunctionType(moduleStack);
			String name = TAG_COMPONENT_PREFIX + moduleFunctionType.name();

			NBTTagCompound cmp = new NBTTagCompound();
			moduleStack.writeToNBT(cmp);
			ItemNBTHelper.setCompound(stack, name, cmp);
		}
	}
	
	@Override
	public ItemStack getModuleInSlot(ItemStack stack, ModuleFunctionType type) {
		String name = TAG_COMPONENT_PREFIX + type.name();
		NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, name, true);

		if(cmp == null)
			return null;

		return ItemStack.loadItemStackFromNBT(cmp);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer entityPlayer) {
		initNBT(stack);
		//NBTTagCompound tag = stack.stackTagCompound;
		tag.setString("owner", entityPlayer.getDisplayName());
	}
	
	public void setBoolString(NBTTagCompound tag, String name, boolean bool, String string) {
		tag.setBoolean(name, bool);
		tag.setString(name, string);
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