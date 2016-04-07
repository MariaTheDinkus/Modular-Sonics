package com.momnop.modularsonics.models.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.momnop.modularsonics.blocks.tiles.TileEntityDummy;
import com.momnop.modularsonics.models.ModelTenthSonicOld;

public class SonicItemRender
  implements IItemRenderer
{
  TileEntitySpecialRenderer render;
  private ModelTenthSonicOld flintmodel;
  private TileEntity dummytile;
  
  public SonicItemRender(TileEntitySpecialRenderer render, TileEntity dummy)
  {
    this.render = render;
    this.dummytile = dummy;
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
  {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
	  if (type == type.INVENTORY) {
			this.render.renderTileEntityAt(new TileEntityDummy(), 1D, 0.35D, 1D, 0.0F);
		} else if (type == type.EQUIPPED) {
			GL11.glRotatef(225.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(65.0F, 1.0F, 0.0F, 0.0F);
			this.render.renderTileEntityAt(new TileEntityDummy(), -0.5D, -1.6D, -1.3D, 0.0F);
		} else if (type == type.EQUIPPED_FIRST_PERSON) {
			//GL11.glScalef(3.0F, 3.0F, 3.0F);
			GL11.glRotatef(135F, 0.0F, 1F, 0.0F);
			//GL11.glRotatef(15F, 1F, 0.0F, 0.0F);
			this.render.renderTileEntityAt(new TileEntityDummy(), -1.6D, 0.0D, -0.3D, 0.0F);
		} else if (type == type.ENTITY) {
			//GL11.glScalef(2.0F, 2.0F, 2.0F);
			//GL11.glRotatef(90F, 0.0F, 0.0F, 0.1F);
			this.render.renderTileEntityAt(new TileEntityDummy(), -0.5D, 0.0D, -0.5D, 0.0F);
		}
  	}
}


/* Location:           C:\Users\USER\Downloads\bwf-pre1.0b-dev.jar
 * Qualified Name:     com.codecommune.betterwithforge.models.renderers.TileEntityFlintRockItemRender
 * JD-Core Version:    0.7.0.1
 */