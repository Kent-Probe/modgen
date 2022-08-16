package com.kentdar.modgen.setup;

import com.kentdar.modgen.ModGen;
import com.kentdar.modgen.block.BigChestBlock;
import com.kentdar.modgen.block.ModBlocks;
import com.kentdar.modgen.container.ElectrifierContainer;
import com.kentdar.modgen.container.ModContainers;
import com.kentdar.modgen.entity.ModEntityTypes;
import com.kentdar.modgen.entity.render.BuffaloRender;
import com.kentdar.modgen.item.BigChestItemStackTileEntityRender;
import com.kentdar.modgen.item.ModSpawnEggItem;
import com.kentdar.modgen.screens.BigChestScreen;
import com.kentdar.modgen.screens.ElectrifierScreen;
import com.kentdar.modgen.tileentity.BigChestTileEntityRender;
import com.kentdar.modgen.tileentity.ModTileEntities;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.datafix.fixes.TeamDisplayName;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModGen.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy{
    @Override
    public void init() {
        RenderTypeLookup.setRenderLayer(ModBlocks.ZUCCHINI_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_SAPLING.get(), RenderType.getCutout());

        ModSpawnEggItem.initSpawnEgg();

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BUFFALO.get(), BuffaloRender::new);

        ScreenManager.registerFactory(ModContainers.ELECTRIFIER_CONTAINER.get(), ElectrifierScreen::new);
        ScreenManager.registerFactory(ModContainers.BIG_CHEST_CONTAINER.get(), BigChestScreen::new);

        ClientRegistry.bindTileEntityRenderer(ModTileEntities.BIG_CHEST_TILE_ENTITY.get(), BigChestTileEntityRender::new);

    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
