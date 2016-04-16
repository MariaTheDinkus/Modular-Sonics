package com.momnop.modularsonics;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import akka.japi.Effect;

import com.momnop.modularsonics.api.ModuleFunctionType;
import com.momnop.modularsonics.items.ItemSonicScrewdriver;
import com.momnop.modularsonics.items.ModularSonicsItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

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

	public static boolean blockAndMetaCheck(World world, int x, int y, int z,
			Block block, int meta) {
		if (world.getBlock(x, y, z).equals(Blocks.iron_door)
				&& world.getBlockMetadata(x, y, z) == meta) {
			return true;
		} else {
			return false;
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerInteractEvent event) {
		int x = event.x;
		int y = event.y;
		int z = event.z;
		// System.out.println("TEST");
		if (event.entityPlayer.getHeldItem() != null
				&& event.entityPlayer.getHeldItem().getItem() instanceof ItemSonicScrewdriver
				&& event.action != event.action.LEFT_CLICK_BLOCK
				&& event.entityPlayer.getHeldItem().stackTagCompound != null
				&& event.entityPlayer.getHeldItem().stackTagCompound.getString(
						"owner").equals(event.entityPlayer.getDisplayName())
				|| event.entityPlayer.getHeldItem() != null
				&& event.entityPlayer.getHeldItem().getItem() instanceof ItemSonicScrewdriver
				&& event.action != event.action.LEFT_CLICK_BLOCK
				&& event.entityPlayer.getHeldItem().stackTagCompound == null
				|| event.entityPlayer.getHeldItem() != null
				&& event.entityPlayer.getHeldItem().getItem() instanceof ItemSonicScrewdriver
				&& event.action != event.action.LEFT_CLICK_BLOCK
				&& event.entityPlayer.getHeldItem().stackTagCompound != null
				&& event.entityPlayer.getHeldItem().stackTagCompound.getString("owner").isEmpty()) {
			if (event.entityPlayer.isSneaking() == false
					&& event.entityPlayer.getHeldItem().getItemDamage() == 1
					&& event.action == event.action.RIGHT_CLICK_BLOCK) {
				if (event.world.getBlock(x, y, z) instanceof BlockRedstoneWire) {
					if (event.world.getBlockMetadata(x, y, z) == 0) {
						event.world.setBlockMetadataWithNotify(x, y, z, 15, 1);
						// event.world.setBlock(p_147449_1_, p_147449_2_,
						// p_147449_3_, p_147449_4_,2,2)
						event.entityPlayer.playSound(
								"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
					} else if (event.world.getBlockMetadata(x, y, z) == 15) {
						event.world.setBlockMetadataWithNotify(x, y, z, 0, 1);
						event.entityPlayer.playSound(
								"sonicscrewdriver:item.sonic.use", 1.0F, 0.95F);
					}
				} else if (event.world.getBlock(x, y, z).equals(
						Blocks.redstone_lamp)) {
					event.world.setBlock(x, y, z, Blocks.lit_redstone_lamp);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (event.world.getBlock(x, y, z).equals(
						Blocks.lit_redstone_lamp)) {
					event.world.setBlock(x, y, z, Blocks.redstone_lamp);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 3)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 7, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 3)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 7, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 7)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 3, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 7)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 3, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 2)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 6, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 2)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 6, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 6)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 2, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 6)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 2, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 1)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 5, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 1)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 5, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 5)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 1, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 5)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 1, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 0)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 4, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 0)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 4, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y, z,
						Blocks.iron_door, 4)) {
					event.world.setBlockMetadataWithNotify(x, y, z, 0, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				} else if (blockAndMetaCheck(event.world, x, y - 1, z,
						Blocks.iron_door, 4)) {
					event.world.setBlockMetadataWithNotify(x, y - 1, z, 0, 2);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
				}
			} else if (event.entityPlayer.getHeldItem().getItemDamage() == 2
					&& event.action == event.action.RIGHT_CLICK_BLOCK) {
				event.entityPlayer.playSound("sonicscrewdriver:item.sonic.use",
						1.0F, 1.0F);
				if (event.world.isRemote == true) {
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Scanning block:"));
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Block Name: "
									+ StatCollector
											.translateToLocal(event.world
													.getBlock(x, y, z)
													.getLocalizedName())));
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Block ID: "
									+ Block.getIdFromBlock(event.world
											.getBlock(x, y, z)) + ":"
									+ event.world.getBlockMetadata(x, y, z)));
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Block Unlocalized Name: "
									+ event.world.getBlock(x, y, z)
											.getUnlocalizedName()));
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Block Harvest Tool: "
									+ event.world.getBlock(x, y, z)
											.getHarvestTool(
													event.world
															.getBlockMetadata(
																	x, y, z))));
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Block Harvest Level: "
									+ event.world.getBlock(x, y, z)
											.getHarvestLevel(
													event.world
															.getBlockMetadata(
																	x, y, z))));
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Block Hardness Level: "
									+ event.world.getBlock(x, y, z)
											.getBlockHardness(event.world, x,
													y, z)));
					event.entityPlayer.addChatMessage(new ChatComponentText(
							"Block Flammability Level: "
									+ event.world.getBlock(x, y, z)
											.getFlammability(event.world, x, y,
													z, ForgeDirection.UP)));
					if (event.world.getTileEntity(x, y, z) != null) {
						event.entityPlayer
								.addChatMessage(new ChatComponentText(
										"Block Has Tile Entity: " + "true"));
					} else if (event.world.getTileEntity(x, y, z) == null) {
						event.entityPlayer
								.addChatMessage(new ChatComponentText(
										"Block Has Tile Entity: " + "false"));
					}
				}
			} else if (event.entityPlayer.isSneaking() == false
					&& event.entityPlayer.getHeldItem().getItemDamage() == 3
					&& event.action == event.action.RIGHT_CLICK_AIR) {
				double x2 = event.entityPlayer.posX;
				double y2 = event.entityPlayer.posY;
				double z2 = event.entityPlayer.posZ;
				List entities = event.world.getEntitiesWithinAABB(
						EntityLivingBase.class, AxisAlignedBB.getBoundingBox(
								x2 - 50, y2 - 5, z2 - 50, x2 + 50, y2 + 256,
								z2 + 50));

				int playerCount = 0;
				int mobCount = 0;
				int animalCount = 0;
				int count = 0;
				boolean creeper = false;

				for (Object obj : entities) {
					count = count + 1;
					System.out.println(count);
					EntityLivingBase entityLiving = (EntityLivingBase) obj;
					if (entityLiving instanceof EntityMob
							|| entityLiving instanceof EntitySlime) {
						mobCount = mobCount + 1;
					} else if (entityLiving instanceof EntityPlayer
							&& !(entityLiving.equals(event.entityPlayer))) {
						playerCount = playerCount + 1;
					} else if (entityLiving instanceof EntityAnimal
							|| entityLiving instanceof EntityAmbientCreature) {
						animalCount = animalCount + 1;
					}

					if (entityLiving instanceof EntityCreeper) {
						event.entityPlayer.playSound("random.orb", 1.0F, 1.0F);
						creeper = true;
					}

					if (count == entities.size() && event.world.isRemote) {
						event.entityPlayer.playSound(
								"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
						event.entityPlayer
								.addChatMessage(new ChatComponentText(
										"Scanning Mobs. Calculated in a 50 block radius."));
						if (creeper == true) {
							event.entityPlayer
									.addChatMessage(new ChatComponentText(
											EnumChatFormatting.RED
													+ "CREEPER ALERT"));
						}
						event.entityPlayer
								.addChatMessage(new ChatComponentText(
										"Total Amount: " + (count + 1)));
						event.entityPlayer
								.addChatMessage(new ChatComponentText(
										"Player Amount: " + playerCount));
						event.entityPlayer
								.addChatMessage(new ChatComponentText(
										"Passive Mob Amount: " + animalCount));
						event.entityPlayer
								.addChatMessage(new ChatComponentText(
										"Hostile Mob Amount: " + mobCount));
					}
				}
			} else if (event.entityPlayer.isSneaking() == false
					&& event.entityPlayer.getHeldItem().getItemDamage() == 4
					&& event.action == event.action.RIGHT_CLICK_BLOCK
					&& event.world.getBlock(event.x, event.y, event.z) instanceof BlockTNT) {
				BlockTNT tnt = (BlockTNT) event.world.getBlock(event.x,
						event.y, event.z);
				tnt.func_150114_a(event.world, event.x, event.y, event.z, 1,
						event.entityPlayer);
				event.world.setBlockToAir(event.x, event.y, event.z);
			} else if (event.entityPlayer.isSneaking() == false
					&& event.entityPlayer.getHeldItem().getItemDamage() == 5
					&& event.action == event.action.RIGHT_CLICK_AIR) {
				if (event.world.isRaining()) {
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
					event.world.getWorldInfo().setRainTime(0);
					event.world.getWorldInfo().setRaining(false);
				} else if (event.world.isRaining() == false) {
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.use", 1.0F, 1.0F);
					event.world.getWorldInfo().setRaining(true);
				}
			} else if (event.entityPlayer.isSneaking() == false
					&& event.entityPlayer.getHeldItem().getItemDamage() == 6) {
				double x2 = event.entityPlayer.posX;
				double y2 = event.entityPlayer.posY;
				double z2 = event.entityPlayer.posZ;
				List entities = event.world
						.getEntitiesWithinAABB(EntityLivingBase.class,
								AxisAlignedBB.getBoundingBox(x2 - 4, y2 - 2,
										z2 - 4, x2 + 4, y2 + 2, z2 + 4));

				for (Object obj : entities) {
					EntityLivingBase entityLiving = (EntityLivingBase) obj;
					if (!(entityLiving.equals(event.entityPlayer))) {
						entityLiving.motionX = event.entityPlayer.getLookVec().xCoord;
						entityLiving.motionY = event.entityPlayer.getLookVec().yCoord;
						entityLiving.motionZ = event.entityPlayer.getLookVec().zCoord;
					}
				}
			} else if (event.entityPlayer.isSneaking() == false
					&& event.entityPlayer.getHeldItem().getItemDamage() == 7
					&& event.entityPlayer.isAirBorne) {
				event.entityPlayer.fallDistance = 0;
				event.entityPlayer.motionY -= 0.1F;
				if (event.entityPlayer.motionY < -0.1F) {
					event.entityPlayer.motionY = -0.1F;
				}
			} else if (event.entityPlayer.isSneaking() == false
					&& event.entityPlayer.getHeldItem().getItemDamage() == 7) {
				event.entityPlayer.fallDistance = 0;
			}
		}

		if (event.entityPlayer.getHeldItem() != null
				&& event.entityPlayer.getHeldItem().getItem() instanceof ItemSonicScrewdriver
				&& event.action == event.action.RIGHT_CLICK_AIR
				&& event.entityPlayer.getHeldItem().stackTagCompound != null
				&& event.entityPlayer.getHeldItem().stackTagCompound.getString(
						"owner").equals(event.entityPlayer.getDisplayName())
				|| event.entityPlayer.getHeldItem() != null
				&& event.entityPlayer.getHeldItem().getItem() instanceof ItemSonicScrewdriver
				&& event.action == event.action.RIGHT_CLICK_AIR
				&& event.entityPlayer.getHeldItem().stackTagCompound == null
				|| event.entityPlayer.getHeldItem() != null
				&& event.entityPlayer.getHeldItem().getItem() instanceof ItemSonicScrewdriver
				&& event.action == event.action.RIGHT_CLICK_AIR
				&& event.entityPlayer.getHeldItem().stackTagCompound != null
				&& event.entityPlayer.getHeldItem().stackTagCompound.getString("owner").isEmpty()) {
			if (event.entityPlayer.isSneaking()) {
				if (event.entityPlayer.getHeldItem().getItemDamage() == 7) {
					event.entityPlayer.getHeldItem().setItemDamage(0);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.modeswitch", 1.0F,
							1.0F);
				} else {
					event.entityPlayer.getHeldItem()
							.setItemDamage(
									event.entityPlayer.getHeldItem()
											.getItemDamage() + 1);
					event.entityPlayer.playSound(
							"sonicscrewdriver:item.sonic.modeswitch", 1.0F,
							1.0F);
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderGameOverlay(RenderGameOverlayEvent event) {
		if (event.type != ElementType.ALL) {
			return;
		}

		Tessellator tessellator = Tessellator.instance;
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPushMatrix();
		{
			if (Minecraft.getMinecraft().thePlayer.getHeldItem() != null
					&& Minecraft.getMinecraft().thePlayer.getHeldItem()
							.getItem() instanceof ItemSonicScrewdriver) {
				fr.drawString(""
						+ Minecraft.getMinecraft().thePlayer.getHeldItem()
								.getDisplayName(), 5, 7,
						ColorHelper.getDecimalFromRGB(63, 63, 63));

				fr.drawString(""
						+ Minecraft.getMinecraft().thePlayer.getHeldItem()
								.getDisplayName(), 4, 6,
						ColorHelper.getDecimalFromRGB(255, 255, 255));
			}

		}
		GL11.glPopMatrix();
	}

}
