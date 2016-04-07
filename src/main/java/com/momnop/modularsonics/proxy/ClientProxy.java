package com.momnop.modularsonics.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.momnop.modularsonics.items.ModularSonicsItems;
import com.momnop.modularsonics.models.renderers.NinthSonicRender;
import com.momnop.modularsonics.models.renderers.SonicItemRender;
import com.momnop.modularsonics.models.renderers.TenthSonicRender;

public class ClientProxy
  extends CommonProxy
{
  public void initSounds() {}
  
  public void initRenderers()
  {
	TileEntitySpecialRenderer render = new NinthSonicRender();
	MinecraftForgeClient.registerItemRenderer(ModularSonicsItems.ninthSonic, new SonicItemRender(render, null));  
	  
    TileEntitySpecialRenderer render2 = new TenthSonicRender();
    MinecraftForgeClient.registerItemRenderer(ModularSonicsItems.tenthSonic, new SonicItemRender(render2, null));
  }
}


/* Location:           C:\Users\USER\Downloads\bwf-pre1.0b-dev.jar
 * Qualified Name:     com.codecommune.betterwithforge.proxy.ClientProxy
 * JD-Core Version:    0.7.0.1
 */