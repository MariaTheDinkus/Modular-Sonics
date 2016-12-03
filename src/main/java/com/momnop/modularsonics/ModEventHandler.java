package com.momnop.modularsonics;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

public class ModEventHandler {

	private int count;
	private int sonicMode = 0;

	public static int getWorldMinutes(World world) {
		int time = (int) Math.abs((world.getWorldTime() + 6000) % 24000);
		return (time % 1000) * 6 / 100;
	}

	public static int getWorldHours(World world) {
		int time = (int) Math.abs((world.getWorldTime() + 6000) % 24000);
		return (int) ((float) time / 1000F);
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerInteractEvent event) {
		
	}

//	@SubscribeEvent
//	@SideOnly(Side.CLIENT)
//	public void onRenderGameOverlay(RenderGameOverlayEvent event) {
//		if (event.type != ElementType.ALL) {
//			return;
//		}
//
//		Tessellator tessellator = Tessellator.instance;
//		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
//
//		GL11.glEnable(GL11.GL_BLEND);
//		GL11.glPushMatrix();
//		{
//			if (Minecraft.getMinecraft().thePlayer.getHeldItem() != null
//					&& Minecraft.getMinecraft().thePlayer.getHeldItem()
//							.getItem() instanceof ItemSonicScrewdriver) {
//				fr.drawString(""
//						+ Minecraft.getMinecraft().thePlayer.getHeldItem()
//								.getDisplayName(), 5, 7,
//						ColorHelper.getDecimalFromRGB(63, 63, 63));
//
//				fr.drawString(""
//						+ Minecraft.getMinecraft().thePlayer.getHeldItem()
//								.getDisplayName(), 4, 6,
//						ColorHelper.getDecimalFromRGB(255, 255, 255));
//			}
//
//		}
//		GL11.glPopMatrix();
//	}

}
